import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        //step a. data를 기준에 맞게 정렬한다.
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] row0, int[] row1) {
                int cmpr = Integer.compare(row0[col-1], row1[col-1]);
                if (cmpr!=0) return cmpr;
                else return Integer.compare(row1[0], row0[0]);
            }
        });

        //step b. row_begin번 부터 row_end번 까지 행에 대해 s 값을 계산한다.
        List<Integer> sList = new ArrayList<>();
        for (int i=row_begin-1; i<row_end; i++) {
            int s = 0;
            for (int elem:data[i]) s+=elem%(i+1);
            sList.add(s);
        } //i loop
        //System.out.println(sList);
        
        //step c. 계산한 s 값에 대해 배타적논리합을 계산하고 반환한다.
        int answer = 0;
        for (int sValue:sList)
            answer^=sValue;
        return answer;
    }
}