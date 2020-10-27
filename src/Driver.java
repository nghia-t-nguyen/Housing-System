import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;

public class Driver {
  public static Server server;

  public Driver(Server server) { // Used in case we want to add functionality when making a GUI
    this.server = server;
  }

  private Account tryLogin(String username, String password) {
    int result = server.login(username, password);

    if (result < 0) {
      if (result == -1) {
        System.out.println("User is unknown");
        return null;
      } else if (result == -2) {
        System.out.println("Invalid password");
        return null;
      }
    }
    System.out.println("Login Successful");
    return server.getAccount(username);

  }

  private static interface Display {
    public Display option(int choice);

    public void display();
  }

  private static class DefaultDisplay implements Display {
    public DefaultDisplay() {}

    public void display() {
      System.out.println(
          "> What would you like to do\n0: Exit program\n1: Login\n2: Search\n3: List\n4: Create an account");
    }

    public Display option(int choice) {
      if (choice == 1) {
        return new LoginDisplay();
      }
      return this;
    }
  }

  private static class LoginDisplay implements Display {
    public LoginDisplay() {}

    public void display() {
      System.out.println(">Login:\n0: Exit program \n1: Return\n2: Continue to Login");
    }

    public Display option(int choice) {
      if (choice < 0 || choice > 2) {
        System.out.println("Incorrect input");
        return this;
      } else if (choice == 1) {
        return new DefaultDisplay();
      } else if (choice == 2) {
        return this;
      }
      return this;
    }
  }

  private static class StudentAccountDisplay implements Display {
    public StudentAccountDisplay() {}

    public void display() {
      System.out.println(
          "> What would you like to do\n0: Exit program\n1: Logout\n2: Search\n3: Messages\n4: View Profile");
    }

    public Display option(int choice) {
      if (choice < 0 || choice > 4) {
        System.out.println("Incorrect input");
        return this;
      }
      if (choice == 1) {
        System.out.println("Logging out");
        return new DefaultDisplay();
      }
      if (choice == 3) {
        return new MessageDisplay();
      }
      return this;
    }
  }

  private static class MessageDisplay implements Display {
    public MessageDisplay() {}

    public void display() {
      System.out
          .println(">Messages:\n0: Exit program\n1: Return\n2: View messages\n3: Send Messages");
    }

    public Display option(int choice) {
      if (choice < 0 || choice > 3) {
        System.out.println("Incorrect input");
        return this;
      } else if (choice == 1) {
        return new StudentAccountDisplay();
      } else {
        return this;
      }
    }
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

    Driver myDriver = new Driver(server);

    Display display = new DefaultDisplay();
    Account loggedIn = null;
    int choice = -2;

    while(true) {
      System.out.println("================================");
      display.display();
      try {
        choice = scan.nextInt();
        scan.nextLine();
      } catch (Exception e) {
        System.out.println("Sorry, didn't quite get that");
      }
      if (choice == 0) {
        break;
      }

      if (display.getClass() == MessageDisplay.class) {
        if (choice == 2) {
          loggedIn.getMessageBox().showMessages();
        } else if (choice == 3) {
          System.out.println("To:");
          Account recipient = server.getAccount(scan.nextLine());
          if (recipient == null) {
            System.out.println("User not found");
          } else {
            System.out.println("Enter message:");
            String message = scan.nextLine();
            loggedIn.getMessageBox().sendMessage(recipient, message);
          }
        }
      }
      
      display = display.option(choice);
      if (display.getClass() == LoginDisplay.class && choice == 2) {
        System.out.println("Username: ");
        String username = scan.nextLine();
        System.out.println("Password: ");
        String password = scan.nextLine();
        loggedIn = myDriver.tryLogin(username, password);
        if (loggedIn != null) {
          display = new StudentAccountDisplay();
        }
      }
      
      if (display.getClass() == StudentAccountDisplay.class && choice == 1) {
        loggedIn = null;
      }
    }


    scan.close();
    System.out.println("Program terminated");
  }
}
