package org.example.Tasks;


import org.example.Status;

public class Task {
    private final int id;
    private final String name;
    private final String description;
    private Status status;

    public Task(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = Status.NEW;
    }

    public Task(int id, String name) {
        this.name = name;
        this.description = "";
        this.id = id;
        this.status = Status.NEW;
    }

    public Task(int id, String name, Status status, String description){
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
