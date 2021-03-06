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
package indi.atlantis.framework.chaconne.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.atlantis.framework.chaconne.model.Result;
import indi.atlantis.framework.tridenter.ApplicationInfo;

/**
 * 
 * JobServerRegistryController
 * 
 * @author Fred Feng
 *
 * @since 1.0
 */
@RestController
@RequestMapping("/job/admin")
public class JobServerRegistryController {

	@Autowired
	private JobServerRegistry jobServerRegistry;

	@PostMapping("/registerCluster")
	public ResponseEntity<Result<Boolean>> registerCluster(@RequestBody ApplicationInfo applicationInfo) {
		jobServerRegistry.registerCluster(applicationInfo);
		return ResponseEntity.ok(Result.success(Boolean.TRUE));
	}

}
