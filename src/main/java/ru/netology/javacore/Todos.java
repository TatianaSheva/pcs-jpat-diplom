package ru.netology.javacore;

import java.util.*;

public class Todos {
    /**
     * Набор задач, добавленных в систему.
     * Каждая задача уникальна.
     * Задача - task -> String.
     */

    private static final int MAXIMUM_TASKS = 7;
    //Список задач хранится в toDoList
    private TreeSet<String> toDoList = new TreeSet<>();
    //История произврдимых операций хранится в operationStack
    private Deque<Pair> operationStack = new LinkedList<>();


    public void addTask(String task) {
        if (toDoList.size() < MAXIMUM_TASKS) {
            operationStack.push(new Pair(Operation.ADD, task));
            toDoList.add(task);
        }
    }

    public void removeTask(String task) {
        if (toDoList.contains(task)) {
            operationStack.push(new Pair(Operation.REMOVE, task));
            toDoList.remove(task);
        }
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : toDoList) {
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * Вспомогательный класс для хранения в стеке пар: операция - задача
     */

    class Pair {

        Operation operation;
        String task;

        public Pair(Operation operation, String task) {
            this.operation = operation;
            this.task = task;
        }
    }
}