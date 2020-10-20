import java.util.ArrayList;

public class HostAccount extends Account {
  private ArrayList<Listing> ownedProperties;
  private ArrayList<Review> hostReviews;

  public HostAccount(String username, String password, String firstName, String lastName) {
    super(username, password, firstName, lastName);
  }
  
  public void addHostReview(Review review) {}
  
  public void writeRenterReview(StudentAccount subject, int rating, String text) {}}

  public ArrayList<Review> getReviews() {
    return hostReviews;
  }
  
  public void addProperty(Listing listing) {}
}
