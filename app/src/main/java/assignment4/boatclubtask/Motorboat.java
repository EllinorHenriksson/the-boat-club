package assignment4.boatclubtask;

/**
 * Represents a motorboat.
 */
public class Motorboat extends Boat {
  private int power;

  /**
   * Initializing constructor.
   *
   * @param name The name of the motorboat.
   * @param length The length (in meter).
   * @param power The horse power.
   */
  public Motorboat(String name, int length, int power) {
    super(name, length);
    setPower(power);
  }

  /**
   * Gets the horse power of the motorboat.
   *
   * @return The horse power.
   */
  public int getPower() {
    return power;
  }

  /**
   * Sets the horse power of the motorboat.
   *
   * @param power The horse power.
   */
  public void setPower(int power) {
    if (power > 0) {
      this.power = power;
    } else {
      throw new IllegalArgumentException("The horse power of the boat must be greater than 0.");
    }
  }
}
