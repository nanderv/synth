package net.nander.synth.scheduling;

public class Worker extends Thread {
    @Override
    public void run(){
        int lastTask = -1;

        while(true){
            Scheduler scheduler = Scheduler.getInstance();
            TaskScheduling taskScheduling = scheduler.getTask(lastTask);
            if(taskScheduling != null){
                taskScheduling.task.run();
                lastTask = taskScheduling.slot;
            } else{
                lastTask = -1;
                try {
                    Thread.sleep(0,100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
