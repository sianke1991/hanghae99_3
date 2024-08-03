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
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode currentNode = root;
        while (true) {
            if (currentNode==null)
                break;
            if (currentNode.val==val)
                return currentNode;
            if (currentNode.val>val) {
                //현재 노드의 값이 목표치보다 크다면 왼쪽 자식을 탐색한다.
                currentNode = currentNode.left;
            } else {
                //현재 노드의 값이 목표치보다 작다면 오른쪽 자식을 탐색한다.
                currentNode = currentNode.right;
            }
        } //while loop
        
        return null;
    }
}