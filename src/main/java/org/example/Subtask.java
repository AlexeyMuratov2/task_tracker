package org.example;


public class Subtask extends Task {
    private Epic epic;

    public Subtask(int id, String name, String description, Task father) {
        super(id, name, description);
        this.epic = (Epic) father;
    }

    public Subtask(int id, String name, Task father) {
        super(id, name);
        this.epic = (Epic) father;
    }

    public Task getEpic() {
        return epic;
    }
}
