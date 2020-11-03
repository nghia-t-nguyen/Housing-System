import java.util.*;
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
    System.out.println("Program terminated.");
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
          return new SearchDisplay(loggedIn);
        case 3:
          return new CreateAccountDisplay(loggedIn);
        case 4:
        	
          for (Account account : server.getAllAccounts()) {
            System.out.print(account.getFirstName()); 
            System.out.println("  - " + account.getUsername()); 
          } 
          System.out.println(); 
          for (Listing listing : server.getAllListings()) { 
            System.out.println(listing.getHost().getUsername()); 
          }
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
          System.out.println("Password:");
          String password2 = scan.nextLine();
          loggedIn = tryLogin(username2, password2);
          if (loggedIn == null) {
            return this;
          } else if (loggedIn.getClass() == StudentAccount.class) {
            System.out.println("Not a host account.");
            return this;
          } else {
            System.out.println("Login successful");
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
        case 2:
          return new SearchDisplay(loggedIn);
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
          "> What would you like to do\n0: Exit program\n1: Logout\n2: Search\n3: Manage Listings\n4: Messages\n5: View Profile");
    }

    public Display option(int choice) {
      switch (choice) {
        case 1:
          System.out.println("Logging out");
          return new DefaultDisplay(null);
        case 2:
          return new SearchDisplay(loggedIn);
        case 3:
          return new ListingDisplay((HostAccount) loggedIn);
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
          return new DefaultDisplay(loggedIn);
        case 2:
          System.out.println("Enter student ID:");
          String studentID = scan.nextLine();
          System.out.println("Enter first name");
          String firstName = scan.nextLine();
          System.out.println("Enter last name");
          String lastName = scan.nextLine();
          // TODO verify studentID
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
    private Account loggedIn;

    public SearchDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out
          .println(">Search: \n0: Exit program\n1: Return \n2: Search hosts\n3: Search listings");
    }

    @Override
    public Display option(int choice) {
      switch (choice) {
        case 1:
          if (loggedIn == null) {
            return new DefaultDisplay(null);
          } else if (loggedIn.getClass() == StudentAccount.class) {
            return new StudentAccountDisplay(loggedIn);
          } else if (loggedIn.getClass() == HostAccount.class) {
            return new HostAccountDisplay(loggedIn);
          }
        case 2:
          System.out.println("Enter host name:");
          String hostName = scan.nextLine();
          ArrayList<Account> hosts = server.searchHosts(hostName);
          if (hosts.size() == 0) {
            System.out.println("No results found.");
          } else {
            for (Account host : hosts)
              System.out.println(
                  host.getUsername() + ": " + host.getFirstName() + " " + host.getLastName());
          }
          return this;
        case 3:
          return new SearchListingDisplay(loggedIn);
        default:
          System.out.println("Invalid input.");
          return this;
      }

    }

  }

  private static ArrayList<String> addFilters() {
    Set<String> filters = new HashSet<String>();
    String response = "";
    while (!response.equals("0")) {
      System.out.println(
          "Enter any filters (0 to stop entering filters)\n0: Done\na: Pet Friendly\nb: Cats allowed?\nc: Dogs allowed?\nd:"
          + " Washer \ne: Dryer\nf: Parking\ng: Pool\nh: Gym\ni: Free WiFi \nj: Furnished");
      System.out.print("Filters added:");
      for (String filter : filters) {
        System.out.print(" #" + filter);
      }
      System.out.println();
      response = scan.nextLine();
      if (response.equals("a")) {
        filters.add("pet friendly");
      } else if (response.equals("b")) {
        filters.add("cats");
      } else if (response.equals("c")) {
        filters.add("dogs");
      } else if (response.equals("d")) {
        filters.add("washer");
      } else if (response.equals("e")) {
        filters.add("dryer");
      } else if (response.equals("f")) {
        filters.add("parking");
      } else if (response.equals("g")) {
        filters.add("pool");
      } else if (response.equals("h")) {
        filters.add("gym");
      } else if (response.equals("i")) {
        filters.add("free wifi");
      } else if (response.equals("j")) {
        filters.add("furnished");
      }
    }
    return new ArrayList<String>(filters);
  }

  private static class SearchListingDisplay implements Display {
    private Account loggedIn;

    public SearchListingDisplay(Account account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(">Search Listings\n0: Exit program\n1: Return\n2: Continue to Search");
    }

    public Display option(int choice) {
      switch (choice) {
        case 1:
          return new SearchDisplay(loggedIn);
        case 2:
          System.out.println("Enter number of bedrooms (as an integer):");
          int bedrooms = scan.nextInt();
          System.out.println("Enter number of bathrooms (as an integer):");
          int bathrooms = scan.nextInt();
          scan.nextLine();
          ArrayList<String> filters = addFilters();
          Listing listing = new Listing("","", "", 0.0, false);
          listing.addBathrooms(bathrooms);
          listing.addBedrooms(bedrooms);
          for (String filter : filters) {
            listing.addFilter(filter);
          }

          ArrayList<Listing> searchResults = server.match(listing);
          if (searchResults.size() == 0) {
            System.out.println("No search results");
          } else {
            int counter = 1;
            for (Listing result : searchResults)
              System.out.println("Listing #" + counter + "\n" + result);
              counter++;
          }
          
          /*
          System.out.println("Enter the search result's listing number to generate a lease (enter 0 to not generate lease):");
          if (scan.hasNextInt()) {
            int listNumber = scan.nextInt();
            if (listNumber == 0) {
              return this;
            } else if (listNumber <= searchResults.size()) {
              if (loggedIn == null) {
                System.out.println("Sorry. Cannot generate lease unless logged in");
                return this;
              } else if (loggedIn.getClass() != StudentAccount.class) {
                System.out.println("Sorry this feature is only accessible to student accounts");
                return this;
              }
              System.out.println("Add tenants? (enter \"yes\" to add):");
              String response = scan.nextLine();
              ArrayList<StudentAccount> tenants = new ArrayList<StudentAccount>();
              tenants.add((StudentAccount)loggedIn);
              if ("yes".equalsIgnoreCase(response)) { 
                System.out.println("How many? (enter as an integer)");
                int numTenants = scan.nextInt();
                int i = 1;
                
                while (i <= numTenants) {
                  System.out.println("Enter username of tenant #" + i + " or enter \"exit\" to quit adding tenants");
                  String tenantResponse = scan.nextLine();
                  if ("exit".equalsIgnoreCase(tenantResponse))
                    break;
                  Account tenant = server.getAccount(tenantResponse);
                  if (tenant == null) {
                    System.out.println("Sorry account is not found");
                    i--;
                  } else if (tenant.getClass() != StudentAccount.class) {
                    System.out.println("Account is not a student account.");
                    i--;
                  } else {
                    tenants.add((StudentAccount)tenant);
                  }
                  i++;
                }  
              }
              Lease l = new Lease(tenants, searchResults.get(listNumber-1).getHost(), searchResults.get(listNumber-1));
              
            }
            
          }*/
          
          return this;
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class ListingDisplay implements Display {
    private HostAccount loggedIn;

    public ListingDisplay(HostAccount account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(
          ">Listing Manager\n0: Exit program\n1: Return\n2: Create new listing\n3: View Listings");
    }


    public Display option(int choice) {
      switch (choice) {
        case 1:
          return new HostAccountDisplay(loggedIn);
        case 2:
          System.out.println("Enter an address:");
          String address = scan.nextLine();
          System.out.println("Enter a name:");
          String name = scan.nextLine();
          System.out.println("Enter a rent price:");
          double rent = scan.nextDouble();

          Listing listing = new Listing(loggedIn, name, address, rent);

          System.out.println("Enter number of bedrooms:");
          listing.addBedrooms(scan.nextInt());
          System.out.println("Enter number of bathrooms:");
          listing.addBathrooms(scan.nextInt());
          System.out.println("Enter a description:");
          String description = scan.nextLine();
          listing.addDescription(description);
          ArrayList<String> filters = addFilters();
          for (String f : filters)
            listing.addFilter(f);
          server.addListing(listing);
          return this;
        case 3:
          ArrayList<Listing> properties = loggedIn.getOwnedProperties();
          if (properties.size() == 0) {
            System.out.println("No listings");
          } else {
            for (Listing l : properties) {
              System.out.println(l);
              System.out.println("___________________________");
            }
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
    myDriver.run();
  }
}
