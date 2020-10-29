import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;

public class Driver {
  private static Server server;
  private static Scanner scan;

  Driver() { // Used in case we want to add functionality when making a GUI
    server = Server.getInstance();
    scan = new Scanner(System.in);
  }
  
  public void run() {
    Display display = new DefaultDisplay(null);
    int choice = 0;
    
    while (true) {
      System.out.println("=================================");
      display.display();
      choice = getUserCommand();
      if (choice == 0) {
        break;
      }
      
      display = display.option(choice);
    }
    scan.close();
    
  }
  
  private int getUserCommand() {
    if (scan.hasNextInt()) {
      int ret = scan.nextInt();
      scan.nextLine();
      return ret;
    } else {
      return -1;
    }
  }

  private static Account tryLogin(String username, String password) {
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
    return server.getAccount(username);

  }

  private static interface Display {
    public Display option(int choice);

    public void display();
  }

  private static class DefaultDisplay implements Display {
    private Account loggedIn;
    
    public DefaultDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(
          "> What would you like to do\n0: Exit program\n1: Login\n2: Search\n3: List\n4: Create an account");
    }

    public Display option(int choice) {
      switch(choice) {
        case 1:
          return new LoginDisplay(loggedIn);
        case 2:
          for (Account account : server.getAllAccounts())
            System.out.println(account.getFirstName());
          for (Listing listing : server.getAllListings())
            System.out.println(listing.getAddress());
          return this;
        case 3:
          //server.addAccount(new StudentAccount("rhylen", "Rhylen", "Nguyen", "rhylen", "rhylen"));
          //server.addListing(
              //new Listing("Appartment T", "123 Alphabet Ln, Columbia, 29063", 1234, false));
          //return this;
          return new CreateAccountDisplay(loggedIn);
        case 4: 
        	server.addAccount(new StudentAccount("rhylen", "Rhylen", "Nguyen", "rhylen" , "rhylen"));
        	server.addListing(new Listing("Appartment T", "123 Alphabet Ln, Columbia, 29063", 1234, false));
        	return this;
        default:
          System.out.println("Invalid input");
          return this;
      }
	}
  }

  private static class LoginDisplay implements Display {
    private Account loggedIn;
    
    public LoginDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(">Login:\n0: Exit program \n1: Return\n2: Login as a student\n3: Login as a host");
    }

    public Display option(int choice) {
      switch(choice) {
        case 1:
          return new DefaultDisplay(null);
        case 2:
          System.out.println("Username:");
          String username = scan.nextLine();
          System.out.println("Password");
          String password = scan.nextLine();
          loggedIn = tryLogin(username, password);
          if (loggedIn == null) {
            return this;
          } else if (loggedIn.getClass() == HostAccount.class) {
            System.out.println("Not a student account.");
            return this;
          } else {
            System.out.println("Login successful.");
            return new StudentAccountDisplay(loggedIn);
          }
        case 3:
          System.out.println("Username:");
          String username2 = scan.nextLine();
          System.out.println("Password");
          String password2 = scan.nextLine();
          loggedIn = tryLogin(username2, password2);
          if (loggedIn == null) {
            return this;
          } else if (loggedIn.getClass() == StudentAccount.class) {
            System.out.println("Not a host account.");
            return this;
          } else {
            System.out.println("Login succesful");
            return new HostAccountDisplay(loggedIn);
          }
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class StudentAccountDisplay implements Display {
    private Account loggedIn;
    
    public StudentAccountDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(
          "> What would you like to do\n0: Exit program\n1: Logout\n2: Search\n3: Messages\n4: View Profile");
    }

    public Display option(int choice) {
      switch(choice) {
        case 1:
          System.out.println("Logging out");
          return new DefaultDisplay(null);
        case 3:
          return new MessageDisplay(loggedIn);
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }
  
  private static class HostAccountDisplay implements Display {
    private Account loggedIn;
    
    public HostAccountDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(
          "> What would you like to do\n0: Exit program\n1: Logout\n2: Search\n3: List\n4: Messages\n5: View Profile");
    }

    public Display option(int choice) {
      switch(choice) {
        case 1:
          System.out.println("Logging out");
          return new DefaultDisplay(null);
        case 4:
          return new MessageDisplay(loggedIn);
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class MessageDisplay implements Display {
    private Account loggedIn;
    
    public MessageDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out
          .println(">Messages:\n0: Exit program\n1: Return\n2: View messages\n3: Send Messages");
    }

    public Display option(int choice) {
      switch(choice) {
        case 1:
          if (loggedIn.getClass() == StudentAccount.class) {
            return new StudentAccountDisplay(loggedIn);
          } else if (loggedIn.getClass() == HostAccount.class) {
            return new HostAccountDisplay(loggedIn);
          }
        case 2:
          loggedIn.getMessageBox().showMessages();
          return this;
        case 3:
          System.out.println("To:");
          Account recipient = server.getAccount(scan.nextLine());
          if (recipient == null) {
            System.out.println("User not found");
          } else {
            System.out.println("Enter message:");
            String message = scan.nextLine();
            loggedIn.getMessageBox().sendMessage(recipient, message);
          }
          return this;
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }


  public static void main(String[] args) {
    Driver myDriver = new Driver();
    //myDriver.run();
  }
}