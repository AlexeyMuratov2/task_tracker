package org.example;

import org.example.Tasks.Epic;
import org.example.Tasks.Subtask;
import org.example.Tasks.Task;
import org.example.exceptions.ManagerSaveException;
import org.example.manager.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileBackedTasksManager manager = Managers.getBacked();
        HistoryManager historyManager = manager.getHistoryManager();
        manager.loadFromFile("data.csv");

        manager.createNewTask(new Task(4,"Task1", Status.NEW, "first task"));
        manager.createNewTask(new Epic(5,"Epic1", "Description epic1"));
        manager.createNewTask(new Subtask(6,"Subtask1", Status.NEW, "subtask", manager.getByID(5)));

        manager.getByID(4);
        manager.getByID(2);

        System.out.println("Loaded tasks: " + manager.getTaskData());
        System.out.println("Loaded epics: " + manager.getEpicData());
        System.out.println("Loaded subtask: " + manager.getSubtaskData());


        manager.updateStatus(manager.getByID(6), Status.IN_PROGRESS);


    }
}
