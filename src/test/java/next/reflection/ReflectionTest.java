package next.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import next.optional.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() {
        SoftAssertions s = new SoftAssertions();
        Class<Question> clazz = Question.class;
        logger.debug("Classs Name {}", clazz.getName());

        for (Constructor constructor : clazz.getConstructors()) {
            logger.debug("Classs Constructor {}", constructor.getName());
        }


        for (Field field : clazz.getDeclaredFields()) {
            logger.debug("Classs field {}", field.getName());

        }

        for (Method method : clazz.getMethods()) {
            logger.debug("Classs method {}", method.getName());
        }
    }

    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());

        Field nameField = clazz.getDeclaredField("name");
        Field ageField = clazz.getDeclaredField("age");
        nameField.setAccessible(true);
        ageField.setAccessible(true);

        Student student = new Student();
        nameField.set(student, "손승완");
        ageField.set(student, 25);

        assertThat(student.getName()).isEqualTo("손승완");
        assertThat(student.getAge()).isEqualTo(25);

    }

    @Test
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }

    @Test
    void constructor_param() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<User> clazz = User.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> constructor = constructors[0];

        User user = (User) constructor.newInstance("홍선기", 25);
        assertThat(user.getName()).isEqualTo("홍선기");
        assertThat(user.getAge()).isEqualTo(25);
    }

    @Test
    void time() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Student> clazz = Student.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ElapsedTime.class)) {
                long start = System.currentTimeMillis();
                method.invoke(clazz.getDeclaredConstructor().newInstance());
                long end = System.currentTimeMillis();

                logger.info("걸린 시간 = {}초", (end - start) / 1000.0);
            }
        }

    }
}
