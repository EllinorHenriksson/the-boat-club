package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a strategy for searching for members with boats over a specific length.
 */
public class BoatLengthStrategy implements SearchStrategy {
  private String phrase;

  /**
   * Initializing constructor.
   *
   * @param phrase The search phrase (i.e. the length).
   */
  public BoatLengthStrategy(String phrase) {
    this.phrase = phrase;
  }

  /**
   * Searches for members in a registry.
   *
   * @param members The members to search among.
   * @return The search results.
   */
  @Override
  public ArrayList<Member> search(ArrayList<Member> members) {
    int length = Integer.parseInt(phrase);
    ArrayList<Member> results = new ArrayList<>();
    for (Member m : members) {
      Boolean match = false;
      for (Boat b : m.getBoats()) {
        if (b.getLength() >= length) {
          match = true;
        }
      }
      if (match) {
        results.add(m);
      }
    }
    return results;
  }
}
