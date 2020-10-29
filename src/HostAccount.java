import java.util.ArrayList;

public class HostAccount extends Account {
  private ArrayList<Listing> ownedProperties;
  private ArrayList<Review> hostReviews;

  public HostAccount(String username, String password, String firstName, String lastName) {
    super(username, password, firstName, lastName);
    ownedProperties = new ArrayList<Listing>();
    hostReviews = new ArrayList<Review>();
  }
  
  public HostAccount(String username, int hashedPassword, String firstName, String lastName) {
	    super(username, hashedPassword, firstName, lastName);
	    ownedProperties = new ArrayList<Listing>();
	    hostReviews = new ArrayList<Review>();
	  }
  
  public ArrayList<Listing> getOwnedProperties() {
	return ownedProperties;
}

public void addHostReview(Review review) {}
  
  public void writeRenterReview(StudentAccount subject, int rating, String text) {}

  public ArrayList<Review> getReviews() {
    return hostReviews;
  }
  
  public void addProperty(Listing listing) {}
}
