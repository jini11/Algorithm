import java.util.*;

class Solution {
    static class Music implements Comparable<Music> {
        int idx, play;
        public Music(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
        
        public int compareTo(Music o) {
            if (this.play == o.play) {
                return this.idx - o.idx;
            }
            return o.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<String> genre = new ArrayList<>(map.keySet());
        
        genre.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });
        
        // for (int i = 0; i < genre.size(); i++) {
        //     System.out.println(genre.get(i));
        // }
        
        List<Integer> res = new ArrayList<>();
        for (String g : genre) {
            List<Music> total = new ArrayList<>();
            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(g)) {
                    total.add(new Music(i, plays[i]));
                }
            }
            Collections.sort(total);
            res.add(total.get(0).idx);
            if (total.size() > 1) {
                res.add(total.get(1).idx);
            }
        }
        
        answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }
}

// 속한 노래가 많이 재생된 장르
// 장르 내에서 많이 재생된 노래
// 장르 내에서 재생 횟수0 고유 번호 낮은 노래
