package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a strategy for searching for members with a specific member ID.
 */
public class MemberIdStrategy implements SearchStrategy {
  private String phrase;

  /**
   * Initializing constructor.
   *
   * @param phrase The search phrase (i.e. the ID).
   */
  public MemberIdStrategy(String phrase) {
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
      if (m.getId().equals(phrase)) {
        results.add(m);
      }
    }
    return results;
  }
  
}
