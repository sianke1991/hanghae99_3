/*
    L101의 반복적 풀이: 이진 트리에서 깊이가 같은 노드를 모아서 해당 노드들이 대칭을 이루면 다음 깊이로 넘어가는 방식을 사용한다.
    left 노드 혹은 right 노드를 조회하는데 null 이 나오는 경우 그 서브 트리는 더 이상 조회하지 않는다.
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
    /**
     * 이진 트리의 깊이가 같은 노드를 모은 List를 입력받아, <br/>
     * 노드의 null 여부가 대칭을 이루는지 여부를 반환한다.
     */
    private boolean checkSymmetricNodes(List<TreeNode> nodeList) {
        final int sz = nodeList.size();
        for (int i=0, lim=sz/2; i<lim; i++) {
            int j = sz-1-i;
            if (nodeList.get(i)==null && nodeList.get(j)!=null) return false;
            if (nodeList.get(i)!=null && nodeList.get(j)==null) return false;
        }
        return true;
    }
    
    /**
     * 이진 트리의 깊이가 같은 노드의 값(val)을 모은 List를 입력받아, <br/>
     * 값이 대칭을 이루는지 여부를 반환한다.
     */
    private boolean checkSymmetricVals(List<Integer> valList) {
        final int sz = valList.size();
        for (int i=0, lim=sz/2; i<lim; i++) {
            int j = sz-1-i;
            //int가 아닌 Integer를 비교하는 것이므로 == 대신 .equals를 사용한다.
            if (!valList.get(i).equals(valList.get(j))) return false;
        }
        return true;
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        //이진 트리에서 깊이가 같은 노드를 모은 List
        List<TreeNode> nodeList = new ArrayList<>();
        //nodeList에서 null이 아닌 노드만 모은 List
        List<TreeNode> notNullNodeList = new ArrayList<>();
        //이진 트리에서 깊이가 같은 노드의 값(val)을 모은 List
        List<Integer> valList = new ArrayList<>();

        //깊이 1의 대칭성 조사:
        //step a. nodeList를 구성한다.
        nodeList.add(root.left);
        nodeList.add(root.right);
        //step b. nodeList의 대칭성을 조사한다.
        if (!checkSymmetricNodes(nodeList)) return false;
        //step c. nodeList에서 null인 노드를 제외하여 notNullNodeList를 구성한다.
        if (root.left!=null) notNullNodeList.add(root.left);
        if (root.right!=null) notNullNodeList.add(root.right);
        while (!notNullNodeList.isEmpty()) {
            //step d. valList를 구성한다.
            valList.clear();
            for (TreeNode node:notNullNodeList)
                valList.add(node.val);
            //step e. valList의 대칭성을 조사한다.
            if (!checkSymmetricVals(valList)) return false;

            //다음 깊이의 대칭성 조사:
            //step a. nodeList를 구성한다.
            nodeList.clear();
            for (TreeNode node:notNullNodeList) {
                nodeList.add(node.left);
                nodeList.add(node.right);
            }
            //step b. nodeList의 대칭성을 조사한다.
            if (!checkSymmetricNodes(nodeList)) return false;
            //step c. nodeList에서 null인 노드를 제외하여 notNullNodeList를 구성한다.
            notNullNodeList.clear();
            for (TreeNode node:nodeList) {
                if (node!=null) notNullNodeList.add(node);
            }
        } //while loop (notNullNodeList가 비었다는 것은 그 시점에서 이진 트리가 뻗어나가지 않음을 의미한다.)
        
        return true;
    }
}