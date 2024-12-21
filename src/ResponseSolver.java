public class ResponseSolver extends Response{

    public ResponseSolver(int requestType, Mesaj mesaj, User user) {
        super(requestType, mesaj, user);
    }

    public int solve(){
        try{
            switch (super.getResponseCode()){
                case 11:
                    return 1;
                case 21:
                    return 1;
                case 31:
                    return 1;
                default:
                    return 0;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
