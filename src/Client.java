import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private LoginFrame loginUI = new LoginFrame();
    private UyeOlFrame uyeolUI = new UyeOlFrame();
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private User user;

    public Client(){
        loginUI.addGirisListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = new User(loginUI.getUsername(), loginUI.getSifre());
                try {
                    out.writeObject(user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        loginUI.addUyeOlListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUI.setVisible(false);
                uyeolUI.setVisible(true);
            }
        });
        uyeolUI.addUyeOlListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = new User(uyeolUI.getUsername(), uyeolUI.getSifre(), uyeolUI.getIsim(), uyeolUI.getSoyisim());
                try {
                    out.writeObject(user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        uyeolUI.addGeriGitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uyeolUI.setVisible(false);
                loginUI.setVisible(true);
            }
        });
    }
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

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void gonder(Gonderi gonderi) {
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

            String gonderiString = SifrelemeClient.sifrele(gonderi);

            // Gelen mesajları almak için Threat oluşturalım
            new Thread(() -> {
                try {
                    out.writeObject(gonderiString);
                    String serverResponseString = (String) in.readObject();
                    Response serverResponse = SifrelemeClient.cevir(serverResponseString);
                    while (serverResponse != null) {
                        ResponseSolver responseSolver = (ResponseSolver) serverResponse;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}