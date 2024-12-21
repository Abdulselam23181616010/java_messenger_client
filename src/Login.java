import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

    public static void loginPage() {

        JFrame loginFrame  = new JFrame();

        loginFrame.setTitle(" Login ");
        loginFrame.setLocation(450, 200);
        loginFrame.setSize(500, 500);
        loginFrame.setLayout(null);


        JLabel girisYapLabel  = new JLabel("GİRİŞ YAP");
        girisYapLabel.setFont(new Font("Arial", Font.BOLD, 20));
        girisYapLabel.setLocation(200,100);
        girisYapLabel.setSize(150, 50);
        loginFrame.add(girisYapLabel);

        JLabel isimLabel  = new JLabel("kullaınıcı  ismini giriniz ");
        isimLabel.setFont(new Font("Arial", Font.BOLD, 15));
        isimLabel.setLocation(85,175);
        isimLabel.setSize(300, 50);
        loginFrame.add(isimLabel);

        JLabel sifreLabel  = new JLabel("şifrenizi giriniz");
        sifreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        sifreLabel.setLocation(85,210);
        sifreLabel.setSize(200, 50);
        loginFrame.add(sifreLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 15));
        usernameField.setLocation(285,190);
        usernameField.setSize(150, 20);
        loginFrame.add(usernameField);

        JTextField sifreField = new JTextField();
        sifreField.setFont(new Font("Arial", Font.BOLD, 15));
        sifreField.setLocation(285,225);
        sifreField.setSize(150, 20);
        loginFrame.add(sifreField);

        JButton girisYap = new JButton("giris yap");

        girisYap.setSize(130, 35);
        girisYap.setLocation(120, 325);
        loginFrame.add(girisYap);

        girisYap.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                User user = new User(usernameField.getText(),sifreField.getText());
                Gonderi gonderi = new Gonderi(2,null,user);
                Client.gonder(gonderi);


            }
        });

        JButton üyeOl = new JButton ( "Üye ol");

        üyeOl.setSize(150, 35);
        üyeOl.setLocation(280, 325);
        loginFrame.add(üyeOl);
        
        üyeOl.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                Create.createPage();
                loginFrame.dispose();
            }
        });
        Color eskirenk = üyeOl.getBackground();
        üyeOl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                üyeOl.setBackground(Color.orange);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                üyeOl.setBackground(eskirenk);
            }
        });

        Color eskirenk2 = girisYap.getBackground();
        girisYap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                girisYap.setBackground(Color.pink);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                girisYap.setBackground(eskirenk2);
            }
        });


        loginFrame.getContentPane().setBackground(Color.WHITE);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
