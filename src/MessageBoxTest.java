import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * MessageBox Unit Testing
 * @Rhylen 'Jennifer' Nguyen
 */

class MessageBoxTest {
	private ArrayList<Account> accounts =new ArrayList<Account>();;
	private MessageBox messageBox;
	
	@BeforeEach
	public void setUp() throws Exception {
		accounts.clear();
		accounts.add(new StudentAccount("aBaker","12345" , "Amy", "Baker", "A01"));	
		accounts.add(new StudentAccount("pParker","12345" , "Peter", "Parker", "A02"));	
		accounts.add(new HostAccount("rLauren","12345" , "Ralph", "Lauren"));	
		accounts.add(new HostAccount("sHwang","12345" , "Sam", "Hwang"));	
		
		messageBox = new MessageBox(accounts.get(0));
	}

	@AfterEach
	public void tearDown() throws Exception {
		accounts.clear();
	}

	@Test
	public void testMessageZero() {
		assertEquals(messageBox.getMessages().size(), 0);
	}
	
	@Test
	public void testAddMessage() {
		String[] expected = {"abcdefghij", "123456789", "a1b2c3d4e5f6g7h8"};
		
		for (String message:expected) {
			messageBox.addMessage(message);
		}

		String[] actual = new String[3];
		for (int i=0; i< messageBox.getMessages().size(); ++i) {
			actual[i] = messageBox.getMessages().get(i);
		}
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testSendMessage() {
		String sent = "Hello Sam!";
		messageBox.sendMessage(accounts.get(3), sent);
		MessageBox samBox = accounts.get(3).getMessageBox();
		MessageBox amyBox = accounts.get(0).getMessageBox();
		
		assertEquals(("From: aBaker\n\n" + sent), samBox.getMessages().get(0));
		assertEquals(("To: sHwang\n\n" + sent), amyBox.getMessages().get(0));
		
	}
	
	@Test
	public void testEmptyMessage() {
		String sent = "";
		messageBox.addMessage(sent);
		
		assertEquals(sent,messageBox.getMessages().get(0));
	}

}
