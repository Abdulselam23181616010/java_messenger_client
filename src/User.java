public class User {
    String username;
    String sifreHash;
    String isim;
    String soyisim;

    public User(String username, String sifreHash){
        this.username = username;
        this.sifreHash = sifreHash;


    }

    public User(String username,String sifreHash, String isim, String soyisim){
        this.username = username;
        this.sifreHash = sifreHash;
        this.isim = isim;
        this.soyisim = soyisim;

    }

}
