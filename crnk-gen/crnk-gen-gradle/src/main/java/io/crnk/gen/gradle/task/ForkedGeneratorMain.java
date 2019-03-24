package io.crnk.gen.gradle.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.crnk.gen.base.GeneratorModule;
import io.crnk.gen.gradle.GeneratorConfig;
import io.crnk.gen.gradle.GeneratorContext;
import io.crnk.gen.gradle.runtime.RuntimeMetaResolver;

import java.io.File;

public class ForkedGeneratorMain {

    public static void main(String[] args) {
        try {
            File configFile = new File(args[0]);
            Class moduleClass = Class.forName(args[1]);

            GeneratorModule module = (GeneratorModule) moduleClass.newInstance();

            ObjectMapper mapper = new ObjectMapper();
            GeneratorConfig config = mapper.readerFor(GeneratorConfig.class).readValue(configFile);

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            GeneratorContext context = new GeneratorContext(module, classLoader, config);

            RuntimeMetaResolver runtime = (RuntimeMetaResolver) Class.forName(config.computeMetaResolverClassName()).newInstance();
            runtime.run(context, classLoader);
            System.exit(0);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }
}
