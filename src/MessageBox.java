import java.util.ArrayList;

public class MessageBox {
    private Account myUser;
    private ArrayList<Message> messages;
    private ArrayList<Account> contacts;

    public MessageBox(Account myUser){
        this.myUser = myUser;
    }

    public void addMessage(String text){
        messages.add(new Message(myUser,text));
    }

    public void showMessages(){}
}
