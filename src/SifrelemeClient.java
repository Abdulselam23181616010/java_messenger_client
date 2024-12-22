import java.io.*;
import java.util.Base64;
import java.util.Properties;


public class SifrelemeClient {
    //Hassas içerikleri config filedan çekelim
    private static Properties prop = ConfigHandler.use();

    private static final String SECRET_KEY = prop.getProperty("SECRET_KEY","");

    //Gonderi nesneleri şifrelemek için metodtur
    public static String sifrele(Gonderi gonderi){
        try {
            // Gonderi nesneyi bayt diziye çevirelim
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(gonderi);

            // bayt diziyi base64 şeklinde kodlayalım
            String base64Encoded = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

            // base64 diziyi şifreyelim
            return AESUtil.encrypt(base64Encoded, SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //User nesneleri şifrelemek için metodtur
    public static String userSifrele(User user){
        try {
            // Gonderi nesneyi bayt diziye çevirelim
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(user);

            // bayt diziyi base64 şeklinde kodlayalım
            String base64Encoded = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

            // base64 diziyi şifreyelim
            return AESUtil.encrypt(base64Encoded, SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //gonderiyi şifrelemek için metodtur
    public static Gonderi cevir(String sifrelenmisVeri){
        try {
            // Şifrelenmiş veriyi çözelim
            String decryptedBase64 = AESUtil.decrypt(sifrelenmisVeri, SECRET_KEY);

            // Base64 kodtan bayt diziye de çevirelim
            byte[] decryptedBytes = Base64.getDecoder().decode(decryptedBase64);

            //Bayt diziyi tekrar gönderi nesneye çevirelim ve döndürelim
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return (Gonderi) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

