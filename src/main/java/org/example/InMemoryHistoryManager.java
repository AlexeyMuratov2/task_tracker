package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private HashMap<Integer, Node> history = new HashMap<Integer, Node>();
    private CustomLinkedList data = new CustomLinkedList();

    @Override
    public void add(Task task){
        if (history.containsKey(task.getId())) {
            data.removeNode(history.get(task.getId()));
            history.remove(task.getId());
        }
        data.linkLast(task);
        history.put(task.getId(), data.getTail());
    }

    @Override
    public void remove(int id) {
        if (history.containsKey(id)){
            data.removeNode(history.get(id));
            history.remove(id);
        }
    }

    public List<Task> getHistory() {
        return data.getTasks();
    }
}
