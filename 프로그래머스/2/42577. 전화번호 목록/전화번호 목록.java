import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashMap<String,Integer> map=new HashMap<>();
        
        for(String number: phone_book){
            map.put(number,0);
        }
        
        for(String key:map.keySet()){
            for(int j=1;j<key.length();j++){
                if(map.containsKey(key.substring(0,j)))
                    answer=false;
            }
        }
        // 이 부분 중요

        return answer;
    }
}

//정렬 (굳이?) XX
//i번째 숫자길이(len) 알아내고, i+1번째 len만큼 잘라내 비교
//맞으면 false로 바꾸고, 다르면 유지
//이 과정을 0번째부터 phone_book의 길이 -1 만큼 반복