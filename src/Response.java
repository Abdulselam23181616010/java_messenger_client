public class Response extends Gonderi{
    private boolean responseCode;

    public Response(int requestType, String string, User user) {
        super(requestType, string, user);
    }

    public void setResponseCode(boolean responseCode){
        this.responseCode = responseCode;
    }

    public boolean getResponseCode(){
        return this.responseCode;
    }


}
