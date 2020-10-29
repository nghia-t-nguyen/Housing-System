import java.util.ArrayList;

public class HostAccount extends Account {
  private ArrayList<Listing> ownedProperties;

  public HostAccount(String username, String password, String firstName, String lastName) {
    super(username, password, firstName, lastName);
    ownedProperties = new ArrayList<Listing>();
  }
  
  public HostAccount(String username, int hashedPassword, String firstName, String lastName) {
	    super(username, hashedPassword, firstName, lastName);
	    ownedProperties = new ArrayList<Listing>();
	  }
  
  public ArrayList<Listing> getOwnedProperties() {
	return ownedProperties;
}

  
  public void writeRenterReview(StudentAccount subject, int rating, String text) {}
  
  public void addProperty(Listing listing) {}
}
