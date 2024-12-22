import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.http.WebSocket;

//Kullancının giriş yapmak istediği zaman karşıya çıkan arayüzdür
public class LoginFrame extends JFrame {
    //Başta text fieldler, giriş ve uye olma butonlarını ekleyelim
    private JTextField usernameField = new JTextField();
    private JTextField sifreField = new JTextField();
    private JButton girisButonu = new JButton("giris yap");
    private JButton uyeOlButonu = new JButton ( "Üye ol");


    public LoginFrame(){

        this.setTitle(" Login ");
        this.setLocation(450, 200);
        this.setSize(500, 500);
        this.setLayout(null);

        //Labellerimizi oluşturalım
        JLabel girisYapLabel  = new JLabel("GİRİŞ YAP");
        girisYapLabel.setFont(new Font("Arial", Font.BOLD, 20));
        girisYapLabel.setLocation(200,100);
        girisYapLabel.setSize(150, 50);

        JLabel isimLabel  = new JLabel("kullanıcı ismini giriniz ");
        isimLabel.setFont(new Font("Arial", Font.BOLD, 15));
        isimLabel.setLocation(85,175);
        isimLabel.setSize(300, 50);

        JLabel sifreLabel  = new JLabel("şifrenizi giriniz");
        sifreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        sifreLabel.setLocation(85,210);
        sifreLabel.setSize(200, 50);

        //Text fieldleri de biçimlendirelim
        usernameField.setFont(new Font("Arial", Font.BOLD, 15));
        usernameField.setLocation(285,190);
        usernameField.setSize(150, 20);

        sifreField.setFont(new Font("Arial", Font.BOLD, 15));
        sifreField.setLocation(285,225);
        sifreField.setSize(150, 20);



        //giriş butonunun stilleri
        girisButonu.setSize(130, 35);
        girisButonu.setLocation(120, 325);

        Color eskirenk2 = girisButonu.getBackground();
        girisButonu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                girisButonu.setBackground(Color.pink);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                girisButonu.setBackground(eskirenk2);
            }
        });


        //Uye ol butonunun stilleri
        uyeOlButonu.setSize(150, 35);
        uyeOlButonu.setLocation(280, 325);

        Color eskirenk = uyeOlButonu.getBackground();
        uyeOlButonu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                uyeOlButonu.setBackground(Color.orange);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                uyeOlButonu.setBackground(eskirenk);
            }
        });

        //Şimdi oluşturduğumuz nesneleri JFrame ekleyelim
        this.add(girisYapLabel);
        this.add(isimLabel);
        this.add(sifreLabel);
        this.add(usernameField);
        this.add(sifreField);
        this.add(girisButonu);
        this.add(uyeOlButonu);

        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addLoginListener(ActionListener listener) {
        girisButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsername();
                String password = getSifre();

            }
        });
    }
    //Gerekli olan getterler
    public String getUsername() {
        return usernameField.getText();
    }

    public String getSifre() {
        return new String(sifreField.getText());
    }

    //Bu butona bastığında oluşan işlemleri sonra kullanılacak olacağımız metodları oluşturalım.
    public void addGirisListener(ActionListener listener) {
        girisButonu.addActionListener(listener);

    }
    public void addUyeOlListener(ActionListener listener) {
        uyeOlButonu.addActionListener(listener);
    }


}



