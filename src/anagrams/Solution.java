package anagrams;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Solution {

  static class Node {

    int data;
    Node left;
    Node right;
  }

  public static void main(String[] args) {
    String val = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 17 16 18 19 20 21 22 23 24 25 26 27 28 29 30 31";
    String[] nums = val.split(" ");
    Node root = create(nums, 0, nums.length - 1);
    boolean res = new Solution().checkBST(root);
    System.out.println(res);
  }

  static Node create(String[] arr, int from, int to) {
    Node node = new Node();
    int index = (to + from) / 2;
    node.data = Integer.valueOf(arr[index]);
    if (to == from) {
      return node;
    }
    node.left = create(arr, from, index - 1);
    node.right = create(arr, index + 1, to);
    return node;
  }

  boolean checkBST(Node root) {
    System.out.println(root.data);
    if (!checkChildren(root)) {
      return false;
    }
    return checkLeftTree(root, root.left) && checkRightTree(root, root.right);
  }

  boolean checkChildren(Node parent) {
    Node left = parent.left;
    Node right = parent.right;
    if (left != null && parent.data <= left.data) {
      return false;
    }
    if (right != null && parent.data >= right.data) {
      return false;
    }
    return true;
  }

  boolean checkRightTree(Node grand, Node parent) {
    if (parent == null) {
      return true;
    }
    if (!checkChildren(parent)) {
      return false;
    }
    Node left = parent.left;
    Node right = parent.right;
    if (left != null && grand.data >= left.data) {
      return false;
    }
    if (right != null && grand.data >= right.data) {
      return false;
    }
    return checkLeftTree(parent, left) && checkRightTree(parent, right);
  }

  boolean checkLeftTree(Node grand, Node parent) {
    if (parent == null) {
      return true;
    }
    if (!checkChildren(parent)) {
      return false;
    }
    Node left = parent.left;
    Node right = parent.right;
    if (left != null && grand.data <= left.data) {
      return false;
    }

    if (right != null && grand.data <= right.data) {
      return false;
    }
    return checkLeftTree(parent, left) && checkRightTree(parent, right);
  }
}
