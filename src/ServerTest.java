import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class ServerTest {
	private Server server = Server.getInstance();
	
	@BeforeEach
	public void setUp() {
	  server.getAllAccounts().clear();
	  server.getAllListings().clear();
	  DataWriter.saveAccounts();
	}
	
	@AfterEach
	public void tearDown() {
	  server.getAllAccounts().clear();
	  server.getAllListings().clear();
	  DataWriter.saveAccounts();
	}
	
	@Test
	public void testLoginValid() {
	  server.addAccount(new HostAccount("nghian", "123456", "Nghia", "Nguyen"));
	  assertSame(0, server.login("nghian", "123456"));
	}
	
	@Test
	public void testLoginIncorrectUsername() {
	   server.addAccount(new HostAccount("nghian", "123456", "Nghia", "Nguyen"));
	   assertSame(-1, server.login("nghia", "123456"));
	}
	
	@Test
	public void testLoginNoUsernameFound() {
	  assertSame(-1, server.login("Portia", "123456"));
	}
	
	@Test
	public void testLoginIncorrectPassword() {
	   server.addAccount(new HostAccount("nghian", "123456", "Nghia", "Nguyen"));
	   assertSame(-2, server.login("nghian", "1234567"));
	}
	
	@Test
	public void testGetAccountValid() {
	  Account anAccount = new HostAccount("DylanO", "123456", "Dylan", "Ortuno");
	  server.addAccount(anAccount);
	  assertEquals(anAccount, server.getAccount("DylanO"));
	}
	
	
	@Test
	public void testGetListingValid() {
	  HostAccount host = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
	  server.addAccount(host);
	  String name = "Olympia Mills";
	  String address = "600 Heyward St, Columbia, SC";
	  Listing listing = new Listing(host, name, address, 0);
	  server.addListing(listing);
	  assertEquals(server.getListing(name, address).getName(), name);
	  assertEquals(server.getListing(name, address).getAddress(), address);
	}
	
	@Test
	public void testSearchUsersSubstringInFullName() {
	  HostAccount person1 = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
	  HostAccount person2 = new HostAccount("RhylenN", "123456", "Rhylen", "Nguyen");
	  HostAccount person3 = new HostAccount("DylanO", "123456", "Dylan", "Ortuno");
	  
	  server.addAccount(person1);
	  server.addAccount(person2);
	  server.addAccount(person3);
	  
	  
	  // Searching "ng" which should result in "RhylenN" and "nghian" since both have "Ng" in lastname
	  ArrayList<Account> expected = new ArrayList<Account>();
	  expected.add(person1);
	  expected.add(person2);
	  
	  ArrayList<Account> observed = server.searchUsers("ng");
	  assertSame(expected.size(), observed.size());
	  for (Account a : observed) {
	    assertTrue(expected.contains(a));
	  }
	}
	
	@Test
	public void testSearchUsersSubstringInUsername() {
      HostAccount person1 = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      HostAccount person2 = new HostAccount("RhylenN", "123456", "Rhylen", "Nguyen");
      HostAccount person3 = new HostAccount("DylanO", "123456", "Dylan", "Ortuno");
      
      server.addAccount(person1);
      server.addAccount(person2);
      server.addAccount(person3);
      
      // Searching for "nn" since only RhylenN has "nN" as a substring in her username
      ArrayList<Account> expected = new ArrayList<Account>();
      expected.add(person2);
      
      ArrayList<Account> results = server.searchUsers("nn");
      
      assertSame(1, results.size());
      assertEquals(expected.get(0), results.get(0));
	}
	
	@Test
	public void testSearchUsersNoResultsFound() {
      HostAccount person1 = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      HostAccount person2 = new HostAccount("RhylenN", "123456", "Rhylen", "Nguyen");
      HostAccount person3 = new HostAccount("DylanO", "123456", "Dylan", "Ortuno");
      
      server.addAccount(person1);
      server.addAccount(person2);
      server.addAccount(person3);
      
      assertSame(0, server.searchUsers("z").size());
	}
	
	@Test
	public void testSearchListingsSubstringInAddress() {
	  HostAccount nghia = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
	  server.addAccount(nghia);
	  
	  Listing listing1 = new Listing(nghia, "Olympia Mills", "600 Heyward St", 1000);
	  Listing listing2 = new Listing(nghia, "Honors Residence", "1215 Blossom St", 1000);
	  Listing listing3 = new Listing(nghia, "650 Lincoln", "650 Lincoln St", 1000);
	  Listing listing4 = new Listing(nghia, "Granby Mills", "1010 Jimmy Carter Boulevard", 1000);
	  
	  server.addListing(listing1);
	  server.addListing(listing2);
	  server.addListing(listing3);
	  server.addListing(listing4);
	  
	  ArrayList<Listing> expected = new ArrayList<Listing>();
	  expected.add(listing1);
	  expected.add(listing2);
	  expected.add(listing3);
	  
	  ArrayList<Listing> results = server.searchListings("St");
	  
	  assertSame(expected.size(), results.size());
	  for (int i = 0; i < results.size(); i++) {
	    assertEquals(expected.get(i).getName(), results.get(i).getName());
        assertEquals(expected.get(i).getAddress(), results.get(i).getAddress());
	  }
	}
	
	@Test
	public void testSearchListingSubstringInName() {
      HostAccount nghia = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      server.addAccount(nghia);
      
      Listing listing1 = new Listing(nghia, "Olympia Mills", "600 Heyward St", 1000);
      Listing listing2 = new Listing(nghia, "Honors Residence", "1215 Blossom St", 1000);
      Listing listing3 = new Listing(nghia, "650 Lincoln", "650 Lincoln St", 1000);
      Listing listing4 = new Listing(nghia, "Granby Mills", "1010 Jimmy Carter Boulevard", 1000);
      
      server.addListing(listing1);
      server.addListing(listing2);
      server.addListing(listing3);
      server.addListing(listing4);
      
      ArrayList<Listing> expected = new ArrayList<Listing>();
      expected.add(listing1);
      expected.add(listing4);
      
      ArrayList<Listing> results = server.searchListings("mills");
      
      assertSame(expected.size(), results.size());
      for (int i = 0; i < results.size(); i++) {
        assertEquals(expected.get(i).getName(), results.get(i).getName());
        assertEquals(expected.get(i).getAddress(), results.get(i).getAddress());
      }
	}
	
	@Test
	public void testSearchListingsNoResults() {
      HostAccount nghia = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      server.addAccount(nghia);
      
      Listing listing1 = new Listing(nghia, "Olympia Mills", "600 Heyward St", 1000);
      Listing listing2 = new Listing(nghia, "Honors Residence", "1215 Blossom St", 1000);
      Listing listing3 = new Listing(nghia, "650 Lincoln", "650 Lincoln St", 1000);
      Listing listing4 = new Listing(nghia, "Granby Mills", "1010 Jimmy Carter Boulevard", 1000);
      
      server.addListing(listing1);
      server.addListing(listing2);
      server.addListing(listing3);
      server.addListing(listing4);
      
      ArrayList<Listing> results = server.searchListings("z");
      
      assertSame(0, results.size());
	}
	
	@Test
	public void testMatchValid() {
      HostAccount nghia = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      server.addAccount(nghia);
      
      Listing listing1 = new Listing(nghia, "Olympia Mills", "600 Heyward St", 1000);
      Listing listing2 = new Listing(nghia, "Honors Residence", "1215 Blossom St", 1000);
      Listing listing3 = new Listing(nghia, "650 Lincoln", "650 Lincoln St", 1000);
      server.addListing(listing1);
      server.addListing(listing2);
      server.addListing(listing3);
      
      listing1.addBathrooms(2);
      listing2.addBathrooms(2);
      listing3.addBathrooms(3);
      listing1.addBedrooms(3);
      listing2.addBedrooms(3);
      listing3.addBedrooms(1);
      listing1.addFilter("pet friendly");
      listing1.addFilter("washer");
      listing2.addFilter("pet friendly");
      
      ArrayList<Listing> expected = new ArrayList<Listing>();
      expected.add(listing1);
      expected.add(listing2);
      
      Listing searchInput = new Listing("", "", "", 0.0, false);
      searchInput.addBathrooms(2);
      searchInput.addBedrooms(3);
      searchInput.addFilter("pet friendly");
      ArrayList<Listing> results = server.match(searchInput);
      
      assertSame(expected.size(), results.size());
      for (int i = 0; i < results.size(); i++) {
        assertEquals(expected.get(i).getName(), results.get(i).getName());
        assertEquals(expected.get(i).getAddress(), results.get(i).getAddress());
      }
	}
	
	@Test
	public void testMatchFilters() {
      HostAccount nghia = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      server.addAccount(nghia);
      
      Listing listing1 = new Listing(nghia, "Olympia Mills", "600 Heyward St", 1000);
      Listing listing2 = new Listing(nghia, "Honors Residence", "1215 Blossom St", 1000);
      Listing listing3 = new Listing(nghia, "650 Lincoln", "650 Lincoln St", 1000);
      server.addListing(listing1);
      server.addListing(listing2);
      server.addListing(listing3);
      
      listing1.addBathrooms(2);
      listing2.addBathrooms(2);
      listing3.addBathrooms(3);
      listing1.addBedrooms(3);
      listing2.addBedrooms(3);
      listing3.addBedrooms(1);
      listing1.addFilter("pet friendly");
      listing1.addFilter("washer");
      listing2.addFilter("pet friendly");
      
      ArrayList<Listing> expected = new ArrayList<Listing>();
      expected.add(listing1);
      
      Listing searchInput = new Listing("", "", "", 0.0, false);
      searchInput.addBathrooms(2);
      searchInput.addBedrooms(3);
      searchInput.addFilter("washer");
      ArrayList<Listing> results = server.match(searchInput);
      
      assertSame(expected.size(), results.size());
      for (int i = 0; i < results.size(); i++) {
        assertEquals(expected.get(i).getName(), results.get(i).getName());
        assertEquals(expected.get(i).getAddress(), results.get(i).getAddress());
      }
      
      expected = new ArrayList<Listing>();
      expected.add(listing1);
      expected.add(listing2);
      
      searchInput = new Listing("", "", "", 0.0, false);
      searchInput.addBathrooms(2);
      searchInput.addBedrooms(3);
      searchInput.addFilter("pet friendly");
      results = server.match(searchInput);
      
      assertSame(expected.size(), results.size());
      for (int i = 0; i < results.size(); i++) {
        assertEquals(expected.get(i).getName(), results.get(i).getName());
        assertEquals(expected.get(i).getAddress(), results.get(i).getAddress());
      }
	}
	
	@Test
	public void testMatchNoResults() {
      HostAccount nghia = new HostAccount("nghian", "123456", "Nghia", "Nguyen");
      server.addAccount(nghia);
      
      Listing listing1 = new Listing(nghia, "Olympia Mills", "600 Heyward St", 1000);
      Listing listing2 = new Listing(nghia, "Honors Residence", "1215 Blossom St", 1000);
      Listing listing3 = new Listing(nghia, "650 Lincoln", "650 Lincoln St", 1000);
      server.addListing(listing1);
      server.addListing(listing2);
      server.addListing(listing3);
      
      listing1.addBathrooms(2);
      listing2.addBathrooms(2);
      listing3.addBathrooms(3);
      listing1.addBedrooms(3);
      listing2.addBedrooms(3);
      listing3.addBedrooms(1);
      listing1.addFilter("pet friendly");
      listing1.addFilter("washer");
      listing2.addFilter("pet friendly");
      
      ArrayList<Listing> expected = new ArrayList<Listing>();
      expected.add(listing1);
      expected.add(listing2);
      
      Listing searchInput = new Listing("", "", "", 0.0, false);
      searchInput.addBathrooms(1);
      searchInput.addBedrooms(1);
      ArrayList<Listing> results = server.match(searchInput); // Testing valid bedrooms, invalid bathrooms
      assertSame(0, results.size());
      
      searchInput = new Listing("", "", "", 0.0, false);
      searchInput.addBathrooms(2);
      searchInput.addBedrooms(6);
      results = server.match(searchInput); // Testing valid bathrooms, invalid bathrooms
      assertSame(0, results.size());
      
      searchInput = new Listing("", "", "", 0.0, false);
      searchInput.addBathrooms(2);
      searchInput.addBedrooms(3);
      searchInput.addFilter("parking");
      results = server.match(searchInput); // Testing valid bathrooms, valid bedrooms, invalid filters
      assertSame(0, results.size());
	}
}
