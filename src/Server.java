import java.util.TreeSet;
import java.util.*;

public class Server {
  private TreeSet<Account> accounts;
  private ArrayList<Listing> listings;
  private static Server server;

  /**
   * Creates a server that manages the accounts and listings
   */
  private Server() {
    accounts = new TreeSet<Account>();
    listings = new ArrayList<Listing>();
    
    listings = DataLoader.loadListings();
    accounts = DataLoader.loadAccounts();
  }

  /**
   * Gets the singular instance of a server
   * 
   * @return - the single instance of the server
   */
  public static Server getInstance() {
    if (server == null) {
      server = new Server();
    }
    return server;
  }

  /**
   * Tests to see if a username and password can login
   * 
   * @param username - username to log in
   * @param password - password to log in
   * @return - an integer that represents the whether the login is successful
   */
  public int login(String username, String password) {
    Account test = new HostAccount(username, password, "", "");

    if (!accounts.contains(test)) { // username is not found
      return -1;
    }
    if (password.hashCode() != this.getAccount(username).getPassword()) { // password is incorrect
      return -2;
    }

    return 0; // login is successful
  }

  /**
   * Gets all of the accounts from the server
   * 
   * @return - an arraylist with all of the accounts
   */
  /*public ArrayList<Account> getAllAccounts() {
    ArrayList<Account> ret = new ArrayList<Account>();

    Iterator<Account> iterator = accounts.iterator();
    while (iterator.hasNext()) {
      ret.add(iterator.next());
    }

    return ret;

  }*/
  
  public TreeSet<Account> getAllAccounts() {
	    return accounts;

	  }

  /**
   * Gets an instance of an account with the corresponding username
   * 
   * @param username - a String of the username of an account
   * @return - an account with the matching username
   */
  public Account getAccount(String username) {
    Iterator<Account> iterator = accounts.iterator();

    while (iterator.hasNext()) {
      Account account = iterator.next();
      if (username.equals(account.getUsername())) {
        return account;
      }
    }
    return null;
  }


  /**
   * Gets all of the listings from the server
   * 
   * @return - an arraylist with all of the listings from the server
   */
  public ArrayList<Listing> getAllListings() {
    return listings;
  }

  /**
   * Gets a listing based on the address
   * 
   * @param address - the address of a listing
   * @return - a listing with the matching address
   */
  public Listing getListing(String address) {
    Iterator<Listing> iterator = listings.iterator();

    while (iterator.hasNext()) {
      Listing listing = iterator.next();
      if (listing.getAddress().equals(address)) {
        return listing;
      }
    }

    return null;
  }


  /**
   * adds an account to the server
   * 
   * @param account - the account to be added to the server
   */
  public void addAccount(Account account) {
	  accounts.add(account);
	  //DataWriter.saveAccounts();
  }
    
  /**
   * adds a listing to the server
   * 
   * @param listing - the listing to be added to the server
   */
    public void addListing(Listing listing) {
	  listings.add(listing);
	  //DataWriter.saveListings();
    }

  /**
   * Searches the host by name
   * 
   * @param name - name of the host
   * @return an arraylist with the hosts with the matching name
   */
  public ArrayList<Account> searchHosts(String name) {
    ArrayList<Account> hosts = new ArrayList<Account>();

    for (Account account : accounts) {
      if (account.getClass() == HostAccount.class) {
        if (name.equalsIgnoreCase(account.getFirstName())
            || name.equalsIgnoreCase(account.getLastName())) {
          hosts.add(account);
        }
      }
    }

    return hosts;
  }

  /**
   * Searches listings for a specific address
   * 
   * @param address - the address of the listing
   * @return - an arraylist with the listings with the corresponding address
   */
  public ArrayList<Listing> searchListings(String address) {
    ArrayList<Listing> ret = new ArrayList<Listing>();

    for (Listing listing : listings) {
      if (address.equalsIgnoreCase(listing.getAddress())) {
        ret.add(listing);
      }
    }

    return ret;
  }

  /**
   * Returns the listings with specific filters
   * 
   * @param type - the type of filter the user is searching
   * @return - an arraylist with all of the listings matching the filter
   */
  public ArrayList<Listing> sortListings(String type) {
    ArrayList<Listing> ret = new ArrayList<Listing>();

    for (Listing listing : listings) {
      if (listing.getFilters().contains(type)) {
        ret.add(listing);
      }
    }

    return null;
  }
}
