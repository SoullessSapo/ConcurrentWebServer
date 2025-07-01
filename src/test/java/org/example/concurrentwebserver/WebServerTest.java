package org.example.concurrentwebserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebServerTest {

    @Test
    public void pruebaConcurrente() throws InterruptedException {
        int clientes = 5;
        ExecutorService executor = Executors.newFixedThreadPool(clientes);
        CountDownLatch latch = new CountDownLatch(clientes);

        Runnable tarea = () -> {
            try (Socket socket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println("GET / HTTP/1.1");
                out.println("Host: localhost");
                out.println("");

                String response = in.readLine();
                assertTrue(response.contains("HTTP/1.1 200 OK"));

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        };

        for (int i = 0; i < clientes; i++) {
            executor.submit(tarea);
        }

        latch.await();
        executor.shutdown();
    }
}
