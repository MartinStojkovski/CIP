package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 9000;
    private static final Map<String, Integer> voteCounts = new HashMap<>();

    public static void main(String[] args) {

        voteCounts.put("A", 0);
        voteCounts.put("B", 0);
        voteCounts.put("C", 0);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Client connected: "+socket.getLocalAddress()+"-voted for ");


                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }


    private static class ClientHandler extends Thread {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }


        public void run() {
            try (
                    InputStream input = socket.getInputStream();
                    OutputStream output = socket.getOutputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    PrintWriter writer = new PrintWriter(output, true)
            ) {

                writer.println("Available voting options: \n Option A \n Option B \n Option C");
                String vote = reader.readLine();
                if (voteCounts.containsKey(vote)) {
                    voteCounts.put(vote, voteCounts.get(vote) + 1);
                    writer.println("Vote received for option " + vote);
                } else {
                    writer.println("Invalid vote. Please choose \n Option A \n Option B \n Option C");
                }


                System.out.println("Current vote counts: " + voteCounts);


                socket.close();
                System.out.println("Client connection closed.");
            } catch (IOException e) {
                System.out.println("Client handling error: " + e.getMessage());
            }
        }
    }
}
