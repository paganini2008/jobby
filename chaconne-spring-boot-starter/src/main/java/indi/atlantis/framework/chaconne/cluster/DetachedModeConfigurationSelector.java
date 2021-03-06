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

import java.util.Collections;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 
 * DetachedModeConfigurationSelector
 * 
 * @author Fred Feng
 *
 * @version 1.0
 */
public class DetachedModeConfigurationSelector implements ImportSelector, EnvironmentAware {

	private Environment environment;

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		AnnotationAttributes annotationAttributes = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(EnableChaconneDetachedMode.class.getName()));
		DetachedMode detachedMode = annotationAttributes.getEnum("value");
		((ConfigurableEnvironment) environment).getPropertySources().addLast(new MapPropertySource("chaconne:config:detachedMode",
				Collections.singletonMap("atlantis.framework.chaconne.detachedMode", detachedMode.getRole())));
		return new String[] { DetachedModeConfiguration.class.getName() };
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
