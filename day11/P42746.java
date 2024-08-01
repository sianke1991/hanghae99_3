import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i=0; i<numbers.length; i++)
            nums[i] = Integer.toString(numbers[i]);

        //두 수 o1과 o2를 이어붙인 결과
        //o1o2와 o2o1을 비교하여 더 큰 결과를 만들어낼 수 있도록 배열 nums를 정렬한다.
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                StringBuilder o1o2 = new StringBuilder(o1).append(o2);
                StringBuilder o2o1 = new StringBuilder(o2).append(o1);
                return o2o1.compareTo(o1o2);
            }
        });

        //만약 nums 배열의 첫 원소가 "0"일 경우 nums 배열 내 모든 원소가 "0"임을 의미하므로 곧바로 "0"을 반환한다.
        if (nums[0].equals("0")) return "0";
        StringBuilder result = new StringBuilder();
        for (var elem:nums)
            result.append(elem);
        return result.toString();
    }
}