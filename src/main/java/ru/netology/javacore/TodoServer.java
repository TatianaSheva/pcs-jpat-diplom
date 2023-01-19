package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private int port;
    private Todos todos;
    private Gson gson = new Gson();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())) {

                    // Принимаем запрос
                    String request = in.readLine();

                    // Конвертируем
                    Commands commands = gson.fromJson(request, Commands.class);
                    switch (commands.type) {
                        case ADD:
                            todos.addTask(commands.task);
                            break;
                        case REMOVE:
                            todos.removeTask(commands.task);
                            break;

                    }
                    // Запрашиваем результат и отправляем его клиенту
                    String result = todos.getAllTasks();
                    out.println(result);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

/**
 * Внутренний класс для разбора JSON
 */

class Commands {

    Operation type;
    String task;
}

