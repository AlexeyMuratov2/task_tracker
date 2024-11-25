package org.example.manager;

import org.example.Status;
import org.example.Tasks.Epic;
import org.example.Tasks.Subtask;
import org.example.Tasks.Task;
import org.example.exceptions.ManagerSaveException;

import java.util.HashMap;
import java.util.List;

public interface TaskManager {
    List<Task> getTaskHistory();
    void deleteData() throws ManagerSaveException;
    Task getByID(int id);
    void createNewTask(Task task) throws ManagerSaveException;
    void updateTask(Task task) throws ManagerSaveException;
    List<Task> getSubtasksByEpic(Task epic);
    void updateStatus(Task task, Status newStatus) throws ManagerSaveException;
    void deleteTask(int id) throws ManagerSaveException;
    HashMap<Integer, Epic> getEpicData();
    HashMap<Integer, Task> getTaskData();
    HashMap<Integer, Subtask> getSubtaskData();
}