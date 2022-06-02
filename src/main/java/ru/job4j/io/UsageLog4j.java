package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean isTeacher = true;
        String courseName = "job4j";
        String startDate = "18.01.2022 19:41";
        String lastActivity = "02.06.2022 08:35";
        int daysOfLearning = 135;
        float tasksPerDay = 2.41f;
        LOG.trace("trace message");
        LOG.debug("\nUser info name : {}, age : {} , he is a teacher on the {} course and it is {}.\n"
                        .concat("My learning progress : Начало обучения {}, "
                                .concat("последняя активность : {} , дней обучения {}, задач в день {}.")),
                name, age, courseName, isTeacher, startDate, lastActivity, daysOfLearning, tasksPerDay);
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}