import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentAccountTest {
    private Server server = new Server();

    @BeforeEach
    public void setup()
    {
        StudentAccount.getInstance().getStudentAccount.clear();
        DataWriter.saveAccounts();
    }

    @AfterEach
    public void teardown()
    {
        StudentAccount.getInstance().getStudentAccount.clear();
        DataWriter.saveAccounts();
    }

    @Test
    public void testCreateValidStudentAccount()
    {
        server.addAccount("joesmo2", "12345", "john", "trump");
        server.login("joesmo2", "12345");
        assertEquals("joesmo2", server.);
    }

    @Test
    public void testCreateNullStudentAccount()
    {
        server.addAccount("", "0", "", "");
        server.login("", "");
        assertEquals("joesmo2", server.);
    }

    @Test
    public void testEmptyReview()
    {
        boolean isCreated = server.StudentAccount.addReview("", 0, "");
        assertFalse(isCreated);
    }
    @Test
    public void testCreateLogin()
    {
        boolean isCreated = server.login("", "");
        assert False(isCreated);
    }

}
