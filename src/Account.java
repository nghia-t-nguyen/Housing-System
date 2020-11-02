import java.lang.Comparable;
import java.util.ArrayList;

/**
 * The Account class is used for Account object creation and implements
 * Comparable<Account>. It declares a Review object ArrayList, a messageBox
 * ArrayList, and various String and int private variables. 
 * Created by Team QUEBEC
 */
public abstract class Account implements Comparable<Account> {
	protected String username;
	protected int hashedPassword;
	protected String firstName;
	protected String lastName;
	protected MessageBox messagebox;
	protected ArrayList<Review> accountReviews;

	/**
	 * The first constructor for the Account class takes in several Strings and
	 * assigns them to various private variables within the class. It then uses
	 * .hashCode on the password String to created hashedPassword. It also declares
	 * a new MessageBox and declares the accountReviews ArrayList. 
	 * Created by Team QUEBEC
	 */
	public Account(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hashedPassword = password.hashCode();
		messagebox = new MessageBox();
		accountReviews = new ArrayList<Review>();
	}

	/**
	 * The first constructor for the Account class takes in several Strings and an
	 * int and assigns them to various private variables within the class. It then
	 * takes hashedPassword and assigns it with an int. declares a new MessageBox
	 * and declares the accountReviews ArrayList. 
	 * Created by Team QUEBEC
	 */
	public Account(String username, int hashedPassword, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hashedPassword = hashedPassword;
		messagebox = new MessageBox();
		accountReviews = new ArrayList<Review>();
	}

	/**
	 * The equals method takes in an Account object checks if it's username variable
	 * is the same as another username variable. It then returns true and if they
	 * aren't the same, it returns false. 
	 * Created by Team QUEBEC
	 */
	public boolean equals(Account other) {
		if (username.equals(other.getUsername())) {
			return true;
		}
		return false;
	}

	/**
	 * The compareTo method takes in an account object and compares it's username
	 * variable with that of another username variable. 
	 * Created by Team QUEBEC
	 */
	public int compareTo(Account other) {
		return username.compareToIgnoreCase(other.getUsername());
	}

	/**
	 * The recieveMessage takes in a String and uses addMessage on messageBox while
	 * passing the String. 
	 * Created by Team QUEBEC
	 */
	public void receiveMessage(String text) {
		messagebox.addMessage(text);
	}

	/**
	 * The getMessageBox method returns the messageBox object. 
	 * Created by Team QUEBEC
	 */
	public MessageBox getMessageBox() {
		return messagebox;
	}

	/**
	 * The getAccountReviews creates a new ArrayList and uses a loop to go through
	 * accountReviews and add it's contents to the new ArrayList. It then returns
	 * the new ArrayList. 
	 * Created by Team QUEBEC
	 */
	public ArrayList<Review> getAccountReviews() {
		ArrayList<Review> reviewsList = new ArrayList<>();
		for (Review review : accountReviews) {
			reviewsList.add(review);
		}
		return reviewsList;
	}

	/**
	 * The addAccountReview method takes in a Review object and adds it to the
	 * accountReviews ArrayList. 
	 * Created by Team QUEBEC
	 */
	public void addAccountReview(Review review) {
		accountReviews.add(review);
	}

	/**
	 * The getUsername method returns the username String variable. 
	 * Created by Team QUEBEC
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * The getPassword method returns the hashedPassword int variable. 
	 * Created by Team QUEBEC
	 */
	public int getPassword() {
		return hashedPassword;
	}

	/**
	 * The getFirstName method returns the firstName String variable. 
	 * Created by Team QUEBEC
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * The getLastName method returns the lastName String variable. 
	 * Created by Team QUEBEC
	 */
	public String getLastName() {
		return lastName;
	}
}
