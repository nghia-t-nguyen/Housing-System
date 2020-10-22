import java.util.TreeSet;
import java.util.ArrayList;

public class Server {
  private TreeSet<Account> accounts;
  private ArrayList<Listing> listings;
  private static Server server;
  
  private Server() {
    accounts = new TreeSet<Account>();
    listings = new ArrayList<Listing>();
    
    accounts = DataLoader.loadAccounts();
    listings = DataLoader.loadListings();
  }
  
  public static Server getInstance() {
    if (server == null) {
      server = new Server();
    }
    return server;
  }
  
  public TreeSet<Account> getAllAccounts() {
    return accounts;
  }
  
  public Account getAccount(String username) {
    return null;
  }

  public ArrayList<Listing >getAllListings() {
    return listings;
  }
  
  public Listing getListing(String address) {
    return null;
  }
  
  public void addAccount(Account account) {
	  accounts.add(account);
	  DataWriter.saveAccounts();
  }
  
  public void addListing(Listing listing) {
	  listings.add(listing);
	  DataWriter.saveListings();
  }
  
  public ArrayList<HostAccount> searchHosts(String name) {
    return null;
  }
  
  public ArrayList<Listing> searchListings(String address) {
    return null;
  }
  
  public ArrayList<Listing> sortListings(String type) {
    return null;
  }
}
