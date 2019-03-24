package io.crnk.gen.gradle;

import io.crnk.gen.base.GeneratorModule;
import io.crnk.meta.MetaLookup;

import java.io.IOException;

public class GeneratorContext {

    private final GeneratorModule module;

    private final GeneratorConfig config;

    private final ClassLoader classloader;

    public GeneratorContext(GeneratorModule module, ClassLoader classloader, GeneratorConfig config) {
        this.config = config;
        this.module = module;
        this.classloader = classloader;
    }

    public GeneratorConfig getConfig() {
        return config;
    }

    public void generate(MetaLookup lookup) throws IOException {
        module.generate(lookup);
    }

    public ClassLoader getClassLoader() {
        return classloader;
    }
}
