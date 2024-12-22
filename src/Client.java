import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client {
    private  LoginFrame loginUI = new LoginFrame();
    private  UyeOlFrame uyeolUI = new UyeOlFrame();
    private  ChatFrame chatUI = new ChatFrame();
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;
    private  Socket socket;
    private  ObjectOutputStream out;
    private  ObjectInputStream in;
    private User user;

    //Client için Contructor
    public Client() {
        //İlk önce bizim için önemli olan socket oluşturalım ve sunucumuza bağlanalım
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to the chat server!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sonra UI'daki butonların çalışmasını ayarlayalım
        loginUI.addGirisListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                user = new User(loginUI.getUsername(), loginUI.getSifre());
                String userString = SifrelemeClient.userSifrele(user);
                out.writeObject(userString);
                out.flush(); // Ensure data is sent
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }});

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
                try {
                    user = new User(uyeolUI.getUsername(), uyeolUI.getSifre(), uyeolUI.getIsim(), uyeolUI.getSoyisim());
                    String userString = SifrelemeClient.userSifrele(user);
                    out.writeObject(userString);
                    out.flush(); // Ensure data is sent
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Popup.showPopup(uyeolUI,"Bir hata oluştu");
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

        chatUI.addSendListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatUI.getMessageField().getText();

                if (message != null && !message.isEmpty()) {
                    chatUI.getSendButton().setEnabled(false);

                    // Format and display the message
                    LocalDateTime currentTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = currentTime.format(formatter);
                    String string = "You (" + formattedDateTime + "): " + message + "\n";
                    SwingUtilities.invokeLater(() -> chatUI.writeMessageArea(string));
                    SwingUtilities.invokeLater(() -> chatUI.setMessageField(""));

                    // Run the network operation in a background thread
                    new Thread(() -> {
                        try {
                            Gonderi gonderi = new Gonderi(3, new Mesaj(user.username, message));
                            gonder(gonderi);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();

                    SwingUtilities.invokeLater(() -> chatUI.getSendButton().setEnabled(true));
                }
            }
        });;



    }

    //Şimdi aslında programın ana kısmını metod içine yazalım
    public  void clientCalistir() {
        try {
            // İletişim için input/output oluşturalım
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }




        // Gelen mesajları almak için Threat oluşturalım
        new Thread(() -> {
            try {
                String serverResponseString = (String)this.in.readObject();
                Gonderi serverResponse = SifrelemeClient.cevir(serverResponseString);

                boolean isPopupShown = false;
                while (serverResponse != null) {
                    switch (serverResponse.getResponseCode()){
                        case 10:
                            //hata popup
                            if (!isPopupShown){
                                Popup.showPopup(loginUI,"Giriş yapılamadı!");
                                isPopupShown = true;
                            }
                            break;
                        case 11:
                            loginUI.setVisible(false);
                            chatUI.setVisible(true);
                            break;
                        case 20:
                            //Hata popup
                            if (!isPopupShown) {
                                Popup.showPopup(uyeolUI, "Kullancı oluşturulmadı!");
                                isPopupShown = true;
                            }
                            break;
                        case 21:
                            if (!isPopupShown){
                                Popup.showPopup(uyeolUI,"Kullancı oluşturuldu!");
                                isPopupShown = true;
                            }
                            break;
                        case 31:
                            new Thread(() -> {
                                //mesaj nesnesini alıp ondan anlamlı mesaj stringi oluşturalım
                                Mesaj mesaj = serverResponse.getMesaj();
                                String string = mesaj.getGonderici() + "[" + mesaj.getTime() + "]: " + mesaj.getMesaj() + "\n";
                                //Sonra mesajı chate yazdıralım ve alanı temizleyelim
                                chatUI.writeMessageArea(string);
                                chatUI.setMessageArea("");

                            }).start();
                            break;

                        default:
                            System.out.println("Hata oluştu");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void gonder(Gonderi gonderi) {
        new Thread(() -> {
            synchronized (this) {
            try {
                String responseString = SifrelemeClient.sifrele(gonderi);
                out.writeObject(responseString);
                out.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }}).start();


    }

}