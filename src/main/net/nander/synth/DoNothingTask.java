package net.nander.synth;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.scheduling.Task;
import net.nander.synth.scheduling.Worker;

public class DoNothingTask implements Task {
    String name;
    public DoNothingTask(String name){
        this.name = name;
    }
    @Override
    public void run() { }

    public static void main(String[] args){
        Task t1 = new DoNothingTask("1");
        Task t2 = new DoNothingTask("2");
        Scheduler.getInstance().addTask(t1);
        Scheduler.getInstance().addTask(t2);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();
    }
}
