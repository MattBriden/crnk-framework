package io.crnk.gen.gradle.runtime.spring;

import io.crnk.gen.base.GeneratorModule;
import io.crnk.gen.gradle.GeneratorContext;
import io.crnk.gen.gradle.runtime.RuntimeMetaResolver;

import java.lang.reflect.Method;

public class SpringMetaResolver implements RuntimeMetaResolver {

    @Override
    public void run(GeneratorContext context, ClassLoader classLoader) {
        try {
            Class<?> runnerClass = classLoader.loadClass(SpringRunner.class.getName());
            Object runner = runnerClass.newInstance();
            Method method = runnerClass.getMethod("run", GeneratorModule.class);
            method.invoke(runner, context);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
