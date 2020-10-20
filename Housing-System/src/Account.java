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
    if (username.equals(other.getUsername())) {
      return true;
    }
    return false;
  }
  
  public int compareTo(Account other) {
    return username.compareToIgnoreCase(other.getUsername());
  }
  
  public void receiveMessage(String text) {
    if (messagebox != null) {
      messagebox.addMessage(text);
    }
  }
  
  public String getUsername() {
    return username;
  }
  
  public int getPassword() {
    return hashedPassword;
  }
}