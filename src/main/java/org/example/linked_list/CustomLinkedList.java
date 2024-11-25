package org.example.linked_list;

import org.example.Tasks.Task;

import java.util.ArrayList;

public class CustomLinkedList {
    private Node head;
    private Node tail;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void linkLast(Task data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPref(tail);
            tail = newNode;
        }
    }

    public void removeNode(Node node) {
        if (node == null) return;

        if (node == head) {
            head = node.getNext();
            if (head != null) {
                head.setPref(null);
            }
        }
        else if (node == tail) {
            tail = node.getPref();
            if (tail != null) {
                tail.setNext(null);
            }
        }
        else {
            Node prev = node.getPref();
            Node next = node.getNext();

            if (prev != null) prev.setNext(next);
            if (next != null) next.setPref(prev);
        }
    }

    public Node getTail() {
        return tail;
    }
}
