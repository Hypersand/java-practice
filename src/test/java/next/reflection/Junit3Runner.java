package next.reflection;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Junit3Runner {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void runner() throws Exception {
        Class clazz = Junit3Test.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("test")) {
                logger.debug("Classs method {}", method.getName());
                method.invoke(clazz.getDeclaredConstructor().newInstance());
            }
        }


    }
}
