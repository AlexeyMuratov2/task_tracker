package org.example.linked_list;

import org.example.Tasks.Task;

public class Node {
    private Task data;
    private Node next;
    private Node pref;

    public Node(Task data){
        this.data = data;
        this.next = null;
        this.pref = null;
    }

    public Node getNext() {
        return next;
    }

    public Node getPref() {
        return pref;
    }

    public Task getData() {
        return data;
    }

    public void setNext(Node next1){
        this.next = next1;
    }

    public void setPref(Node pref1){
        this.pref = pref1;
    }
}
