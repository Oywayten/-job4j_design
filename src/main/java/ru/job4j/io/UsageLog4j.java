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
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("he is a teacher on the {} course and it is {}.", courseName, isTeacher);
        LOG.debug("My learning progress :");
        LOG.debug("Start date {}, last activity : {}", startDate, lastActivity);
        LOG.debug("Days of training {}, tasks per day {}", daysOfLearning, tasksPerDay);
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}