import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
	private Server server = Server.getInstance();
	
	public void setup() {
		server.getAllAccounts().clear();
		DataWriter.saveAccounts();
	}
	
	
	
	
	
}
