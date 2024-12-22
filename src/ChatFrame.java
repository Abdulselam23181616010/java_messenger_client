import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


//Mesajlaşmanın gerçekleştiği kullancı arayüzüdür
public class ChatFrame extends JFrame {
    private JTextArea messageArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton  gecmis;

    public ChatFrame() {
        // Pencere başlığı
        super("Mesajlaşma Ekranı");

        // Ana paneli oluştur
        setLayout(new BorderLayout());

        // Üstteki mesaj alanı
        messageArea = new JTextArea();
        messageArea.setEditable(false); // Kullanıcı düzenleyemesin
        messageArea.setLineWrap(true); // Satır kaydırma
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea); // Kaydırma çubuğu ekle
        add(scrollPane, BorderLayout.CENTER);

        // Alt kısımda mesaj girişi ve buton
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        messageField = new JTextField(); // Mesaj giriş alanı
        inputPanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Gönder"); // Gönder butonu
        inputPanel.add(sendButton, BorderLayout.EAST);
        gecmis = new JButton("geçmiş yükle");
        inputPanel.add(gecmis, BorderLayout.WEST);


        add(inputPanel, BorderLayout.SOUTH);


        // Pencere ayarları
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ortala
    }

    //Gerekli getter-setter oluşturalım
    public JTextArea getMessageArea(){
        return messageArea;
    }

    public void setMessageArea(String string){
        this.messageArea.setText(string);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public void setMessageField(String s) {
        messageField.setText(s);
    }
    public void addSendListener(ActionListener listener){
        sendButton.addActionListener(listener);
    }

    //Butonlar için metodları tanımlayalım
    public void addGecmissListener(ActionListener listener){
        gecmis.addActionListener(listener);
    }

    public JTextField getMessageField(){
        return messageField;

    }
    //Mesajların chate yazdırmasını sağlayan metodta oluşturalım
    public void writeMessageArea(String mesaj) {
        SwingUtilities.invokeLater(() -> {
            this.messageArea.append(mesaj);
        });
    }

}
