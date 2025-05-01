package com.example.treeworkshop.service;


import com.example.treeworkshop.model.BinaryTree;
import com.example.treeworkshop.model.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBinaryTree {

    private final BinaryTree tree = new BinaryTree();

    public boolean isEmpty(){
        return tree.root == null;
    }

    public void addRoot(int data) {
        tree.root = addRecursive(tree.root,  data);
    }

    private Node addRecursive(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.data) {
            current.left = addRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = addRecursive(current.right, data);
        }

        return current;
    }

    public List<Integer> routInOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRecursive(tree.root, result);
        return result;
    }

    private void inOrderRecursive(Node node, List<Integer> result) {
        if (node!= null) {
            inOrderRecursive(node.left, result);
            result.add(node.data);
            inOrderRecursive(node.left, result);
        }
    }

}
