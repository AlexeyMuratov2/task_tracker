package org.example.manager;


import org.example.Tasks.Epic;
import org.example.Status;
import org.example.Tasks.Subtask;
import org.example.Tasks.Task;
import org.example.exceptions.ManagerSaveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    protected final HashMap<Integer, Task> taskData = new HashMap<>();
    protected final HashMap<Integer, Epic> epicData = new HashMap<>();
    protected final HashMap<Integer, Subtask> subtaskData = new HashMap<>();
    protected final HistoryManager history = Managers.getDefaultHistory();

    @Override
    public void deleteData() throws ManagerSaveException {
        taskData.clear();
        epicData.clear();
        subtaskData.clear();
    }

    @Override
    public List<Task> getTaskHistory(){
        return  history.getHistory();
    }

    @Override
    public Task getByID(int id) {
        if (taskData.containsKey(id)) {
            history.add(taskData.get(id));
            return taskData.get(id);
        }
        if (epicData.containsKey(id)) {
            history.add(epicData.get(id));
            return epicData.get(id);
        }
        if (subtaskData.containsKey(id)) {
            history.add(subtaskData.get(id));
            return subtaskData.get(id);
        }
        return null;
    }

    @Override
    public void createNewTask(Task task) throws ManagerSaveException {
        if (task instanceof Subtask subtask) {
            Epic epic = (Epic) subtask.getEpic();
            epic.updateSubtasks(subtask);
            subtaskData.put(task.getId(), (Subtask) task);
        } else if (task instanceof Epic) {
            epicData.put(task.getId(), (Epic) task);
        } else {
            taskData.put(task.getId(), task);
        }
    }

    @Override
    public void updateTask(Task task) throws ManagerSaveException {
        if (task instanceof Subtask) {
            subtaskData.put(task.getId(), (Subtask) task);
        } else if (task instanceof Epic) {
            epicData.put(task.getId(), (Epic) task);
        } else {
            taskData.put(task.getId(), task);
        }
    }

    @Override
    public List<Task> getSubtasksByEpic(Task epic) {
        List<Task> ans = new ArrayList<>();
        for (Task subtask : subtaskData.values()) {
            if (((Subtask) subtask).getEpic().equals(epic)) {
                ans.add(subtask);
            }
        }
        return ans;
    }

    @Override
    public void updateStatus(Task task, Status newStatus) throws ManagerSaveException {
        task.setStatus(newStatus);
        if (task instanceof Subtask) {
            Epic buffer = (Epic) ((Subtask) task).getEpic();
            buffer.updateEpicStatus();
        }
    }

    @Override
    public void deleteTask(int id) throws ManagerSaveException {
        if (taskData.containsKey(id)) {
            taskData.remove(id);
        }
        if (epicData.containsKey(id)) {
            Epic epick = (Epic) epicData.get(id);
            for (Task subtask : epick.getSubtasks()) {
                subtaskData.remove(subtask.getId());
            }
            epicData.remove(id);
        }
        if (subtaskData.containsKey(id)) {
            subtaskData.remove(id);
        }
    }

    @Override
    public HashMap<Integer, Epic> getEpicData() {
        return epicData;
    }

    @Override
    public HashMap<Integer, Task> getTaskData() {
        return taskData;
    }

    @Override
    public HashMap<Integer, Subtask> getSubtaskData() {
        return subtaskData;
    }

    public HistoryManager getHistoryManager(){
        return history;
    }
}
