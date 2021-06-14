package indi.atlantis.framework.chaconne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.atlantis.framework.chaconne.model.JobLifeCycleParameter;
import indi.atlantis.framework.chaconne.model.JobParameter;
import indi.atlantis.framework.chaconne.model.JobResult;

/**
 * 
 * JobAdminController
 * 
 * @author Fred Feng
 *
 * @since 1.0
 */
@RestController
@RequestMapping("/job/admin")
public class JobAdminController {

	@Autowired
	private JobAdmin jobAdmin;

	@Autowired
	private ScheduleAdmin scheduleAdmin;

	@PostMapping("/triggerJob")
	public ResponseEntity<JobResult<JobState>> triggerJob(@RequestBody JobParameter jobParam) throws Exception {
		JobState jobState = jobAdmin.triggerJob(jobParam.getJobKey(), jobParam.getAttachment());
		return ResponseEntity.ok(JobResult.success(jobState));
	}

	@PostMapping("/publicLifeCycleEvent")
	public ResponseEntity<JobResult<String>> publicLifeCycleEvent(@RequestBody JobLifeCycleParameter jobParam) throws Exception {
		jobAdmin.publicLifeCycleEvent(jobParam.getJobKey(), jobParam.getLifeCycle());
		return ResponseEntity.ok(JobResult.success("ok"));
	}

	@PostMapping("/unscheduleJob")
	public ResponseEntity<JobResult<JobState>> unscheduleJob(@RequestBody JobKey jobKey) {
		JobState jobState = scheduleAdmin.unscheduleJob(jobKey);
		return ResponseEntity.ok(JobResult.success(jobState));
	}

	@PostMapping("/scheduleJob")
	public ResponseEntity<JobResult<JobState>> scheduleJob(@RequestBody JobKey jobKey) {
		JobState jobState = scheduleAdmin.scheduleJob(jobKey);
		return ResponseEntity.ok(JobResult.success(jobState));
	}

}
