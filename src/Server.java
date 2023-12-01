import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    // Default port
    private static final int DEFAULT_PORT = 1234;

    // Array list to hold the client's socket channel.
    private static ArrayList<Socket> clientSockets = new ArrayList<>();

    // BufferedReader to read input from the client.
    private static BufferedReader bufferedReader;

    // Server constructor.
    public Server() {
        // Construct a ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            System.out.println("Server is listening on port " + serverSocket.getLocalPort());

            // Create an infinite loop for the server to function.
            while (true) {
                // Use accept() method of the ServerSocket to accept a new client socket.
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected with IP: " + clientSocket.getInetAddress());

                // Add the client socket to the ArrayList.
                clientSockets.add(clientSocket);

                // Use the bufferedReader to read the client's message.
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Start a new thread for the current client.
                new ClientHandler(clientSocket, bufferedReader).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}