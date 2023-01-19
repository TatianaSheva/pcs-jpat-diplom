package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static String pickRandom() {
        String[] task = {"Купить хлеб", "Поздравить маму с днем реждения", "Позвонить Илье", "Убрать дом"};
        Random random = new Random();
        int index = random.nextInt(task.length);
        return task[index];
    }

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket("localhost", 8989);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"task #" + pickRandom() + "\" }");
            System.out.println(in.readLine());
        }
    }
}
