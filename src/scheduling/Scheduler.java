package scheduling;

import java.util.ArrayList;
import java.util.LinkedList;
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
}
