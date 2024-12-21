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


        JLabel label1  = new JLabel("GİRİŞ YAP");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setLocation(200,100);
        label1.setSize(150, 50);
        loginFrame.add(label1);

        JLabel label2  = new JLabel("kullaınıcı  ismini giriniz ");
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        label2.setLocation(85,175);
        label2.setSize(300, 50);
        loginFrame.add(label2);

        JLabel label3  = new JLabel("şifrenizi giriniz");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setLocation(85,210);
        label3.setSize(200, 50);
        loginFrame.add(label3);

        JTextField text1 = new JTextField();
        text1.setFont(new Font("Arial", Font.BOLD, 15));
        text1.setLocation(285,190);
        text1.setSize(150, 20);
        loginFrame.add(text1);

        JTextField text2 = new JTextField();
        text2.setFont(new Font("Arial", Font.BOLD, 15));
        text2.setLocation(285,225);
        text2.setSize(150, 20);
        loginFrame.add(text2);

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

        JButton girisYap = new JButton("giris yap");

        girisYap.setSize(130, 35);
        girisYap.setLocation(120, 325);
        loginFrame.add(girisYap);

        girisYap.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane panel = new JOptionPane();
                JOptionPane.showMessageDialog(null,"Merhaba " + text1.getText() +  " programa hos geldiniz" );

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
