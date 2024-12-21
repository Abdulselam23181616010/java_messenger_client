import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Create {
    public static void createPage() {

        JFrame uyeOlPage  = new JFrame();

        uyeOlPage.setTitle("Üye ol");
        uyeOlPage.setLocation(450, 200);
        uyeOlPage.setSize(500, 500);
        uyeOlPage.setLayout(null);

        JLabel label1  = new JLabel("ÜYE OL");
        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label1.setLocation(200,70);
        label1.setSize(150, 50);
        uyeOlPage.add(label1);

        JLabel label2  = new JLabel("isim ");
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        label2.setLocation(130,150);
        label2.setSize(150, 50);
        uyeOlPage.add(label2);

        JLabel  label3 = new JLabel("soyisim  ");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setLocation(130,200);
        label3.setSize(150, 50);
        uyeOlPage.add(label3);

        JLabel label4 = new JLabel("kullanıcı isim");
        label4.setFont(new Font("Arial", Font.BOLD, 15));
        label4.setLocation(130,250);
        label4.setSize(150, 50);
        uyeOlPage.add(label4);

        JLabel label5  = new JLabel("şifre ");
        label5.setFont(new Font("Arial", Font.BOLD, 15));
        label5.setLocation(130,300);
        label5.setSize(150, 50);
        uyeOlPage.add(label5);


        JTextField text1 = new JTextField();
        text1.setSize(130, 20);
        text1.setLocation(250,165);
        text1.setFont(new Font("Arial", Font.BOLD, 15));
        uyeOlPage.add(text1);



        JTextField text2 = new JTextField();
        text2.setSize(130, 20);
        text2.setLocation(250,212);
        text2.setFont(new Font("Arial", Font.BOLD, 15));
        uyeOlPage.add(text2);


        JTextField text3 = new JTextField();
        text3.setSize(130, 20);
        text3.setLocation(250,262);
        text3.setFont(new Font("Arial", Font.BOLD, 15));
        uyeOlPage.add(text3);

        JTextField text4 = new JTextField();
        text4.setSize(130, 20);
        text4.setLocation(250,312);
        text4.setFont(new Font("Arial", Font.BOLD, 15));
        uyeOlPage.add(text4);

        JButton buton1 = new JButton("Üye ol");
        buton1.setFont(new Font("Arial", Font.BOLD, 15));
        buton1.setLocation(250,370);
        buton1.setSize(150, 25);
        uyeOlPage.add(buton1);

        buton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(null,"Merhaba " + text1.getText() + " uyeniz basayla gerceklesti");
                System.out.println();


            }
        });

        Color eskirenk1  = buton1.getBackground();
        buton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buton1.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buton1.setBackground(eskirenk1);
            }
        });

        JButton geriGit = new JButton("Geri git");
        geriGit.setFont(new Font("Arial", Font.BOLD, 15));
        geriGit.setLocation(85,370);
        geriGit.setSize(150, 25);
        uyeOlPage.add(geriGit);

        Color eskirenk2  = geriGit.getBackground();
        geriGit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.loginPage();
                uyeOlPage.dispose();

            }
        });

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

        uyeOlPage.setVisible(true);
        uyeOlPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
