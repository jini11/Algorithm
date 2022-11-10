import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static char[][] map;
	static int[] train = new int[2];
	static char trainDir;
	static int[] dx = {1, -1};
	static int h, w;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = br.readLine();
			h = Integer.parseInt(line.split(" ")[0]);
			w = Integer.parseInt(line.split(" ")[1]);
			map = new char[h][w];
			for (int i=0; i<h; i++) {
				line = br.readLine();
				for (int j=0; j<w; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v') {
						trainDir = map[i][j];
						train[0] = i;
						train[1] = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String[] way = br.readLine().split("");
			
			for (int i=0; i<way.length; i++) {
				switch (way[i]) {
				case "U":
					move(-1, 0, '^');
					break;
				case "D":
					move(1, 0, 'v');
					break;
				case "L":
					move(0, -1, '<');
					break;
				case "R":
					move(0, 1, '>');
					break;
				case "S":
					if (trainDir == '^') {
						shoot(-1, 0);
					} else if (trainDir == 'v') {
						shoot(1, 0);
					} else if (trainDir == '<') {
						shoot(0, -1);
					} else if (trainDir == '>') {
						shoot(0, 1);
					}
					break;
				}
			}
			
			System.out.print("#" + test_case + " ");
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	public static void move(int dx, int dy, char dir) {
		trainDir = dir;
        int nx = train[0] + dx;
        int ny = train[1] + dy;
        if (nx < 0 || ny < 0 || nx >= h || ny >= w || map[nx][ny] == '*' || map[nx][ny] == '#' || map[nx][ny] == '-') {
            map[train[0]][train[1]] = trainDir;
            return;
        }
        map[train[0]][train[1]] = '.';
        map[nx][ny] = trainDir;
        train[0] = nx;
        train[1] = ny;
	}
	
	public static void shoot(int dx, int dy) {
		int x = train[0];
		int y = train[1];
		
		while (true) {
			int nx = x + dx;
			int ny = y + dy;
			if (nx < 0 || ny < 0 || nx >= h || ny >= w || map[nx][ny] == '#') break;
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				break;
			}
			x = nx;
			y = ny;
		}
	}
	
}