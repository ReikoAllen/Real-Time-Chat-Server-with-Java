import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader bufferedReader;

    // Constructor for the ClientHandler class.
    public ClientHandler(Socket clientSocket, BufferedReader bufferedReader) {
        this.clientSocket = clientSocket;
        this.bufferedReader = bufferedReader;
    }

    // Override the run() method of the Thread class.
    @Override
    public void run() {
        try {
            String inputLine;

            // Use a while loop to keep reading until we receive a message that starts with 'exit'.
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(clientSocket.getInetAddress() + " : " + inputLine);

                // Exit the chat if the client types 'exit'.
                if (inputLine.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("ClientHandler error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Failed to close client socket: " + e.getMessage());
            }
        }
    }
}