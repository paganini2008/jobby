package indi.atlantis.framework.chaconne.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import indi.atlantis.framework.chaconne.JobKey;
import indi.atlantis.framework.chaconne.LogLevel;
import indi.atlantis.framework.chaconne.LogManager;
import indi.atlantis.framework.chaconne.model.JobLogParam;
import indi.atlantis.framework.chaconne.model.JobResult;

/**
 * 
 * RestLogManager
 * 
 * @author Jimmy Hoff
 *
 * @since 1.0
 */
public class RestLogManager implements LogManager {

	@Autowired
	private ClusterRestTemplate restTemplate;

	@Async
	@Override
	public void log(long traceId, JobKey jobKey, LogLevel logLevel, String messagePattern, Object[] args, String[] stackTraces) {
		ResponseEntity<JobResult<String>> responseEntity = restTemplate.perform(jobKey.getClusterName(), "/job/manager/log",
				HttpMethod.POST, new JobLogParam(traceId, jobKey, logLevel, messagePattern, args, stackTraces),
				new ParameterizedTypeReference<JobResult<String>>() {
				});
		responseEntity.getBody().getData();
	}

	@Override
	public void log(long traceId, LogLevel level, JobKey jobKey, String msg, String[] stackTraces) {
		throw new UnsupportedOperationException("log");
	}

	@Override
	public void error(long traceId, JobKey jobKey, String msg, String[] stackTraces) {
		throw new UnsupportedOperationException("error");
	}

}