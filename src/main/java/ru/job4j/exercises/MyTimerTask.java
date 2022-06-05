package ru.job4j.exercises;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    public void run() {
        System.out.println("Timer job in progress");
    }
}

class TTest {
    public static void main(String[] args) {
        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();
        myTimer.schedule(myTask, 5000, 1000);
        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myTimer.cancel();
    }
}
