import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SmartHomeSystem system = new SmartHomeSystem();

        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        scanner.nextLine();

        String[] commands = new String[q];
        System.out.println("Enter command (add_device , set_device , remove_device , list_devices , add_rule , check_rules , list_rules) : ");
        for(int i = 0 ; i < q ; i += 1){
            commands[i] = scanner.nextLine();
        }

        for(int i = 0 ; i < q ; i += 1){
            try{
                String[] parts = commands[i].split(" ");

                switch (parts[0]){
                    case "add_device":
                        system.addDevice(parts[1] , parts[2] , parts[3]);
                        break;
                    case "set_device":
                        system.setDevice(parts[1] , parts[2] , parts[3]);
                        break;
                    case "remove_device":
                        system.removeDevice(parts[1]);
                        break;
                    case "list_devices":
                        system.listDevices();
                        break;
                    case "add_rule":
                        system.addRule(parts[1] , parts[2] , parts[3]);
                        break;
                    case "check_rules":
                        system.checkRules(parts[1]);
                        break;
                    case "list_rules":
                        system.listRules();
                        break;
                    default:
                        System.out.println("invalid command");
                }
            }
            catch(Exception e) {
                if(e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}