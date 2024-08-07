/*
이진 트리의 중위 순회: 재귀 방법 사용
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root==null) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        result.addAll(inorderTraversal(root.left));  //왼쪽 자식 서브 트리 방문
        result.add(root.val);                        //해당 노드 방문
        result.addAll(inorderTraversal(root.right)); //오른쪽 자식 서브 트리 방문
        return result;
    }
}