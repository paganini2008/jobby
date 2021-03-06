/**
* Copyright 2018-2021 Fred Feng (paganini.fy@gmail.com)

* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package indi.atlantis.framework.chaconne;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import indi.atlantis.framework.chaconne.model.JobDetail;
import indi.atlantis.framework.chaconne.model.JobRuntimeDetail;
import indi.atlantis.framework.chaconne.model.JobTriggerDetail;
import indi.atlantis.framework.tridenter.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * NoneJobFuture
 * 
 * @author Fred Feng
 *
 * @since 1.0
 */
@Slf4j
public class NoneJobFuture implements JobFuture {

	private final JobKey jobKey;

	public NoneJobFuture(JobKey jobKey) {
		this.jobKey = jobKey;
	}

	private final AtomicBoolean done = new AtomicBoolean();
	private final AtomicBoolean cancel = new AtomicBoolean();

	@Override
	public void cancel() {
		done.set(true);
		cancel.set(true);
	}

	@Override
	public boolean isDone() {
		return done.get();
	}

	@Override
	public boolean isCancelled() {
		return cancel.get();
	}

	@Override
	public long getNextExectionTime(Date lastExecutionTime, Date lastActualExecutionTime, Date lastCompletionTime) {
		JobManager jobManager = ApplicationContextUtils.getBean(JobManager.class);
		JobDetail jobDetail;
		try {
			jobDetail = jobManager.getJobDetail(jobKey, true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return NEXT_EXECUTION_TIME_NOT_FOUND;
		}
		JobTriggerDetail triggerDetail = jobDetail.getJobTriggerDetail();
		if (triggerDetail.getTriggerType() == TriggerType.DEPENDENT) {
			if (triggerDetail.getTriggerDescriptionObject().getDependency().getDependencyType() == DependencyType.PARALLEL) {
				JobRuntimeDetail jobRuntime;
				try {
					JobKey relation = jobManager.getRelations(jobKey, DependencyType.PARALLEL)[0];
					jobRuntime = jobManager.getJobRuntimeDetail(relation);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					return NEXT_EXECUTION_TIME_NOT_FOUND;
				}
				return jobRuntime.getNextExecutionTime().getTime();
			}
		}
		return NEXT_EXECUTION_TIME_NOT_FOUND;
	}

}
