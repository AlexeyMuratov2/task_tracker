package org.example;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private static List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task){
        history.add(task);
        if (history.size() == 11) {
            history.remove(0);
        }
    }

    public List<Task> getHistory() {
        return history;
    }
}
