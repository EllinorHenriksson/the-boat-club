package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a boat club.
 */
public class Registry {
  ArrayList<Member> members = new ArrayList<>();

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
    for (Member member : members) {
      if (member.getId().equals(id)) {
        member.setName(newName);
        member.setEmail(newEmail);
      }
    }
  }

  /**
   * Adds a boat to a member.
   *
   * @param id The id of the member.
   * @param boat The boat.
   */
  public void addBoatToMember(String id, Boat boat) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        member.addBoat(boat);
      }
    }
  }

  /**
   * Edits a boat (i.e. deletes an old bot and adds a new in its place).
   *
   * @param member A copy of the member that owns the boat.
   * @param oldBoat A copy of the boat that is edited (i.e. removed).
   * @param newBoat The edited (i.e. new) boat.
   */
  public void editBoat(Member member, Boat oldBoat, Boat newBoat) {
    String id = member.getId();

    for (Member m : members) {
      if (m.getId().equals(id)) {
        m.deleteBoat(oldBoat);
        m.addBoat(newBoat);
      }
    }
  }
}
