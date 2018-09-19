package net.nander.synth.scheduling;

import java.util.LinkedList;

public class MultiTask implements Task{
    LinkedList<Task> taskLinkedList = new LinkedList<>();

    public void addTask(Task t){
        taskLinkedList.add(t);
    }
    public int size(){
        return taskLinkedList.size();
    }

    @Override
    public void run(){
        for(Task t : taskLinkedList){
            t.run();
        }
    }
}
