package org.example.manager;

import java.io.IOException;

public class Managers {
    private Managers(){}
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
    public static FileBackedTasksManager getBacked() throws IOException {return new FileBackedTasksManager();}
}
