package main;

import scheduling.Scheduler;
import scheduling.Task;
import scheduling.Worker;

public class TestTask implements Task {
    String name;
    public TestTask(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name);
    }

    public static void main(String[] args){
        Task t1 = new TestTask("1");
        Task t2 = new TestTask("2");
        Scheduler.getInstance().addTask(t1);
        Scheduler.getInstance().addTask(t2);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();
    }
}
