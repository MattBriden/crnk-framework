package io.crnk.gen.gradle;

import groovy.lang.Closure;
import io.crnk.gen.gradle.runtime.RuntimeConfiguration;
import io.crnk.gen.gradle.runtime.RuntimeExtension;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.UnknownDomainObjectException;
import org.gradle.api.plugins.ExtensionAware;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.ExtensionsSchema;
import org.gradle.api.plugins.ExtraPropertiesExtension;
import org.gradle.api.reflect.TypeOf;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;

public class GeneratorExtension extends GeneratorConfig implements ExtensionAware {

    private Project project;

    private Runnable initMethod;

    public GeneratorExtension(Project project, Runnable initMethod) {
        this.project = project;
        this.initMethod = initMethod;
        this.runtime = new RuntimeExtension(project);

        // reconfigure within extension, not in TSGeneratorConfig since the later is used also in forked mode
        setForked(true);
    }

    public void init() {
        initMethod.run();
    }

    public RuntimeConfiguration runtime(Closure closure) {
        return (RuntimeConfiguration) project.configure(getRuntime(), closure);
    }


    @Override
    public ExtensionContainer getExtensions() {
        return new ExtensionContainer() {
            @Override
            public <T> void add(Class<T> aClass, String s, T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> void add(TypeOf<T> typeOf, String s, T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(String s, Object o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T create(Class<T> aClass, String s, Class<? extends T> aClass1, Object... objects) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T create(TypeOf<T> typeOf, String s, Class<? extends T> aClass, Object... objects) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T create(String s, Class<T> aClass, Object... objects) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Map<String, TypeOf<?>> getSchema() {
                throw new UnsupportedOperationException();
            }

            @Override
            public ExtensionsSchema getExtensionsSchema() {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T getByType(Class<T> aClass) throws UnknownDomainObjectException {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T getByType(TypeOf<T> typeOf) throws UnknownDomainObjectException {
                throw new UnsupportedOperationException();
            }

            @Nullable
            @Override
            public <T> T findByType(Class<T> aClass) {
                throw new UnsupportedOperationException();
            }

            @Nullable
            @Override
            public <T> T findByType(TypeOf<T> typeOf) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Object getByName(String name) throws UnknownDomainObjectException {
                return getModuleConfig(name);
            }

            @Nullable
            @Override
            public Object findByName(String name) {
                return getModuleConfig(name);
            }

            @Override
            public <T> void configure(Class<T> aClass, Action<? super T> action) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> void configure(TypeOf<T> typeOf, Action<? super T> action) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> void configure(String name, Action<? super T> action) {
                Object moduleConfig = getModuleConfig(name);
                project.configure(Arrays.asList(moduleConfig), (Action) action);
            }

            @Override
            public ExtraPropertiesExtension getExtraProperties() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
