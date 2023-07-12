package next.reflection;

import java.awt.desktop.SystemSleepEvent;

public class Student {
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @ElapsedTime
    public void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }
}
