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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.paganini2008.devtools.enums.EnumConstant;

/**
 * 
 * JobState
 *
 * @author Fred Feng
 * @since 1.0
 */
public enum JobState implements EnumConstant {

	NOT_SCHEDULED(0, "Not scheduled"), 
	SCHEDULING(1, "Scheduling"), 
	RUNNING(2, "Running"), 
	PAUSED(3, "Paused"), 
	FINISHED(4, "Finished"), 
	FROZEN(10, "Frozen"),
	NONE(99, "None");

	private JobState(int value, String repr) {
		this.value = value;
		this.repr = repr;
	}

	private final int value;
	private final String repr;

	@JsonValue
	public int getValue() {
		return value;
	}

	@Override
	public String getRepr() {
		return repr;
	}

	@JsonCreator
	public static JobState valueOf(int value) {
		for (JobState jobState : JobState.values()) {
			if (jobState.getValue() == value) {
				return jobState;
			}
		}
		throw new IllegalArgumentException("Unknown jobState: " + value);
	}

}
