import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

    public static void main(String[] args) {

        JFrame jf  = new JFrame();

        jf.setTitle(" Login ");
        jf.setLocation(450, 200);
        jf.setSize(500, 500);
        jf.setLayout(null);


        JLabel label1  = new JLabel("GİRİŞ YAP");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setLocation(200,100);
        label1.setSize(150, 50);
        jf.add(label1);

        JLabel label2  = new JLabel("kullaınıcı  ismini giriniz ");
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        label2.setLocation(85,175);
        label2.setSize(300, 50);
        jf.add(label2);

        JLabel label3  = new JLabel("şifrenizi giriniz");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setLocation(85,210);
        label3.setSize(200, 50);
        jf.add(label3);

        JTextField text1 = new JTextField();
        text1.setFont(new Font("Arial", Font.BOLD, 15));
        text1.setLocation(285,190);
        text1.setSize(150, 20);
        jf.add(text1);

        JTextField text2 = new JTextField();
        text2.setFont(new Font("Arial", Font.BOLD, 15));
        text2.setLocation(285,225);
        text2.setSize(150, 20);
        jf.add(text2);

        JButton button1 = new JButton ( "Üye ol");

        button1.setSize(150, 35);
        button1.setLocation(280, 325);
        jf.add(button1);
        
        button1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JOptionPane panel1 =  new JOptionPane ();
                panel1.showMessageDialog(null , "uye oluyorsunuz");
            }
        });
        Color eskirenk = button1.getBackground();
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button1.setBackground(Color.orange);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button1.setBackground(eskirenk);
            }
        });

        JButton buton2 = new JButton("giris yap");

        buton2.setSize(130, 35);
        buton2.setLocation(120, 325);
        jf.add(buton2);

        buton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane panel = new JOptionPane();
                JOptionPane.showMessageDialog(null,"Merhaba " + text1.getText() +  " programa hos geldiniz" );

            }
        });
        Color eskirenk2 = buton2.getBackground();
        buton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buton2.setBackground(Color.pink);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buton2.setBackground(eskirenk2);
            }
        });


        jf.getContentPane().setBackground(Color.WHITE);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
