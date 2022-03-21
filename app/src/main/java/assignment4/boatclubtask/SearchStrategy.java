package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * OBS! Ändra här.
 */
public interface SearchStrategy {
  /**
   * Searches for members in a registry.
   *
   * @param members The members to search among.
   * @param phrase The search phrase.
   * @return The search results.
   */
  public ArrayList<Member> search(ArrayList<Member> members, String phrase);
}