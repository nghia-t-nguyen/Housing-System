import java.util.ArrayList;

public class MessageBox {
    private Account myUser;
    private ArrayList<Message> messages;
    private ArrayList<Account> contacts;

    public MessageBox(Account myUser){
        this.myUser = myUser;
    }

    public void addMessages(String text){
        messages.add(text);
    }

    public void showMessages(){}
}
