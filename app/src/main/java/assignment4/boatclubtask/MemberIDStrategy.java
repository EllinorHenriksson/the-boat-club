package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a strategy for searching for members with a specific member ID.
 */
public class MemberIdStrategy implements SearchStrategy {

  /**
   * Searches for members in a registry.
   *
   * @param members The members to search among.
   * @param phrase The search phrase.
   * @return The search results.
   */
  @Override
  public ArrayList<Member> search(ArrayList<Member> members, String phrase) {
    ArrayList<Member> results = new ArrayList<>();
    for (Member m : members) {
      if (m.getId().equals(phrase)) {
        results.add(m);
      }
    }
    return results;
  }
  
}
