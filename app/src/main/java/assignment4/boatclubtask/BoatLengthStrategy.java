package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a strategy for searching for members with boats over a specific length.
 */
public class BoatLengthStrategy implements SearchStrategy {
  private int length;

  /**
   * Initializing constructor.
   *
   * @param phrase The search phrase (i.e. the boat length).
   */
  public BoatLengthStrategy(String phrase) {
    setLength(Integer.parseInt(phrase));
  }

  /**
   * Sets the search phrase.
   *
   * @param length The search phrase (i.e. the boat length) (must be graeter than 0 or an exception will be thrown).
   */
  private void setLength(int length) {
    if (length > 0) {
      this.length = length;
    } else {
      throw new IllegalArgumentException("Length must be greater than 0.");
    }
  }

  /**
   * Searches for members in a registry.
   *
   * @param members The members to search among.
   * @return The search results.
   */
  @Override
  public ArrayList<Member> search(ArrayList<Member> members) {
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
