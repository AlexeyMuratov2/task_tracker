package org.example.Tasks;


import org.example.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Subtask> subtasks = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description);
    }

    public Epic(int id, String name) {
        super(id, name);
    }

    public Epic(int id, String name, Status status, String description){
        super(id, name, status, description);
    }

    public void updateEpicStatus() {
        int newCount = 0;
        int inProgressCount = 0;
        int doneCount = 0;
        for (Subtask task : subtasks) {
            if (task.getStatus() == Status.NEW) {
                newCount += 1;
            } else if (task.getStatus() == Status.IN_PROGRESS) {
                inProgressCount += 1;
            } else {
                doneCount += 1;
            }
        }
        if (newCount == subtasks.size()) {
            this.setStatus(Status.NEW);
        } else if (inProgressCount == subtasks.size() || newCount == 0) {
            this.setStatus(Status.IN_PROGRESS);
        } else if (doneCount == subtasks.size()){
            this.setStatus(Status.DONE);
        }
    }
    public List<Subtask> getSubtasks(){
        return this.subtasks;
    }
    public void updateSubtasks(Subtask task){
        this.subtasks.add(task);
    }
}
