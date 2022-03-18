package assignment4.boatclubtask;

/**
 * Represents a canoe.
 */
public class Canoe extends Boat {
  /**
   * Inizializing constructor.
   *
   * @param name The name of the canoe.
   * @param length The length (in meter).
   */
  public Canoe(String name, int length) {
    super(name, length);
  }

  /**
   * Returns a string representation of the canoe.
   *
   * @return A string.
   */
  @Override
  public String toString() {
    return "BOAT:" + this.getName() + ":" + "canoe" + ":" + this.getLength();
  }
}
