public class Response extends Gonderi{
    private int responseCode;

    public Response(int requestType, Mesaj mesaj, User user) {
        super(requestType, mesaj, user);
    }

    public void setResponseCode(int responseCode){
        this.responseCode = responseCode;
    }

    public int getResponseCode(){
        return this.responseCode;
    }


}
