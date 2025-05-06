package com.example.treeworkshop.service;


import com.example.treeworkshop.model.BinaryTree;
import com.example.treeworkshop.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class ServiceBinaryTree {

    private final BinaryTree tree = new BinaryTree();

    public TreeNode getRoot() {
        return tree.getRoot();
    }

    public boolean isEmpty(){
        return tree.root == null;
    }

    public boolean exist(int data) {
        return existRecursive(tree.root, data);
    }

    private boolean existRecursive(TreeNode current, int data) {
        if (current == null) {
            return false;
        }
        if (data == current.data) {
            return true;
        }
        return data < current.data
                ? existRecursive(current.left, data)
                : existRecursive(current.right, data);
    }

    public void addRoot(int data) {

        tree.root = addRecursive(tree.root,  data);
    }

    private TreeNode addRecursive(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }
        if (data == current.data) {
            return current;
        }
        if (data < current.data) {
            current.left = addRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = addRecursive(current.right, data);
        }

        return current;
    }

    public List<Integer> inOrderRout() {
        List<Integer> result = new ArrayList<>();
        inOrderRecursive(tree.root, result);
        return result;
    }

    private void inOrderRecursive(TreeNode node, List<Integer> result) {
        if (node!= null) {
            inOrderRecursive(node.left, result);
            result.add(node.data);
            inOrderRecursive(node.right, result);
        }
    }

    public List<Integer> preOrderRout() {
        List<Integer> result = new ArrayList<>();
        preOrderRecursive(tree.root, result);
        return result;
    }

    private void preOrderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.data);
            preOrderRecursive(node.left, result);
            preOrderRecursive(node.right, result);

        }
    }

    public List<Integer> postOrderRout() {
        List<Integer> result = new ArrayList<>();
        postOrderRecursive(tree.root, result);
        return result;
    }

    private void postOrderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            postOrderRecursive(node.left, result);
            postOrderRecursive(node.right, result);
            result.add(node.data);
        }
    }

    public int getWeight() {
        return countNodes(tree.root);
    }

    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int getHeight() {
        return calculateHeigth(tree.root);
    }

    private int calculateHeigth(TreeNode node) {
        if (node == null) return -1;  // Altura de árbol vacío es -1
        return 1 + Math.max(calculateHeigth(node.left), calculateHeigth(node.right));
    }

    public int getLevel(int data) {
        return findLevel(tree.root, data, 0);
    }

    private int findLevel(TreeNode node, int data, int level) {
        if (node == null) return -1;
        if (node.data == data) return level;

        int levelLeft = findLevel(node.left, data, level + 1);
        if (levelLeft != -1) return levelLeft;

        return findLevel(node.right, data, level + 1);
    }

    public int countLeaves() {
        return countLeavesRecursive(tree.root);
    }

    private int countLeavesRecursive(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRecursive(node.left) + countLeavesRecursive(node.right);
    }

    public Integer getLower() {
        if (tree.root == null) return null;
        TreeNode current = tree.root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    public Integer getHigher() {
        if (tree.root == null) return null;
        TreeNode current = tree.root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public List<Integer> printAmplitude() {
        List<Integer> result = new ArrayList<>();
        if (tree.root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree.root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.data);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return result;
    }

    public void eliminate(int data) {
        tree.root = eliminateRecursive(tree.root, data);
    }

    private TreeNode eliminateRecursive(TreeNode node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = eliminateRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = eliminateRecursive(node.right, data);
        } else {
            // Nodo encontrado
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            TreeNode lower = findLower(node.right);
            node.data = lower.data;
            node.right = eliminateRecursive(node.right, lower.data);
        }

        return node;
    }

    private TreeNode findLower(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void eliminateTree() {
        tree.root = null;
    }
}
