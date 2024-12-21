public class Response extends Gonderi{
    private int responseCode;

    public Response(int requestType, String string, User user) {
        super(requestType, string, user);
    }

    public void setResponseCode(int responseCode){
        this.responseCode = responseCode;
    }

    public int getResponseCode(){
        return this.responseCode;
    }


}
