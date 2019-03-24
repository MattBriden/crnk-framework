package io.crnk.gen.gradle.runtime;

import groovy.lang.Closure;
import io.crnk.gen.gradle.runtime.spring.SpringRuntimeConfig;
import org.gradle.api.Project;

public class RuntimeExtension extends RuntimeConfiguration {

	private Project project;

	public RuntimeExtension(Project project) {
		this.project = project;
	}

	public SpringRuntimeConfig spring(Closure closure) {
		return (SpringRuntimeConfig) project.configure(getSpring(), closure);
	}
}
