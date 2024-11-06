package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> taskData = new HashMap<>();
    private final HashMap<Integer, Task> epicData = new HashMap<>();
    private final HashMap<Integer, Task> subtaskData = new HashMap<>();

    public void deleteData() {
        taskData.clear();
        epicData.clear();
        subtaskData.clear();
    }

    public Task getByID(int id) {
        if (taskData.containsKey(id)) {
            return taskData.get(id);
        }
        if (epicData.containsKey(id)) {
            return epicData.get(id);
        }
        if (subtaskData.containsKey(id)) {
            return subtaskData.get(id);
        }
        return null;
    }

    public void createNewTask(Task task) {
        if (task instanceof Subtask subtask) {
            Epic epic = (Epic) subtask.getEpic();
            epic.updateSubtasks(subtask);
            subtaskData.put(task.getId(), task);
        } else if (task instanceof Epic) {
            epicData.put(task.getId(), task);
        } else {
            taskData.put(task.getId(), task);
        }
    }

    public void updateTask(Task task) {
        if (task instanceof Subtask) {
            subtaskData.put(task.getId(), task);
        } else if (task instanceof Epic) {
            epicData.put(task.getId(), task);
        } else {
            taskData.put(task.getId(), task);
        }
    }

    public List<Task> getSubtasksByEpic(Task epic) {
        List<Task> ans = new ArrayList<>();
        for (Task subtask : subtaskData.values()) {
            if (((Subtask) subtask).getEpic().equals(epic)) {
                ans.add(subtask);
            }
        }
        return ans;
    }

    public void showData() {
        for (Task task : taskData.values()) {
            System.out.println(task.getName());
        }
        for (Task task : epicData.values()) {
            System.out.println(task.getName());
        }
        for (Task task : subtaskData.values()) {
            System.out.println(task.getName());
        }
    }

    public void updateStatus(Task task, String newStatus) {
        task.setStatus(newStatus);
        if (task instanceof Subtask) {
            Epic buffer = (Epic) ((Subtask) task).getEpic();
            buffer.updateEpicStatus();
        }
    }

    public void deleteTask(int id) {
        if (taskData.containsKey(id)) {
            taskData.remove(id);
        }
        if (epicData.containsKey(id)) {
            Epic epick = (Epic) epicData.get(id);
            for (Task subtask : epick.getSubtasks()) {
                System.out.println(subtask.getName());
                subtaskData.remove(subtask.getId());
            }
            epicData.remove(id);
        }
        if (subtaskData.containsKey(id)) {
            subtaskData.remove(id);
        }
    }

    public HashMap<Integer, Task> getEpicData() {
        return epicData;
    }

    public HashMap<Integer, Task> getTaskData() {
        return taskData;
    }

    public HashMap<Integer, Task> getSubtaskData() {
        return subtaskData;
    }
}
