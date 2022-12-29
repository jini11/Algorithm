import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static boolean flag = true;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[][] map = new int[9][9];
            flag = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
             		map[i][j] = sc.nextInt();       
                }
            }
            
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {                    
             		if (i % 3 == 0 && j % 3 == 0 && flag) 
                     	chkRectangle(i, j, map);   
                }
               if (flag) {
                    chkCol(i, map);
               }
               if (flag) {
               		chkRow(i, map);
               }
            }
            if (flag) {
            	System.out.println("#" + test_case + " " + 1);
            } else {
                System.out.println("#" + test_case + " " + 0);
            }
		}
	}
    public static void chkRectangle(int x, int y, int[][] map) {
        boolean[] number = new boolean[9];
        
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                number[map[i][j]-1] = true;
            }
        }
        
        chkNumber(number);
    }
    
    public static void chkCol(int y, int[][] map) {
     	boolean[] number = new boolean[9];
        
        for (int i = 0; i < 9; i++) {
         	number[map[i][y]-1] = true;   
        }
        
        chkNumber(number);
    }
    
    public static void chkRow(int x, int[][] map) {
    	boolean[] number = new boolean[9];
        
        for (int i = 0; i < 9; i++) {
         	number[map[x][i]-1] = true;   
        }
        
        chkNumber(number);
    }
    
    public static void chkNumber(boolean[] number) {
        for (int i = 0; i < 9; i++) {
         	if (!number[i]) {   
                flag = false;
            }
        }
    }
}