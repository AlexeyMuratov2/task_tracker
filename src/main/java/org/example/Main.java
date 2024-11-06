package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.createNewTask(new Task(0,"Wake up", "wake up at 6:00"));
        manager.createNewTask(new Epic(1,"cook breakfast", "cook scramble eggs"));
        manager.createNewTask(new Subtask(2,"cook it", manager.getByID(1)));
        manager.createNewTask(new Epic(3,"go to work"));
        manager.createNewTask(new Subtask(4,"get a shower", manager.getByID(3)));
        manager.createNewTask(new Subtask(5,"dress up", manager.getByID(3)));

        manager.showData();

        manager.updateStatus(manager.getByID(0), "IN_PROGRESS");
        manager.updateStatus(manager.getByID(2), "IN_PROGRESS");
        manager.updateStatus(manager.getByID(4), "DONE");
        manager.updateStatus(manager.getByID(5), "IN_PROGRESS");

        for (Task task : manager.getTaskData().values()){
            System.out.println(task.getName() + " " + task.getStatus());
        }
        for (Task task : manager.getEpicData().values()){
            System.out.println(task.getName() + " " + task.getStatus());
        }
        for (Task task : manager.getSubtaskData().values()){
            System.out.println(task.getName() + " " + task.getStatus());
        }

        manager.deleteTask(3);
        System.out.println("  ");
        manager.showData();
    }
}
