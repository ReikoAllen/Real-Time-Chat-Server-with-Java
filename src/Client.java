import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        // Create BufferedReader to read input from the user
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

        // Create PrintWriter to send output to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String userInput;
        while ((userInput = userInputReader.readLine()) != null) {
            out.println(userInput);

            // Exit the chat if the user types 'exit'.
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }
        }

        socket.close();
    }
}
