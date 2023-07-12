package next.reflection;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class Junit4Runner {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyTest.class)) {
                logger.debug("Classs method {}", method.getName());
                method.invoke(clazz.getDeclaredConstructor().newInstance());
            }
        }

    }
}


