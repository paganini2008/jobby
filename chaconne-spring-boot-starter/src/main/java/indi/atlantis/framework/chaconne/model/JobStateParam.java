package indi.atlantis.framework.chaconne.model;

import indi.atlantis.framework.chaconne.JobKey;
import indi.atlantis.framework.chaconne.JobState;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * JobStateParam
 * 
 * @author Fred Feng
 *
 * @since 1.0
 */
@Getter
@Setter
public class JobStateParam {

	private JobKey jobKey;
	private JobState jobState;

	public JobStateParam() {
	}

	public JobStateParam(JobKey jobKey, JobState jobState) {
		this.jobKey = jobKey;
		this.jobState = jobState;
	}

}
