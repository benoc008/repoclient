package tv.ustream;

public class InputValidator {

    private static final String ERROR_ADD = "Usage: add [name] [creator]";
    private static final String ERROR_GET = "Usage: get [name]";
    private static final String ERROR_GET_BY_ACCESS_COUT = "Usage: get-by-access-count [access count]";
    private static final String ERROR_DELETE = "Usage: delete [name]";

    public void validateAdd(String[] args) throws Exception {
        if(args.length != 3){
            throw new Exception(ERROR_ADD);
        }
    }

    public void validateGet(String[] args) throws Exception {
        if(args.length != 2){
            throw new Exception(ERROR_GET);
        }
    }

    public void validateGetByAccessCount(String[] args) throws Exception {
        if(args.length != 2){
            throw new Exception(ERROR_GET_BY_ACCESS_COUT);
        }
    }

    public void validateDelete(String[] args) throws Exception {
        if(args.length != 2){
            throw new Exception(ERROR_DELETE);
        }
    }

}
