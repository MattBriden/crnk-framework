package io.crnk.gen.gradle;

import io.crnk.gen.gradle.runtime.RuntimeConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorConfig {

    protected RuntimeConfiguration runtime = new RuntimeConfiguration();

    private List<String> resourcePackages;

    private String metaResolverClassName = null;

    private boolean forked = false;

    private Map<String, Object> moduleConfig = new HashMap<>();

    public Object getModuleConfig(String name) {
        return moduleConfig.get(name);
    }

    public RuntimeConfiguration getRuntime() {
        return runtime;
    }

    public String computeMetaResolverClassName() {
        if (metaResolverClassName != null) {
            return metaResolverClassName;
        }
        if (getResourcePackages() != null) {
            return "io.crnk.gen.gradle.runtime.reflections.ReflectionsMetaResolver";
        }
        if (runtime.getSpring().getConfiguration() != null) {
            return "io.crnk.gen.gradle.runtime.spring.SpringMetaResolver";
        }
        return "io.crnk.gen.gradle.runtime.cdi.CdiMetaResolver";
    }

    /**
     * @return scans the given package with reflection and generates those resources.
     * Provides a quick way of generation without having to start the full application (CDI, Spring, etc.)
     */
    public List<String> getResourcePackages() {
        return resourcePackages;
    }

    public void setResourcePackages(List<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
    }

    boolean isForked() {
        return forked;
    }

    public void setForked(boolean forked) {
        this.forked = forked;
    }

    public String getMetaResolverClassName() {
        return metaResolverClassName;
    }

    public void setMetaResolverClassName(String metaResolverClassName) {
        this.metaResolverClassName = metaResolverClassName;
    }
}
