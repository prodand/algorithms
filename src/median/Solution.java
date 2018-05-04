package median;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Solution {


  public static void main(String[] args) {
    Scanner in = new Scanner(Solution.class.getResourceAsStream("input.txt"));
    int n = in.nextInt();
    int[] a = new int[n];
    for (int a_i = 0; a_i < n; a_i++) {
      a[a_i] = in.nextInt();
    }
    solve(a);
  }

  public static void solve(int[] arr) {
    Heap max = new MaxHeap(arr.length / 2);
    Heap min = new MinHeap((arr.length + 1) / 2 + 1);

    for (int i = 0; i < arr.length; i++) {
      int current = arr[i];
      if (min.size == 0 && max.size == 0) {
        min.add(current);
      } else if (current < min.peek() && current > max.peek()) {
        Heap heap = min.size > max.size ? max : min;
        heap.add(current);
      } else if (current >= min.peek()) {
        min.add(current);
        if (min.size - max.size > 1) {
          int val = min.poll();
          max.add(val);
        }
      } else {
        max.add(current);
        if (max.size >= min.size) {
          int val = max.poll();
          min.add(val);
        }
      }
      double val = median(min, max);
      System.out.println(new DecimalFormat("0.0").format(val));
    }
  }

  private static double median(Heap min, Heap max) {
    if (min.size == max.size) {
      return ((double) (min.peek() + max.peek())) / 2;
    }
    return min.peek();
  }

  static abstract class Heap {

    int size;
    int[] items;

    Heap(int capacity) {
      items = new int[capacity];
    }

    /**
     * @param parentIndex The index of the parent element.
     * @return The index of the left child.
     **/
    public int getLeftChildIndex(int parentIndex) {
      return 2 * parentIndex + 1;
    }

    /**
     * @param parentIndex The index of the parent element.
     * @return The index of the right child.
     **/
    public int getRightChildIndex(int parentIndex) {
      return 2 * parentIndex + 2;
    }

    /**
     * @param childIndex The index of the child element.
     * @return The index of the parent element.
     **/
    public int getParentIndex(int childIndex) {
      return (childIndex - 1) / 2;
    }

    /**
     * @param index The index of the element you are checking.
     * @return true if the Heap contains enough elements to fill the left child index, false
     * otherwise.
     **/
    public boolean hasLeftChild(int index) {
      return getLeftChildIndex(index) < size;
    }

    /**
     * @param index The index of the element you are checking.
     * @return true if the Heap contains enough elements to fill the right child index, false
     * otherwise.
     **/
    public boolean hasRightChild(int index) {
      return getRightChildIndex(index) < size;
    }

    /**
     * @param index The index of the element you are checking.
     * @return true if the calculated parent index exists within array bounds false otherwise.
     **/
    public boolean hasParent(int index) {
      return getParentIndex(index) >= 0;
    }

    /**
     * @param index The index of the element whose child you want.
     * @return the value in the left child.
     **/
    public int leftChild(int index) {
      return items[getLeftChildIndex(index)];
    }

    /**
     * @param index The index of the element whose child you want.
     * @return the value in the right child.
     **/
    public int rightChild(int index) {
      return items[getRightChildIndex(index)];
    }

    /**
     * @param index The index of the element you are checking.
     * @return the value in the parent element.
     **/
    public int parent(int index) {
      return items[getParentIndex(index)];
    }

    public void swap(int i, int j) {
      int tmp = items[i];
      items[i] = items[j];
      items[j] = tmp;
    }

    public void add(int val) {
      items[size] = val;
      size++;
      heapifyUp();
    }

    public int poll() {
      int val = items[0];
      items[0] = items[size - 1];
      size--;
      heapifyDown();
      return val;
    }

    public int peek() {
      return items[0];
    }

    public abstract void heapifyUp();

    public abstract void heapifyDown();
  }

  static class MinHeap extends Heap {

    MinHeap(int capacity) {
      super(capacity);
    }

    public void heapifyUp() {
      int index = size - 1;
      while (hasParent(index) && items[getParentIndex(index)] > items[index]) {
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
      }
    }


    public void heapifyDown() {
      int index = 0;
      while (hasLeftChild(index)) {
        int smallest = getLeftChildIndex(index);
        if (hasRightChild(index) && items[getRightChildIndex(index)] < items[smallest]) {
          smallest = getRightChildIndex(index);
        }
        if (items[smallest] < items[index]) {
          swap(smallest, index);
          index = smallest;
        } else {
          break;
        }
      }
    }
  }

  static class MaxHeap extends Heap {

    MaxHeap(int capacity) {
      super(capacity);
    }

    public void heapifyUp() {
      int index = size - 1;
      while (hasParent(index) && items[getParentIndex(index)] < items[index]) {
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
      }
    }


    public void heapifyDown() {
      int index = 0;
      while (hasLeftChild(index)) {
        int biggest = getLeftChildIndex(index);
        if (hasRightChild(index) && items[getRightChildIndex(index)] > items[biggest]) {
          biggest = getRightChildIndex(index);
        }
        if (items[biggest] > items[index]) {
          swap(biggest, index);
          index = biggest;
        } else {
          break;
        }
      }
    }
  }
}
