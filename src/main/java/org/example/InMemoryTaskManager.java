package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> taskData = new HashMap<>();
    private final HashMap<Integer, Task> epicData = new HashMap<>();
    private final HashMap<Integer, Task> subtaskData = new HashMap<>();
    private final HistoryManager history = Managers.getDefaultHistory();

    @Override
    public void deleteData() {
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

    @Override
    public void updateTask(Task task) {
        if (task instanceof Subtask) {
            subtaskData.put(task.getId(), task);
        } else if (task instanceof Epic) {
            epicData.put(task.getId(), task);
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
    public void updateStatus(Task task, Status newStatus) {
        task.setStatus(newStatus);
        if (task instanceof Subtask) {
            Epic buffer = (Epic) ((Subtask) task).getEpic();
            buffer.updateEpicStatus();
        }
    }

    @Override
    public void deleteTask(int id) {
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
    public HashMap<Integer, Task> getEpicData() {
        return epicData;
    }

    @Override
    public HashMap<Integer, Task> getTaskData() {
        return taskData;
    }

    @Override
    public HashMap<Integer, Task> getSubtaskData() {
        return subtaskData;
    }
}
