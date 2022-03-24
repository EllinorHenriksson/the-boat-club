package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a boat club.
 */
public class Registry {
  ArrayList<Member> members = new ArrayList<>();
  SearchStrategy searchTool = null;

  /**
   * Gets copies of the boat club's members.
   *
   * @return The member copies.
   */
  public ArrayList<Member> getMembers() {
    ArrayList<Member> copy = new ArrayList<>();
    // For each of the boat club's member...
    for (int i = 0; i < members.size(); i++) {
      Member member = members.get(i);
      // ...create a copy of the member and add to the member list copy.
      String email = member.getEmail();

      if (email == null) {
        copy.add(new Member(member.getName(), member.getId()));
      } else {
        copy.add(new Member(member.getName(), member.getEmail(), member.getId()));
      }

      // For each boat (copy) of the member...
      for (Boat boat : member.getBoats()) {
        // ... add it to the member copy.
        copy.get(copy.size() - 1).addBoat(boat);
      }
    }
    return copy;
  }

  /**
   * Adds a member to the boat club.
   *
   * @param member The member.
   */
  public void addMember(Member member) {
    members.add(member);
  }

  /**
   * Deletes a member from the boat club.
   *
   * @param id The id of the member.
   */
  public void deleteMember(String id) {
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getId().equals(id)) {
        members.remove(i);
      }
    }
  }

  /**
   * Edits information about a member.
   *
   * @param id The id of the member.
   * @param newName The new name of the member.
   * @param newEmail The new email address of the member.
   */
  public void editMember(String id, String newName, String newEmail) {
    Member member = getMember(id);
    member.setName(newName);
    member.setEmail(newEmail);
  }

  /**
   * Adds a boat to a member with a certain id.
   *
   * @param id The id of the member.
   * @param boat The boat.
   */
  public void addBoatToMember(String id, Boat boat) {
    Member member = getMember(id);
    member.addBoat(boat);
  }

  /**
   * Edits a boat (i.e. deletes an old bot and adds a new in its place).
   *
   * @param id The ID of the member that owns the boat.
   * @param nameOldBoat The name of the boat that is edited (i.e. removed).
   * @param newBoat The edited (i.e. new) boat.
   */
  public void editBoat(String id, String nameOldBoat, Boat newBoat) {
    Member member = getMember(id);
    member.deleteBoat(nameOldBoat);
    member.addBoat(newBoat);
  }

  /**
   * Deletes a boat with a certain name from a member with a certain ID.
   *
   * @param id The member ID.
   * @param boatName The name of the boat.
   */
  public void deleteBoatFromMember(String id, String boatName) {
    Member member = getMember(id);
    member.deleteBoat(boatName);
  }

  /**
   * Gets the member with a certain ID.
   *
   * @param id The id of the member.
   * @return The member.
   */
  private Member getMember(String id) {
    Member member = null;
    for (Member m : members) {
      if (m.getId().equals(id)) {
        member = new Member(m.getName(), m.getEmail(), m.getId());
      }
    }
    return member;
  }

  /**
   * Sets the search strategy of the registry.
   *
   * @param strategy The search strartegy.
   */
  public void setSearchStrategy(SearchStrategy strategy) {
    searchTool = strategy;
  }

  /**
   * Searches for members in the registry.
   *
   * @return The search result.
   */
  public ArrayList<Member> searchForMembers() {
    return searchTool.search(getMembers());
  }

  /**
   * Example of implementation of binary search functionality with the composite design pattern.
   */
  public void implementSearchComposite() {
    ArrayList<Member> members = getMembers();
    SearchStrategy component1 = new BoatTypeStrategy("sailboat");
    SearchStrategy component2 = new BoatLengthStrategy("20");

    SearchStrategy component3 = new BoatTypeStrategy("motorsailer");
    SearchStrategy component4 = new SearchComposite("AND", component1, component2);

    searchTool = new SearchComposite("OR", component3, component4);
    ArrayList<Member> result = searchTool.search(members);
    System.out.println(result);
  }
}
