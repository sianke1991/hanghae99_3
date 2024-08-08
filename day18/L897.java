/* 
    이진 탐색 트리 (binary search tree)를 오름차순으로 순회하기 위해서는 중위 순회를 수행하면 된다.
    중위 순회로 이진 탐색 트리 내 값을 오름차순으로 정렬했다면 그 결과를 TreeNode로 만들어서 반환한다.
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
    private List<Integer> inorderTraverse(TreeNode root) {
        if (root==null) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        result.addAll(inorderTraverse(root.left));
        result.add(root.val);
        result.addAll(inorderTraverse(root.right));
        return result;
    }
    
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> traverse = inorderTraverse(root);
        TreeNode[] nodes = new TreeNode[traverse.size()];
        for (int i=0; i<nodes.length; i++)
            nodes[i] = new TreeNode(traverse.get(i));
        for (int i=0, lim=nodes.length-1; i<lim; i++)
            nodes[i].right = nodes[i+1];
        return nodes[0];
    }
}