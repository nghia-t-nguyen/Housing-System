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
      choice = getUserInput();
      if (choice == 0) {
        break;
      }

      display = display.option(choice);
    }
    System.out.println("Program terminated.");
    scan.close();

  }

  private static int getUserInput() {
    if (scan.hasNextInt()) {
      int ret = scan.nextInt();
      scan.nextLine();
      return ret;
    } else {
      scan.nextLine();
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
          //TODO: Delete case 4
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
          System.out.println("Password:");
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
    //TODO: Add favorites functionality
    public Display option(int choice) {
      switch (choice) {
        case 1:
          System.out.println("Logging out");
          return new DefaultDisplay(null);
        case 2:
          return new SearchDisplay(loggedIn);
        case 3:
          return new MessageDisplay(loggedIn);
        case 4:
          System.out.println(loggedIn.getProfile());
          return this;
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
          return new ListingManagerDisplay((HostAccount) loggedIn);
        case 4:
          return new MessageDisplay(loggedIn);
        case 5:
          System.out.println(loggedIn.getProfile());
          return this;
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
            System.out.println("Message sent.");
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
          .println(">Search: \n0: Exit program\n1: Return \n2: Search users\n3: Search listings");
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
          System.out.println("Enter name or username:");
          String name = scan.nextLine();
          ArrayList<Account> results = server.searchUsers(name);
          if (results.size() == 0) {
            System.out.println("No results found.");
          } else {
            for (int i = 0; i < results.size(); ++i) {
              Account account = results.get(i);
              System.out.println((i + 1) + "| @" + account.getUsername() + ": "
                  + account.getFirstName() + " " + account.getLastName());
            }
            System.out.println(
                "Would you like to view a profile, message, or view/write reviews for one of these users? (answer \"yes\" to continue)");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
              System.out.println("Enter the search result number (or 0 to return):");
              int resultNumber = scan.nextInt();
              scan.nextLine();
              if (resultNumber <= 0 || resultNumber > results.size()) {
                System.out.println("Invalid input.");
                return this;
              } else if (resultNumber == 0) {
                System.out.println("Returning to search menu");
                return this;
              } else {
                return new AccountActionDisplay(loggedIn, results.get(resultNumber - 1));
              }
            }
          }
          System.out.println("Returning to search menu");
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
      System.out.println(">Search Listings\n0: Exit program\n1: Return\n2: Find your match\n3: Search by name or address");
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
          Listing listing = new Listing("", "", "", 0.0, false);
          listing.addBathrooms(bathrooms);
          listing.addBedrooms(bedrooms);
          for (String filter : filters) {
            listing.addFilter(filter);
          }

          ArrayList<Listing> matchResults = server.match(listing);
          if (matchResults.size() == 0) {
            System.out.println("No search results.");
          } else {
            for (int i = 1; i <= matchResults.size(); ++i) {
              System.out.println("Listing #" + i + " **********************\n" + matchResults.get(i-1) + "\n");
            }
            System.out.println(
                "Would you like to view a generate a lease, message the host, or view/write a review for"
                + " one of these listings? (answer \"yes\" to continue)");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
              System.out.println("Enter the search result number (or 0 to return):");
              int resultNumber = getUserInput();
              //System.out.println("Result number:" + resultNumber);
              if (resultNumber < 0 || resultNumber > matchResults.size()) {
                System.out.println("Invalid input.");
                return this;
              } else if (resultNumber == 0) {
                System.out.println("Returning to search menu");
                return this;
              } else {
                return new ListingActionDisplay(loggedIn, matchResults.get(resultNumber - 1));
              }
            }
          }
          System.out.println("Returning to search menu.");
          return this;
        case 3:
          System.out.println("Enter the name or address:");
          String keyword = scan.nextLine();
          ArrayList<Listing> searchResults = server.searchListings(keyword);
          if (searchResults.size() == 0) {
            System.out.println("No search results.");
          } else {
            for (int i = 1; i <= searchResults.size(); ++i) {
              System.out.println("Listing #" + i + " **********************\n" + searchResults.get(i-1) + "\n");
            }
            System.out.println(
                "Would you like to view a generate a lease, message the host, or view/write a review for"
                + " one of these listings? (answer \"yes\" to continue)");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
              System.out.println("Enter the search result number (or 0 to return):");
              int resultNumber2 = getUserInput();
              if (resultNumber2 < 0 || resultNumber2 > searchResults.size()) {
                System.out.println("Invalid input.");
                return this;
              } else if (resultNumber2 == 0) {
                System.out.println("Returning to search menu");
                return this;
              } else {
                return new ListingActionDisplay(loggedIn, searchResults.get(resultNumber2 - 1));
              }
            }
          }

          System.out.println("Returning to search menu.");
          return this;
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class ListingManagerDisplay implements Display {
    private HostAccount loggedIn;

    public ListingManagerDisplay(HostAccount account) {
      loggedIn = account;
    }

    public void display() {
      System.out.println(
          ">Listing Manager\n0: Exit program\n1: Return\n2: Create new listing\n3: View Listings");
      //TODO generate lease
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
          scan.nextLine();
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
            for (int i = 1; i <= properties.size(); ++i) {
              System.out.println("Listing #" + i + " **********************\n" + properties.get(i-1) + "\n");
            }
            System.out.println(
                "Would you like to view change rented status or generate a lease for a particular listing? (answer \"yes\" to continue)");
            if (scan.nextLine().equalsIgnoreCase("yes")) {
              System.out.println("Enter the listing number (or 0 to return):");
              Listing selectedListing = null;
              int listingNumber = getUserInput();
              if (listingNumber < 0 || listingNumber > properties.size()) {
                System.out.println("Invalid input.");
                return this;
              } else if (listingNumber == 0) {
                System.out.println("Returning to Listing Manager");
                return this;
              } else {
                selectedListing = properties.get(listingNumber-1);
                System.out.println(selectedListing.getName() + " at " + selectedListing.getAddress());
                System.out.println("Would you like to change rented status? (answer \"yes\" to change rented status)");
                if (scan.nextLine().equalsIgnoreCase("yes")) {
                  selectedListing.changeRented();
                  DataWriter.saveListings();
                }
                System.out.println("Would you like to generate a lease? (answer \"yes\" to generate lease)");
                if (scan.nextLine().equalsIgnoreCase("yes")) {
                  System.out.println("Enter student account username:");
                  String username = scan.nextLine();
                  Account account = server.getAccount(username);
                  if (account == null) {
                    System.out.println("User does not exist");
                    return this;
                  } else if (account.getClass() == HostAccount.class) {
                    System.out.println("User is not a student account.");
                    return this;
                  } else {
                    Lease lease = generateLease((StudentAccount)account, loggedIn, selectedListing);
                    lease.printToFile("Lease.txt");
                  }
                }
              }
            }
          }
          return this;
        default:
          System.out.println("Invalid input");
          return this;
      }
    }
  }

  private static class AccountActionDisplay implements Display {
    private Account loggedIn;
    private Account other;

    public AccountActionDisplay(Account loggedIn, Account other) {
      this.loggedIn = loggedIn;
      this.other = other;
    }

    public void display() {
      System.out.println("Account: @" + other.getUsername());
      System.out.println(
          ">What would you like to do?\n0: Exit program\n1: Return\n2: View Profile\n3: Message\n4: View Reviews\n5: Leave Review");
    }

    public Display option(int choice) {
      switch (choice) {
        case 1:
          return new SearchDisplay(loggedIn);
        case 2:
          System.out.println(other.getProfile());
          return this;
        case 3:
          if (loggedIn == null) {
            System.out.println("Log in to message other users.");
            return this;
          }
          System.out.println("Enter message:");
          String message = scan.nextLine();
          loggedIn.getMessageBox().sendMessage(other, message);
          DataWriter.saveAccounts();
          System.out.println("Message sent.");
          return this;
        case 4:
          if (other.getAccountReviews().size() == 0) {
            System.out.println("No reviews.");
          } else {
            for (Review review : other.getAccountReviews()) {
              System.out.println(review + "\n");
            }
          }
          return this;
        case 5:
          if (loggedIn == null) {
            System.out.println("Log in to leave reviews.");
            return this;
          } else if (loggedIn.getClass() == other.getClass()) {
            String accountType = "";
            if (loggedIn.getClass() == HostAccount.class)
              accountType = "host";
            else
              accountType = "student";
            System.out.println("A " + accountType + " cannot leave a review for a " + accountType + ".");
            return this;
          }
          System.out.println("Enter rating (1-5)");
          int rating = getUserInput();
          if (rating < 1 || rating > 5) {
            System.out.println("Invalid input.");
            return this;
          }
          System.out.println("Enter text:");
          String text = scan.nextLine();
          other.addAccountReview(new Review(loggedIn.getUsername(), rating, text));
          DataWriter.saveAccounts();
        default:
          System.out.println("Invalid input.");
          return this;
      }
    }
  }

  private static class ListingActionDisplay implements Display {
    private Account loggedIn;
    private Listing listing;

    public ListingActionDisplay(Account loggedIn, Listing listing) {
      this.loggedIn = loggedIn;
      this.listing = listing;
    }

    public void display() {
      System.out.println("Listing: " + listing.getName() + " at " + listing.getAddress());
      System.out.println(">What would you like to do?\n0: Exit program\n1: Return\n2: Message the host\n3: View reviews\n4: Leave a review\n5: Generate a lease");
    }

    public Display option(int choice) {
      switch(choice) {
        case 1:
          return new SearchListingDisplay(loggedIn);
        case 2:
          if (loggedIn == null) {
            System.out.println("Log in to message other users.");
            return this;
          }
          System.out.println("Enter message:");
          String message = scan.nextLine();
          loggedIn.getMessageBox().sendMessage(listing.getHost(), message);
          DataWriter.saveAccounts();
          System.out.println("Message sent.");
          return this;
        case 3:
          if (listing.getReviews().size() == 0) {
            System.out.println("No reviews.");
          } else {
            for (Review review : listing.getReviews()) {
              System.out.println(review + "\n");
            }
          }
          return this;
        case 4:
          if (loggedIn == null) {
            System.out.println("Log in to leave reviews.");
            return this;
          }
          System.out.println("Enter rating (1-5)");
          int rating = getUserInput();
          if (rating < 1 || rating > 5) {
            System.out.println("Invalid input.");
            return this;
          }
          System.out.println("Enter text:");
          String text = scan.nextLine();
          listing.addReview(new Review(loggedIn.getUsername(), rating, text));
          DataWriter.saveAccounts();
          DataWriter.saveListings();
          return this;
        case 5:
          if (loggedIn == null) {
            System.out.println("Cannot generate a lease if not logged in as a student");
            return this;
          } else if (loggedIn.getClass() == HostAccount.class) {
            System.out.println("Hosts cannot generate a lease through this function.");
            return this;
          }
          Lease lease = generateLease((StudentAccount)loggedIn, listing.getHost(), listing);
          lease.printToFile("Lease.txt");
          return this;
        default:
          System.out.println("Invalid input.");
          return this;
      }
    }
  }

  private static Lease generateLease(StudentAccount student, HostAccount landLord, Listing listing) {
    System.out.println("Would you like to add other tenants? (\"yes\" to add)");
    ArrayList<StudentAccount> tenants = new ArrayList<StudentAccount>();
    tenants.add(student);
    if (scan.nextLine().equalsIgnoreCase("yes")) {
      System.out.println("How many? (enter as an integer)");
      int numTenants = getUserInput();
      int i = 1;
      while (i <= numTenants) {
        System.out.println("Enter the username of tenant #" + i + " or enter \"exit\" to quit adding tenants");
        String response = scan.nextLine();
        if (response.equalsIgnoreCase("exit")) {
          break;
        }
        Account tenant = server.getAccount(response);
        if (tenant == null) {
          System.out.println("Sorry, that account is not found");
          i--;
        } else if (tenant.getClass() != StudentAccount.class) {
          System.out.println("Account is not a student account");
          i--;
        } else {
          tenants.add((StudentAccount)tenant);
        }
        i++;
      }
    } else {
    	System.out.println("Else");
    }
    Lease ret = new Lease(tenants, landLord, listing);
    ret.addDuration();
    ret.addTenantAct();
    ret.addProperty();
    ret.addPaymentTerm();
    ret.addPayment();
    ret.addPaymentAddress();
    ret.addDamages(300);
    ret.addSignatures();
    return ret;
  }

  public static void main(String[] args) {
    Driver myDriver = new Driver();
    myDriver.run();
  }
}
