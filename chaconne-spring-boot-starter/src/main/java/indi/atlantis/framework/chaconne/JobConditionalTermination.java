/**
* Copyright 2021 Fred Feng (paganini.fy@gmail.com)

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

/**
 * 
 * JobConditionalTermination
 * 
 * @author Fred Feng
 *
 * @version 1.0
 */
public abstract class JobConditionalTermination implements JobListener {

	@Override
	public void beforeRun(long traceId, JobKey jobKey, Object attachment, Date startDate) {
		if (apply(traceId, jobKey, attachment, startDate)) {
			throw new JobTerminationException(jobKey, "Job '" + jobKey + "' has terminated on datetime: " + startDate);
		}
	}

	protected abstract boolean apply(long traceId, JobKey jobKey, Object attachment, Date startDate);

}
