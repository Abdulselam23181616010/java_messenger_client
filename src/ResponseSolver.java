public class ResponseSolver extends Response{

    public ResponseSolver(int requestType, String string, User user) {
        super(requestType, string, user);
    }

    public void solve(){
        try{
            switch (super.getResponseCode()){
                case 11:
                    break;
                case 21:
                    break;
                case 31:
                    break;
                default:

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
