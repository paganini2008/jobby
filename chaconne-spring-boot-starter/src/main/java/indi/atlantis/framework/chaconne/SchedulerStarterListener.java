package indi.atlantis.framework.chaconne;

import indi.atlantis.framework.tridenter.election.ApplicationClusterLeaderEvent;

/**
 * 
 * SchedulerStarterListener
 * 
 * @author Jimmy Hoff
 *
 * @since 1.0
 */
public interface SchedulerStarterListener {

	void onApplicationEvent(ApplicationClusterLeaderEvent event);
}