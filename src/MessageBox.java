import java.util.ArrayList;

public class MessageBox {
  private Account myUser;
  private ArrayList<String> messages;

  public MessageBox(Account myUser){
    this.myUser = myUser;
    messages = new ArrayList<String>();
  }

  public void sendMessage(Account to, String text) {
    to.receiveMessage("From: " + myUser.toString() + "\n" +text);
  }
  
  public void addMessage(String text){
    messages.add(text);
  }

  public void showMessages(){
    System.out.println("\nMessages:\n");
    for (int i = messages.size(); i >= 0; i--) { //print most recent to oldest 
      String message = (String) messages.get(i);
      System.out.println(message + "\n_______________________");
    }
  }
  
  public ArrayList<String> getMessages() {
    return messages;
  }
}
