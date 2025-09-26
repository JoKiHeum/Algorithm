import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 갯수
        HashMap<String, Integer> genre = new HashMap();
        // 노래 별 재생횟수
        HashMap<String, HashMap<Integer,Integer>> playlist = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (!genre.containsKey(genres[i])) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                playlist.put(genres[i], map);
                genre.put(genres[i], plays[i]);
            } else {
                playlist.get(genres[i]).put(i, plays[i]);
                genre.put(genres[i], genre.get(genres[i]) + plays[i]);
            }
        }
        
        for (String key: genre.keySet()) {
            System.out.println(key + ": " + genre.get(key));
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        List<String> keySet = new ArrayList(genre.keySet());
        Collections.sort(keySet, (o1, o2) -> genre.get(o2) - genre.get(o1));
        
        for (String key: keySet) {
            HashMap<Integer, Integer> map = playlist.get(key);
            List<Integer> genreKey = new ArrayList(map.keySet());
            
            Collections.sort(genreKey, (s1, s2) -> map.get(s2) - map.get(s1));
            answer.add(genreKey.get(0));
            if (genreKey.size() > 1) {
                answer.add(genreKey.get(1));
            }
        }
        
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
}