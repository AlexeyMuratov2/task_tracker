package org.example.manager;

import org.example.linked_list.CustomLinkedList;
import org.example.linked_list.Node;
import org.example.Tasks.Task;

import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private HashMap<Integer, Node> history = new HashMap<>();
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
        return history.values().stream()
                .map(node -> node.getData())
                .toList();
    }

}
