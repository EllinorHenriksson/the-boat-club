package assignment4.boatclubtask;

import java.util.ArrayList;

/**
 * Represents a boat club member.
 */
public class Member {
  String name;
  String email = null;
  String id;
  ArrayList<Boat> boats = new ArrayList<>();

  /**
   * Initializing constructor.
   *
   * @param name The name of the member.
   * @param id The id of the member.
   */
  public Member(String name, String id) {
    setName(name);
    this.id = id;
  }

  /**
   * Initializing constructor.
   *
   * @param name The name of the member.
   * @param email The email address of the member.
   * @param id The id of the member.
   */
  public Member(String name, String email, String id) {
    setName(name);
    setEmail(email);
    this.id = id;
  }

  /**
   * Gets the name of the member.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the member.
   *
   * @param name The name (must not be null or shorter than 2 characters, or an exception will be thrown).
   */
  public void setName(String name) {
    if (name == null || name.length() < 2) {
      throw new IllegalArgumentException("Invalid member name.");
    } else {
      this.name = name;
    }
  }

  /**
   * Gets the email address of te member.
   *
   * @return The email adress.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the member.
   *
   * @param email The email address (must not be null nor shorter than 2 characters,
   * and must contain @, or an exception will be thrown).
   */
  public void setEmail(String email) {
    if (email == null || email.length() < 5 || !email.contains("@")) {
      throw new IllegalArgumentException("Invalid email address.");
    } else {
      this.email = email;
    }
  }

  /**
   * Gets the id of the member.
   *
   * @return The id.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets copies of the member's boats.
   *
   * @return The boat copies.
   */
  public ArrayList<Boat> getBoats() {
    ArrayList<Boat> copy = new ArrayList<>();
    for (int i = 0; i < boats.size(); i++) {
      Boat boat = boats.get(i);
      Boat boatCopy = null;
      if (boat instanceof Canoe) {
        boatCopy = new Canoe(boat.getName(), boat.getLength());
      }
      if (boat instanceof Sailboat) {
        boatCopy = new Sailboat(boat.getName(), boat.getLength(), ((Sailboat) boat).getDepth());
      }
      if (boat instanceof Motorboat) {
        boatCopy = new Motorboat(boat.getName(), boat.getLength(), ((Motorboat) boat).getPower());
      }
      if (boat instanceof Motorsailer) {
        boatCopy = new Motorsailer(boat.getName(), boat.getLength(), ((Motorsailer) boat).getDepth(), ((Motorsailer) boat).getPower());
      }
      copy.add(boatCopy);
    }
    return copy;
  }

  /**
   * Adds a boat to a member.
   *
   * @param boat The boat.
   */
  public void addBoat(Boat boat) {
    boats.add(boat);
  }

  /**
   * Deletes a boat with a certain name from the member.
   *
   * @param name The name of the boat.
   */
  public void deleteBoat(String name) {
    for (int i = 0; i < boats.size(); i++) {
      if (boats.get(i).getName().equals(name)) {
        boats.remove(i);
      }
    }
  }

  /**
   * Returns a string representation of the member.
   *
   * @return A string.
   */
  public String toString() {
    if (email == null) {
      return "MEMBER:" + name + ":" + ":" + id;
    } else {
      return "MEMBER:" + name + ":" + email + ":" + id;
    }
  }
}
