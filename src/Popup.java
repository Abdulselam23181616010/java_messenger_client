
import javax.swing.*;

public class Popup {

    // Method to show a positive message popup
    public static void showPopup(JFrame parentFrame, String message) {
        JOptionPane.showMessageDialog(
                parentFrame,
                message,        // Positive message to display
                "Bilgilendirme",              // Title of the dialog
                JOptionPane.INFORMATION_MESSAGE // Type of message
        );
    }
}