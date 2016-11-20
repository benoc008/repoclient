package tv.ustream;

public class Main {

    private static final String USAGE =
            "Usage: \n" +
            "add [name] [creator] \n" +
            "get [name] \n" +
            "get-by-access-count [access count] \n" +
            "delete [name] \n"
            ;

    public static void main(String[] args) {

        Controller controller = new Controller();

        if (args.length == 0) {
            System.out.println(USAGE);
        } else {
            try {
                switch (args[0]) {
                    case "add":
                        controller.add(args);
                        break;
                    case "get":
                        controller.getByName(args);
                        break;
                    case "get-by-access-count":
                        controller.getByAccessCount(args);
                        break;
                    case "delete":
                        controller.delete(args);
                        break;
                    default:
                        System.out.println(USAGE);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
