package io.crnk.gen.typescript;

import io.crnk.gen.base.GeneratorModule;
import io.crnk.gen.typescript.internal.TSGenerator;
import io.crnk.gen.typescript.internal.TSGeneratorRuntimeContext;
import io.crnk.gen.typescript.model.writer.TSCodeStyle;
import io.crnk.gen.typescript.processor.TSSourceProcessor;
import io.crnk.meta.MetaLookup;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;


public class TSGeneratorModule implements GeneratorModule, TSGeneratorRuntimeContext {

    private File outputDir;

    private TSGeneratorConfig config;

    private ClassLoader classloader;

    @Override
    public String getName() {
        return "Typescript";
    }

    @Override
    public void generate(Object meta) throws IOException {
        MetaLookup metaLookup = (MetaLookup) meta;

        TSGenerator gen = new TSGenerator(outputDir, metaLookup, config);
        gen.run();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (classloader == null) {
            throw new IllegalStateException();
        }
        return classloader;
    }

    @Override
    public void setClassLoader(ClassLoader classloader) {
        this.classloader = classloader;
    }

    public TSGeneratorConfig getConfig() {
        return config;
    }

    @Override
    public void initDefaults(File buildDir) {
        config.setBuildDir(buildDir);
        config.getExcludes().add("resources.meta");
        config.getNpm().setPackageName("@packageNameNotSpecified");
    }

    @Override
    public File getGenDir() {
        return config.getGenDir();
    }

    @Override
    public Collection<Class> getConfigClasses() {
        return Arrays.asList(TSCodeStyle.class, TSResourceFormat.class, TSGeneratorConfig.class,
                TSNpmConfiguration.class, TSSourceProcessor.class);
    }

    @Override
    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public void setConfig(TSGeneratorConfig config) {
        this.config = config;
    }
}