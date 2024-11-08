package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();
        manager.createNewTask(new Task(0, "Wake up", "wake up at 6:00"));
        manager.createNewTask(new Epic(1, "cook breakfast", "cook scramble eggs"));
        manager.createNewTask(new Epic(2, "go to work"));
        manager.createNewTask(new Subtask(3, "get a shower", manager.getByID(2)));
        manager.createNewTask(new Subtask(4, "eat", manager.getByID(2)));
        manager.createNewTask(new Subtask(5, "dress up", manager.getByID(2)));


        manager.getByID(1);
        List<Task> history = manager.getTaskHistory();
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println((history.size() - i) + ": " + history.get(i).getName());
        }
        System.out.println("");
        manager.getByID(1);
        manager.getByID(2);
        history = manager.getTaskHistory();
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println((history.size() - i) + ": " + history.get(i).getName());
        }
        System.out.println("");
        manager.getByID(3);
        manager.getByID(5);
        manager.getByID(5);
        history = manager.getTaskHistory();
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println((history.size() - i) + ": " + history.get(i).getName());
        }
        System.out.println("");
    }
}
