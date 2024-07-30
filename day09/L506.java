import java.util.*;
class Solution {
    private static class ScoreIndexPair implements Comparable<ScoreIndexPair> {
        public final int score;
        public final int index;
        public ScoreIndexPair(int score, int index) {
            this.score = score;
            this.index = index;
        }

        @Override
        public int compareTo(ScoreIndexPair that) {
            return Integer.compare(that.score, this.score);
        }
    }

    public String[] findRelativeRanks(int[] score) {
        Queue<ScoreIndexPair> pq = new PriorityQueue<>();
        for (int i=0; i<score.length; i++)
            pq.add(new ScoreIndexPair(score[i], i));

        int rank = 1;
        String[] result = new String[score.length];
        while (!pq.isEmpty()) {
            int index = pq.poll().index;
            switch (rank) {
                case 1: result[index] = "Gold Medal"; break;
                case 2: result[index] = "Silver Medal"; break;
                case 3: result[index] = "Bronze Medal"; break;
                default: result[index] = Integer.toString(rank); break;
            } //switch rank
            rank++;
        } //while loop

        return result;
    }
}