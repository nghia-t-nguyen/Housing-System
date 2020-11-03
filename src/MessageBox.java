import java.util.ArrayList;

public class MessageBox {
  private Account myUser;
  private ArrayList<String> messages;

/**
* Class MessageBox with private instance variables myUser and messages
*/

  public MessageBox(Account myUser){
    this.myUser = myUser;
    messages = new ArrayList<String>();
  }

/**
* Constructor for MessageBox
*/

  public void sendMessage(Account to, String text) {
    to.receiveMessage("From: " + myUser.getUsername() + "\n\n" + text);
    myUser.receiveMessage("To: " + to.getUsername() + "\n\n" + text);
  }

/**
* sendMessage calls upon receiveMessage to setup the sender of the message and recieveMessage again to establish the reciever
*/
  
  public void addMessage(String text){
    messages.add(text);
  }

/**
* addMessgae adds String text to messages
*/

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

/**
* showMessages displays to use whether or not they have messages and if they do, what their messages say. If none are
* in their inbox, "No messages" is displayed. Otherwise, they will see in order from most recent to oldest their messages
*/
  
  public ArrayList<String> getMessages() {
    return messages;
  }

/**
* getMessages returns messages of ArrayList type String
*/

}
