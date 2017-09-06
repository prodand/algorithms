package similar.expressions;

import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int cases = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < cases; i++) {
      String s1 = sc.nextLine();
      String s2 = sc.nextLine();
    }
  }

  private String convertToPrefix(String infix) {
    LinkedList<Character> operators = new LinkedList<>();
    StringBuilder result = new StringBuilder();
    int operandsPending = 0;
    int parenthesesLevel = 0;
    for (char val : infix.toCharArray()) {
      if (val == '+' || val == '-') {
        if (operandsPending == 2) {
          result.append(operators.pop());
          operandsPending = 0;
        }
        operators.push(val);
      } else if (val >= 'a' && val <= 'z') {
        result.append(val);
        operandsPending++;
      } else if (val == '(' || val == ')') {

      }
    }
    return null;
  }
}
