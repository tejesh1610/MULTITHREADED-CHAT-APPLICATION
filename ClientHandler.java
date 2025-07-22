import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Set<ClientHandler> clients;
    private String clientName;

    public ClientHandler(Socket socket, Set<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Client Handler error: " + e.getMessage());
        }
    }

    public void run() {
        try {
            output.println("Enter your name:");
            clientName = input.readLine();
            broadcast("User " + clientName + " has joined the chat.");

            String msg;
            while ((msg = input.readLine()) != null) {
                broadcast(clientName + ": " + msg);
            }
        } catch (IOException e) {
            System.out.println(clientName + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            clients.remove(this);
            broadcast("User " + clientName + " has left the chat.");
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.output.println(message);
        }
    }
}
