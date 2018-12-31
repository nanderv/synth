package net.nander.synth.scheduling;

import net.nander.synth.Config;
import net.nander.synth.OsTools;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    List<TaskScheduling> taskList;
    int index;
    int tasksDone;
    static Scheduler instance;

    public static Scheduler getInstance(){
        if(instance == null)
            instance = new Scheduler();
        return instance;
    }

    public Scheduler(){
        taskList = new ArrayList<>();
    }

    public void addTask(Task t){
        taskList.add(new TaskScheduling(t, taskList.size()));
    }

    public static void addTaskDirectly(Task t) { Scheduler.getInstance().addTask(t);}
;
    public TaskScheduling getTask(int oldID){
        synchronized (instance){
            if(oldID != -1){
                tasksDone++;
            }
            if(tasksDone == taskList.size()) {
                index = 0;
                tasksDone = 0;
            }

            if(index < taskList.size()) {
                TaskScheduling obj = taskList.get(index);

                index = index + 1;
                return obj;
            }
            else
                return null;
        }
    }

    public static void configFreeRun(){
        int hardwareCores = OsTools.getNumberOfCPUCores();
        int used = hardwareCores;
        if (Config.CORES > 0){
            used = Config.CORES;
        }
        if(used > hardwareCores)
            System.out.println("Warning, using " + used+ " threads, while having only " + hardwareCores + " cores.");
        else
            System.out.println("Using " + used + " threads on " + hardwareCores + " cores.");
        for(int i=0;i<Math.max(used, 1); i++){
            new Worker().start();
        }

    }
}
