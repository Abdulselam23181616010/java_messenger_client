import java.io.*;


public class SifrelemeClient {
    private static final String SECRET_KEY = "your-base64-encoded-key-here";
    public static String sifrele(Gonderi gonderi){
        try{
            //Gonderimizi bayt dizisine çevirelim
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(gonderi);

            //Oluşan diziyi şifreleyelim
            String sifrelenmisVeri = AESUtil.encrypt(new String(byteArrayOutputStream.toByteArray()), SECRET_KEY);

            //Son olarak şifrelenmiş diziyi döndürelim
            return sifrelenmisVeri;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response cevir(String sifrelenmisVeri){
        try{
            //Gelen String diziyi  bayt dizisine çevirelim ve
            byte[] decryptedBytes = AESUtil.decrypt(sifrelenmisVeri, SECRET_KEY).getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            //Veriyi bizim anlayabileceğimiz türden objeye çevirelim
            Response response = (Response)objectInputStream.readObject();

            //Son olarak çıkan objemizi döndürelim
            return response;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}

