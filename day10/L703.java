/*
입력 받은 값 중 k 번째로 큰 값을 구하기 위해
"최소 힙"을 사용한다.
최소 힙에 값을 넣고, 그 힙의 크기가 k가 될 때 까지 값을 제거를 하면,
최소 힙에 들어있는 값 중 가장 작은 값이 (입력 받은 값 중) k 번째로 큰 값이 된다.
 */

import java.util.*;
class KthLargest {
    
    public final int k;
    private Queue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>();
        for (int num:nums)
            pq.add(num);
        /*
        for (int i=0, lim=nums.length-k; i<lim; i++)
            pq.poll();
        */
    }
    
    public int add(int val) {
        this.pq.add(val);
        //this.pq의 크기를 k로 맞추기 위해 초과된 원소를 제거한다.
        for (int i=0, lim=this.pq.size()-k; i<lim; i++)
            this.pq.poll();
        return this.pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */