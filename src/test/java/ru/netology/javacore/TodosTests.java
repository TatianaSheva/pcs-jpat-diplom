package ru.netology.javacore;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
import java.net.Socket;

public class TodosTests {
    Todos todos = new Todos();

    @BeforeEach
    public void openConnection() {
        try {
            Socket socket = new Socket("localhost", 8989);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Проверка метода добавления задачи")
    public void TestAddTask() throws IOException {
        todos.addTask("Первая");
        String result = todos.getAllTasks();
        Assertions.assertEquals("Первая", result);
    }


    @Test
    @DisplayName("Проверка метода удаления задачи")
    public void TestRemoveTask() throws IOException {
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.removeTask("Первая");
        String result = todos.getAllTasks();
        Assertions.assertEquals("Вторая", result);
    }

    @Test
    @DisplayName("Метод проверки сортировки")
    public void theMethodReturnsAllTasksSeparatedBySpaceInSortedOrder() {
        todos.addTask("Акробатика");
        todos.addTask("Учёба");
        todos.addTask("Сон");
        String result = todos.getAllTasks();
        Assertions.assertEquals("Акробатика Сон Учёба", result);
    }
}
