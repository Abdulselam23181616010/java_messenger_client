import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

//Kullancımızın ana işlemleri burda gerçeleşecek
public class Client {
    //Hassa içerikler için config'ten veriler çekelim
    private static Properties prop = ConfigHandler.use();

    //Kullancı arayüzleri çağıralım
    private  LoginFrame loginUI = new LoginFrame();
    private  UyeOlFrame uyeolUI = new UyeOlFrame();
    private  ChatFrame chatUI = new ChatFrame();

    //Sunucuya bağlanmak için değerleri belirtelim
    private static final String SERVER_ADDRESS = prop.getProperty("SERVER_ADRESS","");
    private static final int SERVER_PORT = Integer.valueOf(prop.getProperty("SERVER_PORT"));

    //Output-input, bağlanacak Socket ve kullancı bilgileri de lazım olacak
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
                    out.flush();
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
                    //Yanlışıkla butonun basılmaması için butonu bir süreliğine kapatalım
                    chatUI.getSendButton().setEnabled(false);

                    // Mesajı formatlayarak gösterelim
                    LocalDateTime currentTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = currentTime.format(formatter);
                    String string = user.getUsername() + "[" + formattedDateTime + "]: " + message + "\n";
                    SwingUtilities.invokeLater(() -> chatUI.writeMessageArea(string));
                    SwingUtilities.invokeLater(() -> chatUI.setMessageField(""));

                    try {
                        Gonderi gonderi = new Gonderi(3, new Mesaj(user.getUsername(), message));
                        gonder(gonderi);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //<işlemimiz bittiğine göre butonu açabiliriz
                    SwingUtilities.invokeLater(() -> chatUI.getSendButton().setEnabled(true));
                }
            }
        });

        chatUI.addGecmissListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatUI.getSendButton().setEnabled(false);
                try {
                    Gonderi gonderi = new Gonderi(4, new Mesaj(user.getIsim(), null));
                    gonder(gonderi);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                SwingUtilities.invokeLater(() -> chatUI.getSendButton().setEnabled(true));
            }

        });


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
                //Gönderileri sürekli okumamız için while loop kullanalım
                while (true) {
                    String serverResponseString = (String) this.in.readObject();
                    Gonderi serverResponse = SifrelemeClient.cevir(serverResponseString);

                    //Gelen sunucu döndüye göre işlem yapalım(daha fazla bilgi için README okuyun)
                    if (serverResponse != null) {
                        switch (serverResponse.getResponseCode()) {
                            case 10:
                                Popup.showPopup(loginUI,"Giris yapılamadı");
                                break;
                            case 11:
                                System.out.println("oldu");
                                loginUI.setVisible(false);
                                chatUI.setVisible(true);
                                break;
                            case 20:
                                Popup.showPopup(uyeolUI,"Kullancı oluşturamadı");
                                break;
                            case 21:
                                Popup.showPopup(uyeolUI,"Kullancı oluşturuldu");
                                uyeolUI.setVisible(false);
                                loginUI.setVisible(true);
                                break;
                            case 31:
                                new Thread(() -> {
                                    // Create a readable message from the `Mesaj` object
                                    Mesaj mesaj = serverResponse.getMesaj();
                                    String string = mesaj.getGonderici() + "[" + mesaj.getTime() + "]: " + mesaj.getMesaj() + "\n";
                                    chatUI.writeMessageArea(string);
                                    chatUI.setMessageField(""); // Clear the input field
                                }).start();
                                break;
                            default:
                                System.out.println("Hata oluştu");
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

    }

    //YUkarıda kullanacağımız gonderi metodu oluşturalım
    public void gonder(Gonderi gonderi) {
        try {
            String responseString = SifrelemeClient.sifrele(gonderi);
            out.writeObject(responseString);
            out.flush();

        } catch (Exception e)
            {e.printStackTrace();}


    }

}