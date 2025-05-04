package com.example.treeworkshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
