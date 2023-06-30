/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructuressecondproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author emir
 */
public class BinarySearchTree<T extends Comparable<T>> {
// This code defines a generic Binary Search Tree (BST) data structure with a
//root node and an insert() method that inserts a new node in the BST while
//maintaining the sorted order based on the values of the nodes.

    private Node<T> root;
    private BinarySearchTree<T> suggestions;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    private Node<T> insert(Node<T> root, T word) {
        if (root == null) {
            return new Node<T>(word);
        }

        if (word.compareTo(root.word) < 0) {
            root.left = insert(root.left, word);
        } else {
            root.right = insert(root.right, word);
        }

        return root;
    }
//This code reads words from a text file, creates a new node for each word, 
//and inserts it into a Binary Search Tree (BST) data structure.

    public void readFromFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                T newWord = (T) line.trim();
                insert(newWord);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean search(T word) {
        return search(root, word);
    }
    //This code defines a recursive search function to find a specific node containing a given 
    //word in a Binary Search Tree (BST) data structure, returning true if found and false if not found.

    private boolean search(Node<T> root, T word) {
        if (root == null) {
            return false;
        }
        if (word.compareTo(root.word) == 0) {
            return true;
        } else if (word.compareTo(root.word) < 0) {
            return search(root.left, word);
        } else {
            return search(root.right, word);
        }
    }

    // This code defines an update() method that takes old and new data as input and recursively searches for the node 
    //with the old data in a Binary Search Tree (BST) data structure, replacing it with the new data.
    public void update(T oldData, T newData) {
        if (root == null) {
            return;
        }
        update(root, oldData, newData);
    }
// This code defines a recursive update function to find a specific node containing an old word in a 
//Binary Search Tree (BST) data structure and update its value with a new word.

    private void update(Node<T> node, T oldWord, T newWord) {
        if (node == null) {
            return;
        }
        if (oldWord.compareTo(node.word) == 0) {
            node.word = newWord;
        } else if (oldWord.compareTo(node.word) < 0) {
            update(node.left, oldWord, newWord);
        } else {
            update(node.right, oldWord, newWord);
        }
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    //This code defines a recursive delete function to remove a node containing a specific data value from a Binary Search Tree (BST) data structure
    //, while maintaining the sorted order of the remaining nodes.
    private Node<T> delete(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.word) < 0) {
            root.left = delete(root.left, data);
        } else if (data.compareTo(root.word) > 0) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.word = minValue(root.right);
            root.right = delete(root.right, root.word);
        }

        return root;
    }

    //This code defines a function to find and return the minimum value in a Binary Search Tree (BST) data structure.
    private T minValue(Node<T> root) {
        T minv = root.word;
        while (root.left != null) {
            minv = root.left.word;
            root = root.left;
        }
        return minv;
    }

//This code returns a binary search tree containing suggestions for a given word
// by iterating through all nodes in the binary search tree, calculating the Levenshtein distance between each node's word and
// the given word, and adding the closest words as suggestions to a new binary search tree.
    public BinarySearchTree<T> suggestion(String word) {
        int minDist = Integer.MAX_VALUE;
        String closestWord = "";
        suggestions = new BinarySearchTree<>();

        for (Node<T> node : getAllNodes()) {
            int dist = getLevenshteinDistance(word, node.word);
            if (dist < minDist) {
                minDist = dist;
                closestWord = (String) node.word;
                suggestions.insert((T) closestWord);
            } else if (dist == minDist) {
                closestWord = (String) node.word;
                suggestions.insert((T) closestWord);
            }
        }
        return suggestions;
    }

    // This code takes a string as input, calculates the Levenshtein distance between that string and all the nodes in the tree,
    // and returns an ArrayList of the closest words (i.e., with the minimum Levenshtein distance).
    public ArrayList<String> suggestCorrection(String word) {
        int minDist = Integer.MAX_VALUE;
        String closestWord = "";
        ArrayList<String> suggestions = new ArrayList<>();

        for (Node<T> node : getAllNodes()) {
            int dist = getLevenshteinDistance(word, node.word);
            if (dist < minDist) {
                minDist = dist;
                closestWord = (String) node.word;
                suggestions.add(closestWord);
            }
        }
        return suggestions;
    }

    //The code defines a public method `auto` which takes a prefix `prefix` of type `T` as input, casts it to a `String`, 
    //and returns the result of calling a private helper method `auto` with the `root` of the `BinarySearchTree` and the `prefix` as arguments.
    public String auto(T prefix) {
        String prefixWord = (String) prefix;
        return auto(root, prefix);
    }

    //This code defines a recursive function to find the word in a binary search tree that most closely matches a given prefix,
    // using the Levenshtein distance algorithm to calculate the edit distance between the prefix and each node's word.
    public String auto(Node<T> node, T prefix) {
        if (node == null) {
            return null;
        }

        String prefixWord = (String) prefix;
        String word = (String) node.word;

        if (word.startsWith(prefixWord)) {
            return word;
        }

        int dist = getLevenshteinDistance(prefixWord, node.word);
        if (dist <= 1) {
            return word;
        }

        String leftResult = auto(node.left, prefix);
        String rightResult = auto(node.right, prefix);

        if (leftResult == null) {
            return rightResult;
        } else if (rightResult == null) {
            return leftResult;
        } else {
            int leftDist = getLevenshteinDistance(prefixWord, (T) leftResult);
            int rightDist = getLevenshteinDistance(prefixWord, (T) rightResult);

            if (leftDist <= rightDist) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }

    //This code defines a method that returns an ArrayList of all nodes in the 
    //Binary Search Tree by recursively traversing the tree from the root node.
    public ArrayList<Node> getAllNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        getAllNodes(root, nodes);
        return nodes;
    }

    //This code recursively traverses the binary search tree and adds each node to an ArrayList of nodes.
    private void getAllNodes(Node node, ArrayList<Node> nodes) {
        if (node == null) {
            return;
        }
        getAllNodes(node.left, nodes);
        nodes.add(node);
        getAllNodes(node.right, nodes);
    }

    // This code calculates and returns the Levenshtein distance between two strings, 
    //which is the minimum number of single-character edits (insertions, deletions, or substitutions) required to transform one string into the other.
    private int getLevenshteinDistance(String s1, T word2) {
        String s2 = (String) word2;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public int getNodeCount() {
        return getNodeCount(root);
    }

    // This recursive method returns the total count of nodes in a binary search tree starting from a given node.
    private int getNodeCount(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            int count = 1;
            count += getNodeCount(node.left);
            count += getNodeCount(node.right);
            return count;
        }
    }
}
