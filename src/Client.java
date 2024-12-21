import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private User user;

    public static void clientCalistir() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to the chat server!");

            // İletişim için input/output oluşturalım
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Gelen mesajları almak için Threat oluşturalım
            new Thread(() -> {
                try {
                    String serverResponseString = (String)in.readObject();
                    Response serverResponse = SifrelemeClient.cevir(serverResponseString);
                    while (serverResponse != null) {
                        ResponseSolver responseSolver = (ResponseSolver)serverResponse;
                        responseSolver.solve();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            // Read messages from the console and send to the server
            Scanner scanner = new Scanner(System.in);
            String userInput;
            while (true) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}