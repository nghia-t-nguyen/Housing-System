import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;

public class Driver {
  public static Server server;

  public static final String prompt1 =
      "> What would you like to do\n0: Exit program\n1: Login\n2: Search\n3: List\n4: Create an account\n";

  public Driver(Server server) { // Used in case we want to add functionality when making a GUI
    this.server = server;
  }

  private Account tryLogin(String username, String password) {
    int result = server.login(username, password);

    if (result < 0) {
      if (result == -1) {
        System.out.println("User is unknown");
        return null;
      }
      if (result == -2) {
        System.out.println("Invalid password");
        return null;
      }
    }
    System.out.println("Login Successful");
    return server.getAccount(username);

  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    server = Server.getInstance();
    server.addAccount(new StudentAccount("nghia-t-nguyen", "nghia", "Nghia", "Nguyen", "1234"));
    server.addAccount(new StudentAccount("riceconfetti", "rhylen", "Rhylen", "Nguyen", "1234"));
    server.addAccount(new StudentAccount("Austino1999", "austin", "Austin", "O'Malley", "1234"));
    server.addAccount(new StudentAccount("deesort", "dylan", "Dylan", "Ortuno", "1234"));

    // server.addAccount(new StudentAccount(""));
    /*
     * ArrayList<Account> savedAccounts = DataLoader.loadAccounts(); for (Account account :
     * savedAccounts) { server.addAccount(account); }
     */
    
    Driver display = new Driver(server);
    
    Account loggedIn = null;
    String input;
    int option;
    boolean end = false;
    
    while(true) {
      option = -1;
      if (loggedIn == null) {
        System.out.println(prompt1);
        input = scan.nextLine();
        try {
          option = Integer.parseInt(input);
          if (option < 0 || option > 4) {
            System.out.println("Not a valid option");
          }
        } catch (Exception e) {
          System.out.println("Not a valid input");
        }
      }
      
      if (option == 0) {
          end = true;
      } else if (option == 1) {
          System.out.println("Username: ");
          String username = scan.nextLine();
          System.out.println("Password: ");
          String password = scan.nextLine();
          loggedIn = display.tryLogin(username, password);
      }
      
      if (end) {
        break;
      }
    }
    
    scan.close();
    System.out.println("Program terminated");
  }
}
