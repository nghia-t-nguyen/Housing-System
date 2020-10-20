import java.util.TreeSet;
import java.util.ArrayList;

public class Server {
  private TreeSet<Account> accounts;
  private ArrayList<Listing> listings;
  private static Server server;
  
  private Server() {
    accounts = new TreeSet<Account>();
    listings = new ArrayList<Listing>();
  }
  
  public static Server getInstance() {
    if (server == null) {
      server = new Server();
    }
    return server;
  }
  
  public ArrayList<Account> getAllAccounts() {
    return null;
  }
  
  public Account getAccount(String username) {
    return null;
  }

  public ArrayList<Listing >getAllListings() {
    return null;
  }
  
  public Listing getListing(String address) {
    return null;
  }
  
  public void addAccount(Account account) {}
  
  public void addListing(Listing listing) {}
  
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
