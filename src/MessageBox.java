import java.util.ArrayList;

public class MessageBox {
  private Account myUser;
  private ArrayList<String> messages;

  public MessageBox(Account myUser){
    this.myUser = myUser;
    messages = new ArrayList<String>();
  }

  public void sendMessage(Account to, String text) {
    to.receiveMessage("From: " + myUser.getUsername() + "\n\n" + text);
    myUser.receiveMessage("To: " + to.getUsername() + "\n\n" + text);
  }
  
  public void addMessage(String text){
    messages.add(text);
  }

  public void showMessages(){
    if (messages.size() == 0) {
      System.out.println("No messages.");
      return;
    }
    System.out.println("\nMessages:\n");
    for (String message : messages) { //print most recent to oldest 
      System.out.println(message + "\n_______________________");
    }
  }
  
  public ArrayList<String> getMessages() {
    return messages;
  }
}
