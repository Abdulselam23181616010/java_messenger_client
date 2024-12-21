import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Create {
    public static void main(String[] args) {


        JFrame jf  = new JFrame();

        jf.setTitle("Üye ol");
        jf.setLocation(450, 200);
        jf.setSize(500, 500);
        jf.setLayout(null);

        JLabel label1  = new JLabel("ÜYE OL");
        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label1.setLocation(200,70);
        label1.setSize(150, 50);
        jf.add(label1);

        JLabel label2  = new JLabel("isim ");
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        label2.setLocation(130,150);
        label2.setSize(150, 50);
        jf.add(label2);

        JLabel  label3 = new JLabel("soyisim  ");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setLocation(130,200);
        label3.setSize(150, 50);
        jf.add(label3);

        JLabel label4 = new JLabel("kullanıcı isim");
        label4.setFont(new Font("Arial", Font.BOLD, 15));
        label4.setLocation(130,250);
        label4.setSize(150, 50);
        jf.add(label4);

        JLabel label5  = new JLabel("şifre ");
        label5.setFont(new Font("Arial", Font.BOLD, 15));
        label5.setLocation(130,300);
        label5.setSize(150, 50);
        jf.add(label5);


        JTextField text1 = new JTextField();
        text1.setSize(130, 20);
        text1.setLocation(250,165);
        text1.setFont(new Font("Arial", Font.BOLD, 15));
        jf.add(text1);



        JTextField text2 = new JTextField();
        text2.setSize(130, 20);
        text2.setLocation(250,212);
        text2.setFont(new Font("Arial", Font.BOLD, 15));
        jf.add(text2);


        JTextField text3 = new JTextField();
        text3.setSize(130, 20);
        text3.setLocation(250,262);
        text3.setFont(new Font("Arial", Font.BOLD, 15));
        jf.add(text3);

        JTextField text4 = new JTextField();
        text4.setSize(130, 20);
        text4.setLocation(250,312);
        text4.setFont(new Font("Arial", Font.BOLD, 15));
        jf.add(text4);

        JButton buton1 = new JButton("Üye ol");
        buton1.setFont(new Font("Arial", Font.BOLD, 15));
        buton1.setLocation(250,370);
        buton1.setSize(150, 25);
        jf.add(buton1);

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

        JButton buton2 = new JButton("Geri git");
        buton2.setFont(new Font("Arial", Font.BOLD, 15));
        buton2.setLocation(85,370);
        buton2.setSize(150, 25);
        jf.add(buton2);

        Color eskirenk2  = buton2.getBackground();

        buton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buton2.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buton2.setBackground(eskirenk2);
            }
        });

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
