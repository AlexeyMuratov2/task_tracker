package org.example.manager;

import org.example.Status;
import org.example.TaskType;
import org.example.Tasks.Epic;
import org.example.Tasks.Subtask;
import org.example.Tasks.Task;
import org.example.exceptions.ManagerSaveException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private Path path;

    public void loadFromFile(String file) throws IOException {
        path = Path.of(file);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHistory = false;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    isHistory = true;
                    continue;
                }

                if (!isHistory) {
                    Task task = fromString(line);//2
                    if (task != null) {
                        this.createNewTask(task);
                    }
                } else {
                    historyFromString(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    private void save() throws ManagerSaveException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("id,type,name,status,description,epic\n");

            for (Task task : super.getTaskData().values()) {
                writer.write(toString(task) + "\n");
            }
            for (Task task : super.getEpicData().values()) {
                writer.write(toString(task) + "\n");
            }
            for (Subtask task : super.getSubtaskData().values()) {
                writer.write(task.toString() + "\n");
            }

            writer.write("\n");
            writer.write(historyToString(super.history) + "\n");
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private String historyToString(HistoryManager manager) {
        StringBuilder history = new StringBuilder();
        for (Task task : manager.getHistory()) {
            history.append(task.getId());
            history.append(",");
        }
        if (history.length() > 0) {
            history.deleteCharAt(history.length() - 1);
        }
        return history.toString();
    }

    private void historyFromString(String value) {
        if (value != null && !value.isEmpty()) {
            String[] historyId = value.split(",");
            Arrays.stream(historyId).forEach(System.out::println);
            for (String id : historyId) {
                super.getByID(Integer.parseInt(id.trim()));
            }
        }
    }


    private String toString(Task task) {
        StringBuilder ans = new StringBuilder();
        ans.append(task.getId());
        ans.append(',');
        if (task instanceof Subtask) {
            ans.append(TaskType.SUBTASK);
        } else if (task instanceof Epic) {
            ans.append(TaskType.EPIC);
        } else {
            ans.append(TaskType.TASK);
        }
        ans.append(',');
        ans.append(task.getName());
        ans.append(',');
        ans.append(task.getStatus());
        ans.append(',');
        ans.append(task.getDescription());
        ans.append(',');
        if (task instanceof Subtask) {
            ans.append(((Subtask) task).getEpic());
        }
        return ans.toString();
    }

    private Task fromString(String value) {
        String[] taskData = value.split(",");
        int id = Integer.parseInt(taskData[0]);
        TaskType type = TaskType.valueOf(taskData[1]);
        String name = taskData[2];
        Status status = Status.valueOf(taskData[3]);
        String description = taskData[4];
        if (type == TaskType.SUBTASK) {
            return new Subtask(id, name, status, description, super.epicData.get(Integer.valueOf(taskData[5])));
        } else if (type == TaskType.EPIC) {
            return new Epic(id, name, status, description);
        } else {
            return new Task(id, name, status, description);
        }

    }

    @Override
    public void deleteData() throws ManagerSaveException {
        super.deleteData();
        save();
    }


    @Override
    public void createNewTask(Task task) throws ManagerSaveException {
        super.createNewTask(task);
        save();
    }

    @Override
    public void updateTask(Task task) throws ManagerSaveException {
        super.updateTask(task);
        save();
    }


    @Override
    public void updateStatus(Task task, Status newStatus) throws ManagerSaveException {
        super.updateStatus(task, newStatus);
        save();
    }

    @Override
    public void deleteTask(int id) throws ManagerSaveException {
        super.deleteTask(id);
        save();
    }
}
