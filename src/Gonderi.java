public class Gonderi {
    private int requestType;
    private Mesaj mesaj;
    private User user;

    //Gonderi için constructor oluşturalım
    public Gonderi(int requestType, Mesaj mesaj){
        this.requestType = requestType;
        this.mesaj = mesaj;

    }

    //Sonra child sınıflarda bu metodu yeniden biçimlendireceğiz
    public boolean islemYap(){
        return true;

    }


}
