import java.lang.Comparable;

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
 
  public Account(String username, String password, String firstName, String lastName) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.hashedPassword = password.hashCode();
    messagebox = null;
  }
  
  public boolean equals(Account other) {
    return true;
  }
  
  public int compareTo(Account other) {
    return 0;
  }
  
  public void receiveMessage(String text) { }
  
  public int getPassword() {
    return hashedPassword;
  }
}
