package org.example;

import java.util.HashMap;
import java.util.List;

public interface TaskManager {
    List<Task> getTaskHistory();
    void deleteData();
    Task getByID(int id);
    void createNewTask(Task task);
    void updateTask(Task task);
    List<Task> getSubtasksByEpic(Task epic);
    void updateStatus(Task task, Status newStatus);
    void deleteTask(int id);
    HashMap<Integer, Task> getEpicData();
    HashMap<Integer, Task> getTaskData();
    HashMap<Integer, Task> getSubtaskData();
}