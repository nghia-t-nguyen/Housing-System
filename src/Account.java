import java.lang.Comparable;
import java.util.ArrayList;

/**
 * Represents a user account
 * @author Nghia Nguyen
 *
 */
public abstract class Account implements Comparable<Account>{
  protected String username;
  protected int hashedPassword;
  protected String firstName;
  protected String lastName;
  protected MessageBox messagebox;
  protected ArrayList<Review> accountReviews;
 
  public Account(String username, String password, String firstName, String lastName) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.hashedPassword = password.hashCode();
    messagebox = new MessageBox(this);
    accountReviews = new ArrayList<Review>();
  }
  
  public Account(String username, int hashedPassword, String firstName, String lastName) {
	    this.username = username;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.hashedPassword = hashedPassword;
	    messagebox = new MessageBox(this);
	    accountReviews = new ArrayList<Review>();
	  }
  
  public boolean equals(Account other) {
    if (username.equals(other.getUsername())) {
      return true;
    }
    return false;
  }
  
  public int compareTo(Account other) {
    return username.compareToIgnoreCase(other.getUsername());
  }
  
  public void receiveMessage(String text) {
    messagebox.addMessage(text);
  }
  
  public MessageBox getMessageBox() {
    return messagebox;
  }
  
  public ArrayList<Review> getAccountReviews() {
	  return accountReviews;
  }
  
  public void addAccountReview(Review review) {
	  accountReviews.add(review);
  }
  
  public String getUsername() {
    return username;
  }
  
  public int getPassword() {
    return hashedPassword;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
}
