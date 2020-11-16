import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentAccountTest {
    @Test
    public void testWriteListingReview() {
    	Listing listing = new Listing("", "Olympia Mills", "600 Heyward St", 1000, false);
    	StudentAccount student = new StudentAccount("rhylenn", "123456", "Rhylen", "Nguyen", "123456");
    	student.writeListingReview(listing, 5, "Good.");
    	
    	assertSame(listing.getReviews().get(0).getRating(), 5);
    	assertEquals(listing.getReviews().get(0).getWriter(), "Rhylen Nguyen");
    	assertEquals(listing.getReviews().get(0).getComment(), "Good.");
    }
    
    @Test
    public void testWriteHostReview() {
    	HostAccount host = new HostAccount("DylanO", "123456", "Dylan", "Ortuno");
    	StudentAccount student = new StudentAccount("NghiaN", "123456", "Nghia", "Nguyen", "123456");
    	
    	student.writeHostReview(host, 3, "Okay.");
    	
    	assertSame(1, host.getAccountReviews().size());
    	assertEquals(host.getAccountReviews().get(0).getWriter(), "Nghia Nguyen");
    	assertSame(host.getAccountReviews().get(0).getRating(), 3);
    	assertEquals(host.getAccountReviews().get(0).getComment(), "Okay.");
    	
    }

}
