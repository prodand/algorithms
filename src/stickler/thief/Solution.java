package stickler.thief;

import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

  /**
   * Put total into each node while passing it. Chose path with biggest value
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(
        similar.expressions.Solution.class.getResourceAsStream("/stickler/thief/input.txt"));
    int T = scanner.nextInt();
    for (int i = 0; i < T; i++) {
      int N = scanner.nextInt();
      int[] arr = new int[N];
      for (int j = 0; j < N; j++) {
        arr[j] = scanner.nextInt();
      }
      int max = solve(arr);
      System.out.println(max);
    }
  }

  public static int solve(int[] arr) {
    if (arr.length == 1) {
      return arr[0];
    }
    if (arr.length == 2) {
      return Math.max(arr[0], arr[1]);
    }
    int[] aux = new int[arr.length];
    LinkedList<Integer> stack = new LinkedList<>();
    stack.push(0);
    aux[0] = arr[0];
    stack.push(1);
    aux[1] = arr[1];
    while (!stack.isEmpty()) {
      int val = stack.pollLast();
      if (val + 2 >= arr.length) {
        continue;
      }
      if (aux[val + 2] == 0) {
        stack.push(val + 2);
      }
      int val2 = aux[val] + arr[val + 2];
      if (val2 > aux[val + 2]) {
        aux[val + 2] = val2;
      }

      if (val + 3 >= arr.length) {
        continue;
      }
      if (aux[val + 3] == 0) {
        stack.push(val + 3);
      }
      int val3 = aux[val] + arr[val + 3];
      if (val3 > aux[val + 3]) {
        aux[val + 3] = val3;
      }
    }
    return Math.max(aux[aux.length - 3],
        Math.max(aux[aux.length - 1], aux[aux.length - 2]));
  }
}
