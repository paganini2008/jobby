package indi.atlantis.framework.chaconne.cluster;

/**
 * 
 * DetachedMode
 * 
 * @author Fred Feng
 *
 * @version 1.0
 */
public enum DetachedMode {

	CONSUMER("consumer"), PRODUCER("producer");

	private final String role;

	private DetachedMode(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
