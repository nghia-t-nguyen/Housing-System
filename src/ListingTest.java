import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListingTest {
    private Listing listing;
    private HostAccount host;
    private String[] filters;

    @BeforeEach
    public void setup(){
    	host = new HostAccount("cBang", "12345", "Chris", "Bang");
        listing = new Listing(host, "Academic Towers", "123 School LN", 1200);
        
        filters = new String[5];
        filters[0] = "cats";
        filters[1] = "dogs";
        filters[2] = "washer";
        filters[3] = "dryer";
        filters[4] = "parking";
    }

    @AfterEach
    public void tearDown(){
       listing = null;
    }

    @Test
   public void testAddDescription() {
    	Listing newListing = listing;
    	listing.addDescription("Hello");
    	assertEquals("Hello",listing.getDescription());
    }
    
    @Test
    public void testGetAverageRating() {
     	listing.addReview(new Review(null, 5, ""));
     	listing.addReview(new Review(null, 2, ""));
     	listing.addReview(new Review(null, 3, ""));
     	listing.addReview(new Review(null, 2, ""));
     	listing.addReview(new Review(null, 3, ""));
     	
     	double expected = 3;
     	
     	assertEquals(expected, listing.getAverageRating());
     }
    
    @Test
    public void testChangeRented() {
    	listing.changeRented();
    	assertEquals(true,listing.isRented());
    	listing.changeRented();
    	assertEquals(false,listing.isRented());
    }
    
    @Test
    public void testToString() {
    	for(String s : filters) {
    		listing.addFilter(s);
    	}
    	String expected = "Academic Towers\nAddress: 123 School LN\nHost: cBang\nAverage Rating: No ratings\nDescription: n/a\nRented: No\nRent: 1200.0\nBedrooms: n/a\nBathrooms: n/a\nFilters: #cats #dogs #washer #dryer #parking";
    	assertEquals(expected, listing.toString());
    }
}
    