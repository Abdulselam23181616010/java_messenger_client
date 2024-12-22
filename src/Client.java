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

                if (message != null) {
                    chatUI.getSendButton().setEnabled(false);

                    // Şu anki tarih ve saati al ve biçimlendir
                    LocalDateTime currentTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Yıl-ay-gün saat:dakika:saniye
                    String formattedDateTime = currentTime.format(formatter);
                    // Mesajı kendi chatimize yazdıralım
                    chatUI.writeMessageArea("Sen (" + formattedDateTime + "): " + message + "\n");
                    System.out.println("tst");
                    chatUI.setMessageArea("");

                    new Thread(() -> {
                        //Sonra bu mesaje başka kullancılara gönderelim.
                        Gonderi gonderi = new Gonderi(3, new Mesaj(user.username, message));
                        gonder(gonderi);
                    }).start();

                }
            }
        });



    }

    //Şimdi aslında programın ana kısmını metod içine yazalım
    public  void clientCalistir() {
        // İletişim için input/output oluşturalım
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Gelen mesajları almak için Threat oluşturalım
        new Thread(() -> {
            try {
                String serverResponseString = (String)in.readObject();
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
                            //olumlu popup
                            if (!isPopupShown){
                                Popup.showPopup(uyeolUI,"Kullancı oluşturuldu!");
                                isPopupShown = true;
                            }
                        case 31:
                            //mesaj nesnesini alıp ondan anlamlı mesaj stringi oluşturalım
                            Mesaj mesaj = serverResponse.getMesaj();
                            String string = mesaj.getGonderici() + "[" + mesaj.getTime() + "]: " + mesaj.getMesaj() + "\n";

                            //Sonra mesajı chate yazdıralım ve alanı temizleyelim
                            chatUI.writeMessageArea(string);
                            chatUI.setMessageArea("");
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
        try {
            String gonderiString = SifrelemeClient.sifrele(gonderi);
            out.writeObject(gonderiString);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}