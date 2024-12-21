import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mesajdeneme extends JFrame {
    private JTextArea messageArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton  geri;

    public Mesajdeneme() {
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
        geri = new JButton("germiş yükle");
        inputPanel.add(geri, BorderLayout.WEST);


        add(inputPanel, BorderLayout.SOUTH);

        // Gönder butonuna tıklama işlemi
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String message = messageField.getText().trim();
                if (!message.isEmpty()) {
                    // Şu anki tarih ve saati al ve biçimlendir
                    LocalDateTime currentTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Yıl-ay-gün saat:dakika:saniye
                    String formattedDateTime = currentTime.format(formatter);

                    // Mesajı alanın içine ekle
                    messageArea.append("Sen (" + formattedDateTime + "): " + message + "\n");
                    messageField.setText(""); // Alanı temizle
                }
            }
        });

        // Enter tuşuyla gönderme
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick(); // Butona tıklama işlevini çağır
            }
        });

        // Pencere ayarları
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ortala
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Mesajdeneme().setVisible(true);
        });
    }
}
