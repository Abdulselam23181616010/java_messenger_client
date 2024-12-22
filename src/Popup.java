
import javax.swing.*;

public class Popup {
    //Bilgilendirme amaçlı popup nesnesini gösteren metod
    public static void showPopup(JFrame parentFrame, String message) {
        JOptionPane.showMessageDialog(
                parentFrame,
                message,        // gösterilecek mesaj
                "Bilgilendirme",              // başlık
                JOptionPane.INFORMATION_MESSAGE // mesaj türü
        );
    }
}