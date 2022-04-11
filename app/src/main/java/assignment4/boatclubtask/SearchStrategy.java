package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a strategy for searching for members.
 */
public interface SearchStrategy {
  /**
   * Searches for members in a registry.
   *
   * @param members The members to search among.
   * @return The search results.
   */
  public ArrayList<Member> search(ArrayList<Member> members);
}