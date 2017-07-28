package elevator;

import java.util.Scanner;

public class Elevator {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(Elevator.class.getResourceAsStream("input.txt"));
    int N = scanner.nextInt();
    int P = scanner.nextInt();
    int K = scanner.nextInt();
    int W = scanner.nextInt();

    scanner.nextLine();
    int[] desiredFloors = new int[P];
    for (int i = 0; i < P; i++) {
      desiredFloors[i] = scanner.nextInt();
    }

    scanner.nextLine();
    int[] weights = new int[P];
    for (int i = 0; i < P; i++) {
      weights[i] = scanner.nextInt();
    }

    Elevator elevator = new Elevator();
    System.out.println(elevator.solution(N, P, W, K, weights, desiredFloors));
  }

  private int solution(int floors, int peopleAmount, int weightCapacity, int peopleCapacity, int[] weights, int[] desiredFloors) {
    long peopleLoaded = 0;
    long loadedWeight = 0;
    int[] chosenFloors = new int[floors];
    int wave = 1;
    int stops = 0;
    for (int i = 0; i < peopleAmount; i++) {
      peopleLoaded += 1;
      loadedWeight += weights[i];
      int floor = desiredFloors[i];
      if (chosenFloors[floor] != wave) {
        stops++;
        chosenFloors[floor] = wave;
      }
      if (peopleLoaded > peopleCapacity || loadedWeight > weightCapacity) {
        // New wave
        peopleLoaded = 0;
        loadedWeight = 0;
        stops++;
        wave++;
      }
    }
    return stops;
  }
}
