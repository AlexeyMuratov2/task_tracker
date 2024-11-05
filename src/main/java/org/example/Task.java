package org.example;


import java.util.Arrays;

public class Task {
    private final int id;
    private final String name;
    private final String description;
    private String status;

    public Task(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = "NEW";
    }

    public Task(int id, String name) {
        this.name = name;
        this.description = "";
        this.id = id;
        this.status = "NEW";
    }

    public void setStatus(String status) {
        if (!Arrays.asList(new String[]{"NEW", "IN_PROGRESS", "DONE"}).contains(status)) {
            throw new WrongStatusException("Status must be NEW, IN_PROGRESS or DONE");
        }
        this.status = status;
    }

    public String getStatus() {
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
