package Client;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             PrintWriter writer = new PrintWriter(output, true);
             Scanner scanner = new Scanner(System.in)) {


            String options = reader.readLine();
            System.out.println(options);


            System.out.println("Available voting options: \n Option A \n Option B \n Option C");
            System.out.println("Enter your vote:");
            String vote = scanner.nextLine();


            writer.println(vote);

            System.out.println("Server says: Vote cast successfully for Option "+vote);

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
