import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UyeOlFrame extends JFrame {
    //İlk önce kullancıyı kaydetmek için kullanacağımız field ve butonları oluşturalım
    JTextField isimField = new JTextField();
    JTextField soyisimField = new JTextField();
    JTextField usernameField = new JTextField();
    JTextField sifreField = new JTextField();
    JButton uyeOlButonu = new JButton("Üye ol");
    JButton geriGit = new JButton("Geri git");

    public UyeOlFrame(){
        this.setTitle("Üye ol");
        this.setLocation(450, 200);
        this.setSize(500, 500);
        this.setLayout(null);

        //Labellreimizi oluşturup biçimlendirelim
        JLabel uyeOlLabel  = new JLabel("ÜYE OL");
        uyeOlLabel.setFont(new Font("Arial", Font.BOLD, 30));
        uyeOlLabel.setLocation(200,70);
        uyeOlLabel.setSize(150, 50);

        JLabel isimLabel  = new JLabel("isim ");
        isimLabel.setFont(new Font("Arial", Font.BOLD, 15));
        isimLabel.setLocation(130,150);
        isimLabel.setSize(150, 50);

        JLabel  soyisimlabel = new JLabel("soyisim  ");
        soyisimlabel.setFont(new Font("Arial", Font.BOLD, 15));
        soyisimlabel.setLocation(130,200);
        soyisimlabel.setSize(150, 50);

        JLabel usernameLabel = new JLabel("kullanıcı isim");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        usernameLabel.setLocation(130,250);
        usernameLabel.setSize(150, 50);

        JLabel sifrelabel  = new JLabel("şifre ");
        sifrelabel.setFont(new Font("Arial", Font.BOLD, 15));
        sifrelabel.setLocation(130,300);
        sifrelabel.setSize(150, 50);


        //Fieldleri biçimlendirelim
        isimField.setSize(130, 20);
        isimField.setLocation(250,165);
        isimField.setFont(new Font("Arial", Font.BOLD, 15));

        soyisimField.setSize(130, 20);
        soyisimField.setLocation(250,212);
        soyisimField.setFont(new Font("Arial", Font.BOLD, 15));

        usernameField.setSize(130, 20);
        usernameField.setLocation(250,262);
        usernameField.setFont(new Font("Arial", Font.BOLD, 15));

        sifreField.setSize(130, 20);
        sifreField.setLocation(250,312);
        sifreField.setFont(new Font("Arial", Font.BOLD, 15));

        uyeOlButonu.setFont(new Font("Arial", Font.BOLD, 15));
        uyeOlButonu.setLocation(250,370);
        uyeOlButonu.setSize(150, 25);


        Color eskirenk1  = uyeOlButonu.getBackground();
        uyeOlButonu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                uyeOlButonu.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                uyeOlButonu.setBackground(eskirenk1);
            }
        });

        geriGit.setFont(new Font("Arial", Font.BOLD, 15));
        geriGit.setLocation(85,370);
        geriGit.setSize(150, 25);


        Color eskirenk2  = geriGit.getBackground();

        geriGit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                geriGit.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                geriGit.setBackground(eskirenk2);
            }
        });

        //Oluşturduğumuz nesneleri Jframe'e ekleyelim
        this.add(uyeOlLabel);
        this.add(isimLabel);
        this.add(soyisimlabel);
        this.add(usernameLabel);
        this.add(sifrelabel);
        this.add(isimField);
        this.add(soyisimField);
        this.add(usernameField);
        this.add(sifreField);
        this.add(uyeOlButonu);
        this.add(geriGit);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addUyeOlListener(ActionListener listener) {
        uyeOlButonu.addActionListener(listener);

    }
    public void addGeriGitListener(ActionListener listener) {
        geriGit.addActionListener(listener);

    }

    //Fieldlerdeki getterleirmizi oluşturalım
    public String getIsim() {
        return isimField.getText();
    }

    public String getSoyisim() {
        return new String(soyisimField.getText());
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getSifre() {
        return new String(sifreField.getText());
    }
}





