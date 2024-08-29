
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
<풀이 방법>
나이트의 시작위치와 목표위치를 입력 받고 bfs를 통해 탐색하면서

 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int size;
	static int startY, endY, startX, endX;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };

	// 입력
	static void input() throws IOException {
		size = Integer.parseInt(br.readLine());

		map = new int[size][size];
		visited = new boolean[size][size];

		st = new StringTokenizer(br.readLine());
		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		endY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());
	}

	// main
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			input();
			bfs();
			sb.append(map[endY][endX]).append("\n");
		}
		System.out.println(sb);
	}

	//bfs
	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { startY, startX });
		visited[startY][startX] = true;

		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowY = now[0];
			int nowX = now[1];

			for (int i = 0; i < 8; i++) {
				int nextY = nowY + dy[i];
				int nextX = nowX + dx[i];

				if (nextY >= 0 && nextX >= 0 && nextY < size && nextX < size) {
					if (!visited[nextY][nextX]) {
						queue.add(new int[] { nextY, nextX });
						map[nextY][nextX] = map[nowY][nowX] + 1;
						visited[nextY][nextX] = true;
					}
				}
			}
		}
	}
}
