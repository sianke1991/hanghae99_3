/*
    L101의 재귀적 풀이: 왼쪽 노드의 left와 오른쪽 노드의 right가 같고,
                        왼쪽 노드의 right와 왼쪽 노드의 left가 같으면
                        해당 이진 트리는 대칭적이라는 사실을 이용한다.
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
class Solution {
    /**
     * root 노드를 기준으로 대칭 위치에 있는 두 노드 left, right를 입력 받아
     * 두 노드가 서로 같은지,
     * 또한 서로 대칭 위치에 있는 두 노드의 자식 노드가 서로 같은지
     * 여부를 반환한다.
     */
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        //두 노드 중 null이 있으면 더 이상 재귀 호출을 하지 않는다: 베이스 케이스
        if (left==null && right==null)
            return true;
        if (left==null || right==null)
            return false;
        if (left.val!=right.val)
            return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root==null)
            return true;
        TreeNode left = root.left;
        TreeNode right = root.right;
        return isSymmetric(left, right);
    }
}