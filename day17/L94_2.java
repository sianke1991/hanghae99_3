/*
이진 트리의 중위 순회: 반복:깊이 우선 탐색 방법 사용
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
        List<Integer> result = new ArrayList<>();
        Set<TreeNode> visitedNodes = new HashSet<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode currentNode = s.peek();
            if (currentNode==null) {
                s.pop();
                continue;
            }
            //해당 노드에 왼쪽 자식 노드가 없거나,
            //해당 노드의 왼쪽 자식을 이미 탐색한 경우,
            //해당 노드를 방문하고 오른쪽 자식 노드로 넘어간다.
            if (currentNode.left==null || visitedNodes.contains(currentNode.left)) {
                result.add(currentNode.val);
                s.pop();
                s.push(currentNode.right);
            } else {
                //해당 노드에 아직 탐색하지 않은 왼쪽 자식 노드가 있는 경우
                //왼쪽 자식 노드로 넘어간다.
                visitedNodes.add(currentNode.left);
                s.push(currentNode.left);
            }
        } //while loop
        
        return result;
    }
}