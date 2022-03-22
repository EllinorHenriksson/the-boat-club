package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a strategy for searching for members with a specific boat type.
 */
public class BoatTypeStrategy implements SearchStrategy {
  private String phrase;

  /**
   * Initializing constructor.
   *
   * @param phrase The search phrase (i.e. the type).
   */
  public BoatTypeStrategy(String phrase) {
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
    ArrayList<Member> results = new ArrayList<>();
    for (Member m : members) {
      Boolean match = false;
      for (Boat b : m.getBoats()) {
        if (phrase.equals("canoe") && b instanceof Canoe) {
          match = true;
        }
        if (phrase.equals("sailboat") && b instanceof Sailboat) {
          match = true;
        }
        if (phrase.equals("motorboat") && b instanceof Motorboat) {
          match = true;
        }
        if (phrase.equals("motorsailer") && b instanceof Motorsailer) {
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
