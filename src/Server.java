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
   * @return - a TreesSet with all of the accounts
   */
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
   * Gets a listing based on the address and name
   * Have to create a Listing with the same address and name to use
   *
   * @param address - the address of a listing
   * @return - a listing with the matching address
   */
  public Listing getListing(String name, String address) {
    Iterator<Listing> iterator = listings.iterator();

    while (iterator.hasNext()) {
      Listing listing = iterator.next();
      if (listing.getAddress().equals(address) && listing.getName().equals(name)) {
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
	  DataWriter.saveAccounts();
  }


  /**
   * adds a listing to the server
   *
   * @param listing - the listing to be added to the server
   */
  public void addListing(Listing listing) {
	  listings.add(listing);
	  DataWriter.saveListings();
	  DataWriter.saveAccounts();
  }


  /**
   * Searches users by name
   *
   * @param name - name of the host, either username or name
   * @return an arraylist with the hosts with the matching name
   */
  public ArrayList<Account> searchUsers(String name) {
    ArrayList<Account> matches = new ArrayList<Account>();

    String lowercaseName = name.toLowerCase();
    for (Account account : accounts) {
    	String fullName = account.getFirstName() + account.getLastName();
    	fullName = fullName.toLowerCase();
        if (fullName.contains(lowercaseName)) {
        	matches.add(account);
        } else if (account.getUsername().contains(name)) {
        	matches.add(account);
        }
    }

    return matches;
  }

  /**
   * Searches listings for a keyword that compares the address or name
   *
   * @param keyword - the name of the listing or address to search up
   * @return - an arraylist with the listings with the corresponding keyword
   */
  public ArrayList<Listing> searchListings(String keyword) {
    ArrayList<Listing> ret = new ArrayList<Listing>();
    keyword = keyword.toUpperCase();

    for (Listing listing : listings) {
      if (listing.getAddress().toUpperCase().contains(keyword) || listing.getName().toUpperCase().contains(keyword)) {
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

  private ArrayList<Listing> intersection(ArrayList<Listing> list1, ArrayList<Listing> list2) {
    ArrayList<Listing> list = new ArrayList<Listing>();

    for (Listing listing : list1) {
        if(list2.contains(listing)) {
            list.add(listing);
        }
    }
    return list;
  }

  public ArrayList<Listing> match(Listing listing) {
    ArrayList<Listing> bedAndBathMatch = new ArrayList<Listing>();
    for (Listing l : listings) {
      if ((l.getBedrooms() == listing.getBedrooms()) && (l.getBathrooms() == listing.getBathrooms()))
        bedAndBathMatch.add(l);
    }
    ArrayList<Listing> filterMatch = new ArrayList<Listing>();
    for (Listing l : listings) {
      if (l.getFilters().containsAll(listing.getFilters())) {
        filterMatch.add(l);
      }
    }

    return intersection(bedAndBathMatch, filterMatch);
  }
}
