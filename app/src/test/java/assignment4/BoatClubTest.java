package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import assignment4.boatclubtask.Application;
import assignment4.boatclubtask.BoatLengthStrategy;
import assignment4.boatclubtask.Canoe;
import assignment4.boatclubtask.Member;
import assignment4.boatclubtask.Motorboat;
import assignment4.boatclubtask.Motorsailer;
import assignment4.boatclubtask.Sailboat;
import assignment4.boatclubtask.SearchStrategy;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents a test for methods in assignment 4.
 */
public class BoatClubTest {
  /**
   * Tests if the method search in the class BoatLengthStrategy returns the correct members after filtering them.
   */
  @Test
  public void testLengthSearch() {
    // Test standard case
    Member memberA1 = new Member("Ellen", "e@nu.se", "123hjr");
    memberA1.addBoat(new Canoe("Kanoten", 3));
    memberA1.addBoat(new Sailboat("Segelbåten", 25, 3));
    Member memberA2 = new Member("Gustav", "j2g87d");
    memberA2.addBoat(new Motorsailer("Motorseglaren", 30, 7, 20));
    Member memberA3 = new Member("Berit", "berit@gmail.com", "kl34hj");    
    memberA3.addBoat(new Motorboat("Motorbåten", 15, 40));

    ArrayList<Member> allMembersA = new ArrayList<>();
    allMembersA.add(memberA1);
    allMembersA.add(memberA2);
    allMembersA.add(memberA3);

    ArrayList<Member> correctResultA = new ArrayList<>();
    correctResultA.add(memberA1);
    correctResultA.add(memberA2);

    SearchStrategy lengthSearchA = new BoatLengthStrategy("20");
    assertEquals(correctResultA, lengthSearchA.search(allMembersA));

    // Test standard case
    Member memberB1 = new Member("Ellen", "e@nu.se", "123hjr");
    memberB1.addBoat(new Canoe("Kanoten", 3));
    memberB1.addBoat(new Sailboat("Segelbåten", 25, 3));
    Member memberB2 = new Member("Gustav", "j2g87d");
    memberB2.addBoat(new Motorsailer("Motorseglaren", 30, 7, 20));
    Member memberB3 = new Member("Berit", "berit@gmail.com", "kl34hj");    
    memberB3.addBoat(new Motorboat("Motorbåten", 15, 40));
    Member memberB4 = new Member("Lisa", "j2gg73");
    memberB4.addBoat(new Motorsailer("Motorseglaren2", 19, 4, 25));
    Member memberB5 = new Member("Bertil", "bertil@gmail.com", "k36shj");    
    memberB5.addBoat(new Motorboat("Motorbåten2", 22, 35));
    memberB5.addBoat(new Sailboat("Segelbåten2", 35, 5));

    ArrayList<Member> allMembersB = new ArrayList<>();
    allMembersB.add(memberB1);
    allMembersB.add(memberB2);
    allMembersB.add(memberB3);
    allMembersB.add(memberB4);
    allMembersB.add(memberB5);

    ArrayList<Member> correctResultB = new ArrayList<>();
    correctResultB.add(memberB2);
    correctResultB.add(memberB5);

    SearchStrategy lengthSearchB = new BoatLengthStrategy("30");
    assertEquals(correctResultB, lengthSearchB.search(allMembersB));

    // Test extreme case
    ArrayList<Member> allMembersC = new ArrayList<>();
    ArrayList<Member> correctResultC = new ArrayList<>();
    SearchStrategy lengthSearchC = new BoatLengthStrategy("15");
    assertEquals(correctResultC, lengthSearchC.search(allMembersC));

    // Test extreme case
    Member memberD1 = new Member("Ellen", "e@nu.se", "123hjr");
    memberD1.addBoat(new Canoe("Kanoten", 3));
    memberD1.addBoat(new Sailboat("Segelbåten", 25, 3));
    Member memberD2 = new Member("Gustav", "j2g87d");
    memberD2.addBoat(new Motorsailer("Motorseglaren", 30, 7, 20));
    Member memberD3 = new Member("Berit", "berit@gmail.com", "kl34hj");    
    memberD3.addBoat(new Motorboat("Motorbåten", 15, 40));

    ArrayList<Member> allMembersD = new ArrayList<>();
    allMembersD.add(memberD1);
    allMembersD.add(memberD2);
    allMembersD.add(memberD3);

    ArrayList<Member> correctResultD = new ArrayList<>();
    correctResultD.add(memberD1);
    correctResultD.add(memberD2);
    correctResultD.add(memberD3);

    SearchStrategy lengthSearchD = new BoatLengthStrategy("1");
    assertEquals(correctResultD, lengthSearchD.search(allMembersD));
  }

  @Test
  public void testUniqueEmail() {
    Application app = new Application(null);
    
  }
}
