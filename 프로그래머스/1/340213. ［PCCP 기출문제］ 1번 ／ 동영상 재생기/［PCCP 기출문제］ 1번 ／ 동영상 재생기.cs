using System;

public class Solution {
    public string solution(string video_len, string pos, string op_start, string op_end, string[] commands) {
        string answer = "";
        string[] time = pos.Split(":");
        int total = int.Parse(video_len.Split(":")[0]) * 60 + int.Parse(video_len.Split(":")[1]);
        int cur = int.Parse(time[0]) * 60 + int.Parse(time[1]);
        int open = int.Parse(op_start.Split(":")[0]) * 60 + int.Parse(op_start.Split(":")[1]);
        int end = int.Parse(op_end.Split(":")[0]) * 60 + int.Parse(op_end.Split(":")[1]);
        
        foreach (string command in commands) {
            if (open <= cur && end >= cur) {
                cur = end;
            }
            if (command == "next") {
                cur += 10;
                if (cur >= total) {
                    cur = total;
                }
            } else if (command == "prev") {
                cur -= 10;
                if (cur < 0) {
                    cur = 0;
                }
            }
            if (open <= cur && end >= cur) {
                cur = end;
            }
        }
        
        int hour = cur / 60;
        int min = cur % 60;
        
        if (hour < 10) {
            answer += "0" + hour;
        } else {
            answer += hour;
        }
        answer += ":";
        if (min < 10) {
            answer += "0" + min;
        } else {
            answer += min;
        }
        return answer;
    }
}