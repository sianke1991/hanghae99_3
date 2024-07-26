import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> result = new ArrayList<>();
        //각 장르 당 재생 횟수
        Map<String, Long> genrePlayMap = new HashMap<>();
        //각 장르 당 고유 번호 목록
        Map<String, List<Integer>> genreIndicesMap = new HashMap<>();

        //step a. 각 장르 당 재생 횟수를 헤아린다.
        for (int i=0; i<genres.length; i++) {
            final String genre = genres[i];
            Long play = genrePlayMap.get(genre);
            if (play==null) genrePlayMap.put(genre, (long)plays[i]);
            else genrePlayMap.put(genre, play+plays[i]);
            List<Integer> indices = genreIndicesMap.get(genre);
            if (indices==null) genreIndicesMap.put(genre, new ArrayList<>(List.of(i)));
            else indices.add(i);
        } //i loop
        List<Map.Entry<String, Long>> genrePlayList = new ArrayList<>(genrePlayMap.entrySet());
        Collections.sort(genrePlayList, (a, b)->b.getValue().compareTo(a.getValue()));

        //step b. 가장 많이 재생된 장르부터 앨범에 담는다.
        for (var entry:genrePlayList) {
            final String genre = entry.getKey();
            List<Integer> indices = genreIndicesMap.get(genre);
            //해당 장르에 곡이 하나만 있는 경우-
            if (indices.size()<=1) {
                result.add(indices.get(0));
                continue;
            }
            //해당 장르에 곡이 둘 이상 있는 경우-
            Collections.sort(indices, new Comparator<Integer>() {
                @Override
                public int compare(Integer index0, Integer index1) {
                    int cmpr = Integer.compare(plays[index1], plays[index0]);
                    if (cmpr!=0) return cmpr;
                    else return index0.compareTo(index1);
                }
            });
            result.add(indices.get(0));
            result.add(indices.get(1));
        } //entry loop

        //step c. List<Integer>를 int[]로 변환하고 반환함
        int[] answer = new int[result.size()];
        for (int i=0; i<answer.length; i++)
            answer[i] = result.get(i);
        return answer;
    }
}