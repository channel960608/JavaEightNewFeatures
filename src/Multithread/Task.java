package Multithread;

import java.util.UUID;

public class Task {

    private String id;

    public Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }



}
