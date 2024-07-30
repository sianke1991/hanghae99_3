import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] operations = new int[N];
        for (int i=0; i<N; i++)
            operations[i] = sc.nextInt();
        sc.close();
        Queue<Integer> pq = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        for (int operation:operations) {
            //add
            if (operation!=0) {
                pq.add(operation);
                continue;
            }
            //poll
            if (pq.isEmpty()) {
                result.add(0);
            } else {
                result.add(pq.poll());
            }
        } //operation loop
        
        StringBuilder output = new StringBuilder();
        for (int elem:result) {
            output.append(elem);
            output.append('\n');
        } //elem loop
        System.out.print(output);
    }
}