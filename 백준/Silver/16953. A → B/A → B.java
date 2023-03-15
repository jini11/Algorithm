import java.io.*;

public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int a = Integer.parseInt(str.split(" ")[0]);
        int b = Integer.parseInt(str.split(" ")[1]);
        int result = 0;

        while(a!=b) {
            if(b<a) {
                result = -2;
                break;
            }
            if(b%2==0) {
                b /= 2;
                result++;
            }
            else if(b%10==1) {
                b /= 10;
                result++;
            }
            else {
                result = -2;
                break;
            }
        }
       
        System.out.println(result+1);
    }
}