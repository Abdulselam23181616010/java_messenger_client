import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatFrame extends JFrame {
    private JTextArea messageArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton  geri;

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
        geri = new JButton("geçmiş yükle");
        inputPanel.add(geri, BorderLayout.WEST);


        add(inputPanel, BorderLayout.SOUTH);


        // Pencere ayarları
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ortala
    }

    public void addSendListener(ActionListener listener){
        sendButton.addActionListener(listener);
    }



    public JTextField getMessageField(){
        return messageField;

    }

    public void writeMessageArea(String mesaj) {
        SwingUtilities.invokeLater(() -> {
            this.messageArea.append(mesaj);
        });
    }

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
}
