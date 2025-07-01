package org.example.concurrentwebserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             OutputStream outStream = socket.getOutputStream();
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(outStream))) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) return;

            System.out.println("Request: " + requestLine);

            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String path = tokens[1];

            if (!method.equals("GET")) {
                out.write("HTTP/1.1 405 Method Not Allowed\r\n\r\n");
                out.flush();
                return;
            }

            String filePath = "src/main/resources" + (path.equals("/") ? "/index.html" : path);
            File file = new File(filePath);

            if (file.exists()) {
                String contentType = Files.probeContentType(Paths.get(filePath));
                byte[] fileBytes = Files.readAllBytes(file.toPath());

                out.write("HTTP/1.1 200 OK\r\n");
                out.write("Content-Type: " + contentType + "\r\n");
                out.write("Set-Cookie: sessionId=abc123; Path=/;\r\n");
                out.write("Content-Length: " + fileBytes.length + "\r\n\r\n");
                out.flush();

                outStream.write(fileBytes);
                outStream.flush();

            } else {
                out.write("HTTP/1.1 404 Not Found\r\n\r\n");
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
