import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;

public class Driver {
  private static Server server;
  private static Scanner scan;

  Driver() {
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
          "> What would you like to do\n0: Exit program\n1: Login\n2: Search\n3: Create an account");
    }

    public Display option(int choice) {
      switch (choice) {
        case 1:
          return new LoginDisplay(loggedIn);
        case 2:
          /*for (Account account : server.getAllAccounts()) {
            System.out.println(account.getFirstName());
            if (account instanceof StudentAccount) {
            	for (Listing listing :((StudentAccount)account).getBookmarks())
            		System.out.println("     "+listing.getAddress());
            }
          }
          for (Listing listing : server.getAllListings()) {
            System.out.println(listing.getAddress());
          }*/
          return this;
        case 3:
          //server.addAccount(new StudentAccount("rhylen", "1234", "Rhylen", "Nguyen", "rhylen"));
          //server.addListing(
              //new Listing("Appartment T", "123 Alphabet Ln, Columbia, 29063", 1234, false));
          //return this;
          return new CreateAccountDisplay(loggedIn);
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
      System.out.println(
          ">Login:\n0: Exit program \n1: Return\n2: Login as a student\n3: Login as a host");
    }

    public Display option(int choice) {
      switch (choice) {
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
      switch (choice) {
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
      switch (choice) {
        case 1:
          System.out.println("Logging out");
          return new DefaultDisplay(null);
        case 3:
          return new ListingDisplay((HostAccount)loggedIn);
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
      switch (choice) {
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
            DataWriter.saveAccounts();
          }
          return this;
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class CreateAccountDisplay implements Display {
    private Account loggedIn;

    public CreateAccountDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(
          "> What would you like to do\n0: Exit program\n1: Return\n2: Create a student account\n3: Create a host account");
    }

    public Display option(int choice) {
      switch (choice) {
        case 1:
          return new DefaultDisplay(null);
        case 2:
          System.out.println("Enter student ID:");
          String studentID = scan.nextLine();
          System.out.println("Enter first name");
          String firstName = scan.nextLine();
          System.out.println("Enter last name");
          String lastName = scan.nextLine();
          //TODO verify studentID
          System.out.println("Enter username:");
          String username = scan.nextLine();
          if (username.length() < 6) {
            System.out.println("Username is too short. Must be 6-12 characters.");
            return this;
          } else if (username.length() > 12) {
            System.out.println("Username is too long. Must be 6-12 characters.");
            return this;
          }
          System.out.println("Enter password:");
          String password1 = scan.nextLine();
          if (password1.length() < 6) {
            System.out.println("Password is too short. Must be 6-12 characters.");
            return this;
          } else if (password1.length() > 12) {
            System.out.println("Password is too long. Must be 6-12 characters.");
            return this;
          }
          System.out.println("Re-enter password to verify:");
          String password2 = scan.nextLine();
          if (!password1.equals(password2)) {
            System.out.println("Passwords do not match");
            return this;
          }
          System.out.println("Account succesfully created.");
          Account student = new StudentAccount(username, password1, firstName, lastName, studentID);
          server.addAccount(student);
          return new StudentAccountDisplay(student);
        case 3:
          System.out.println("Enter first name:");
          String firstNameH = scan.nextLine();
          System.out.println("Enter last name:");
          String lastNameH = scan.nextLine();
          System.out.println("Enter username:");
          String usernameH = scan.nextLine();
          if (usernameH.length() < 6) {
            System.out.println("Username is too short. Must be 6-12 characters.");
            return this;
          } else if (usernameH.length() > 12) {
            System.out.println("Username is too long. Must be 6-12 characters.");
            return this;
          }
          System.out.println("Enter password:");
          String passwordH1 = scan.nextLine();
          if (passwordH1.length() < 6) {
            System.out.println("Password is too short. Must be 6-12 characters.");
            return this;
          } else if (passwordH1.length() > 12) {
            System.out.println("Password is too long. Must be 6-12 characters.");
            return this;
          }
          System.out.println("Re-enter password to verify:");
          String passwordH2 = scan.nextLine();
          if (!passwordH1.equals(passwordH2)) {
            System.out.println("Passwords do not match");
            return this;
          }
          System.out.println("Account succesfully created.");
          Account host = new HostAccount(usernameH, passwordH1, firstNameH, lastNameH);
          server.addAccount(host);
          return new HostAccountDisplay(host);
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class SearchDisplay implements Display {

	@Override
	public Display option(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	  
  }

  private static class ListingDisplay implements Display {
	private HostAccount loggedIn;
	private Listing listing;

    public ListingDisplay(HostAccount account) {
      loggedIn = account;
    }
    
	public void display() {
		System.out.println("Enter an address:");
	    String address = scan.nextLine();
	    System.out.println("Enter an name:");
	    String name  = scan.nextLine();
	    System.out.println("Enter a rent price:");
	    double rent = scan.nextDouble();
	    
	    listing = new Listing(loggedIn, name, address, rent);
	    
	    System.out.println("Enter number of bedrooms:");
	    listing.addBedrooms(scan.nextInt());
	    System.out.println("Enter number of bathrooms:");
	    listing.addBathrooms(scan.nextInt());
	    System.out.println("Enter a description:");
	    listing.addDescription(scan.nextLine());
	    
	    System.out.println(
	            "\n\n> What would you like to add\n0: Exit program\n1: Logout\n2: Add filters\n3: View Listing");
	      }
	
	
	public Display option(int choice) {
		switch (choice) {
        case 1:
          System.out.println("Logging out");
          return new DefaultDisplay(null);
        case 2:
	    	System.out.println("Enter filter:");
		    listing.addFilter(scan.nextLine());
		    return this;
        case 3:
          return new MessageDisplay(loggedIn);
        default:
          System.out.println("Invalid input");
          return this;
      }
    }   
		
}

  
  public static void main(String[] args) {
    Driver myDriver = new Driver();
    myDriver.run();
  }
}
