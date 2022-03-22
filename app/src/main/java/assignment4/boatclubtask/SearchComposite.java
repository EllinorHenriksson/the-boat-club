package assignment4.boatclubtask;

import java.util.ArrayList;

public class SearchComposite implements SearchStrategy {
  String operator;
  private ArrayList<SearchStrategy> children = new ArrayList<>();

  public SearchComposite(String operator, SearchStrategy component1, SearchStrategy component2) {
    setOperator(operator);
    children.add(component1);
    children.add(component2);
  }

  public void setOperator(String operator) {
    if (operator.equals("AND") || operator.equals("OR")) {
      this.operator = operator;
    } else {
      // Felmeddelande
    }
  }

  /**
   * Searches for members in a registry.
   *
   * @param members The members to search among.
   * @return The search results.
   */
  @Override
  public ArrayList<Member> search(ArrayList<Member> members) {
    ArrayList<Member> result1 = children.get(0).search(members);
    ArrayList<Member> result2 = children.get(1).search(members);

    ArrayList<Member> finalResult = new ArrayList<>();
    if (operator.equals("AND")) {
      for (Member m1 : result1) {
        for (Member m2 : result2) {
          if (m1.getId().equals(m2.getId())) {
            finalResult.add(m1);
          }
        }
      }
    } else if (operator.equals("OR")) {
      for (Member m1 : result1) {
        finalResult.add(m1);
        for (Member m2 : result2) {
          if (!m1.getId().equals(m2.getId())) {
            finalResult.add(m2);
          }
        }
      }
    }
    return finalResult;
  }
}
