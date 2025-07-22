import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Connected to chat server.");

            new Thread(new ReadThread(socket)).start();
            new Thread(new WriteThread(socket)).start();

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}

class ReadThread implements Runnable {
    private BufferedReader reader;

    public ReadThread(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            System.out.println("Disconnected from server.");
        }
    }
}

class WriteThread implements Runnable {
    private PrintWriter writer;
    private BufferedReader console;

    public WriteThread(Socket socket) throws IOException {
        writer = new PrintWriter(socket.getOutputStream(), true);
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            String msg;
            while ((msg = console.readLine()) != null) {
                writer.println(msg);
            }
        } catch (IOException e) {
            System.out.println("Error sending message.");
        }
    }
}
