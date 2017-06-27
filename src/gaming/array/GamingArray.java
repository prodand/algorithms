package gaming.array;

import java.util.Scanner;

public class GamingArray {

  public static void main(String[] args) {
    Scanner sc = new Scanner(GamingArray.class.getResourceAsStream("input.txt"));
    int g = sc.nextInt();
    GamingArray solver = new GamingArray();
    for(int a0 = 0; a0 < g; a0++){
      sc.nextLine();
      int n = sc.nextInt();
      sc.nextLine();
      int[] array = new int[n];
      for (int i = 0; i < n; i++) {
        array[i] = sc.nextInt();
      }
      solver.solve(array);
    }
  }

  private void solve(int[] arr) {
    int maxIndex = 0;
    int count = 1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > arr[maxIndex]) {
        maxIndex = i;
        count++;
      }
    }
    if (count % 2 == 0) {
      System.out.println("ANDY");
    } else {
      System.out.println("BOB");
    }
  }
}
