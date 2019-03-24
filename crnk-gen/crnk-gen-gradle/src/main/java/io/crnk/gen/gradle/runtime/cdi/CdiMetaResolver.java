package io.crnk.gen.gradle.runtime.cdi;

import io.crnk.gen.base.GeneratorModule;
import io.crnk.gen.gradle.GeneratorContext;
import io.crnk.gen.gradle.runtime.RuntimeMetaResolver;

import java.lang.reflect.Method;

/**
 * It is quite difficult to setup a JEE application locally, so going the Deltaspike way seems the simplest approach.
 * Executed the code generation as a Deltaspike test, which is/should already be setup by the project.
 */
public class CdiMetaResolver implements RuntimeMetaResolver {

    @Override
    public void run(GeneratorContext config, ClassLoader classLoader) {
        try {
            Class<?> runnerClass = classLoader.loadClass(CdiRunner.class.getName());
            Object runner = runnerClass.newInstance();
            Method method = runnerClass.getMethod("run", GeneratorModule.class);
            method.invoke(runner, config);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
