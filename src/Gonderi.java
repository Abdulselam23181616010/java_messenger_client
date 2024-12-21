public class Gonderi {
    protected int requestType;
    protected String mesaj;
    protected User user;

    //Gonderi için constructor oluşturalım
    public Gonderi(int requestType, String mesaj, User user){
        this.requestType = requestType;
        setMesaj(mesaj);
        this.user  = user;

    }

    //Sonra child sınıflarda bu metodu yeniden biçimlendireceğiz
    public boolean islemYap(){
        return true;

    }

    //String için setter ve getter oluşturalım
    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getMesaj() {
        return mesaj;
    }

}
