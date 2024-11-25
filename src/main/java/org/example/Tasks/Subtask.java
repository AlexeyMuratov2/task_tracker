package org.example.Tasks;


import org.example.Status;
import org.example.TaskType;

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

    public Subtask(int id, String name, Status status, String description, Task father) {
        super(id, name, status, description);
        this.epic = (Epic) father;
    }

    public Task getEpic() {
        return epic;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append(super.getId()).append(",")
                .append(TaskType.SUBTASK).append(",")
                .append(super.getName()).append(",")
                .append(super.getStatus()).append(",")
                .append(super.getDescription()).append(",")
                .append(epic.getId());
        return ans.toString();
    }
}
