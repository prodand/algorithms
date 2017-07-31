package roads.libraries;

import java.util.Scanner;

public class RoadAndLibraries {

  public static void main(String[] args) {
    Scanner in = new Scanner(RoadAndLibraries.class.getResourceAsStream("input.txt"));
    int q = in.nextInt();
    for (int a0 = 0; a0 < q; a0++) {
      int n = in.nextInt();
      int m = in.nextInt();
      long x = in.nextLong();
      long y = in.nextLong();
      RoadAndLibraries roadAndLibraries = new RoadAndLibraries(n, x, y);
      for (int a1 = 0; a1 < m; a1++) {
        int city_1 = in.nextInt();
        int city_2 = in.nextInt();
        roadAndLibraries.makeDecision(city_1, city_2);
      }
      if (x <= y) {
        System.out.println(x * n);
      } else {
        long cost = roadAndLibraries.cost + (n - roadAndLibraries.citiesCounter) * x;
        System.out.println(cost);
      }
    }
  }

  private Node[] cities;
  private long churchCost;
  private long roadCost;
  long cost = 0;
  int citiesCounter = 0;

  RoadAndLibraries(int citiesAmount, long churchCost, long roadCost) {
    this.cities = new Node[citiesAmount + 1];
    this.churchCost = churchCost;
    this.roadCost = roadCost;
  }

  private void makeDecision(int city1, int city2) {
    if (cities[city1] == null && cities[city2] == null) {
      cities[city1] = new Node(city1, 1);
      cities[city2] = new Node(city1);
      cost += churchCost;
      cost += roadCost;
      citiesCounter += 2;
    } else if (cities[city1] == null || cities[city2] == null) {
      int existing = cities[city1] == null ? city2 : city1;
      int empty = cities[city1] == null ? city1 : city2;
      cities[empty] = new Node(library(existing).parent);
      cost += roadCost;
      citiesCounter += 1;
    } else {
      Node l1 = library(city1);
      Node l2 = library(city2);
      if (l1.parent == l2.parent) {
        return;
      }
      if (l1.depth > l2.depth) {
        l2.parent = l1.parent;
      } else if (l1.depth < l2.depth) {
        l1.parent = l2.parent;
      } else {
        l2.parent = l1.parent;
        l1.depth += 1;
      }
      cost -= churchCost;
      cost += roadCost;
    }
  }

  private Node library(int city) {
    while (cities[city].parent != city) {
      city = cities[city].parent;
    }
    return cities[city];
  }

}

class Node {
  int parent;
  int depth;

  public Node(int parent, int depth) {
    this.parent = parent;
    this.depth = depth;
  }

  public Node(int parent) {
    this.parent = parent;
  }
}