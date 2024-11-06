// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
크루스칼 알고리즘을 이용해 가중치가 낮은 간선부터 입력 받고,
각 별과 연결된 최상위 노드를 계속 갱신하면서
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Point {
        int num;
        double x;
        double y;

        Point(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double weight;

        Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (weight < o.weight) {
                return -1;
            }
            return 1;
        }

    }

    static int[] parent;
    static ArrayList<Edge> edgeList;
    static double answer;

    private static void input() throws IOException {
        int N = Integer.parseInt(br.readLine());
        // 별 정보 저장 배열
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points[i] = new Point(i, x, y);
        }

        // 모든 별들 간의 간선과 비용 정보를 edgeList에 넣어 둔다.
        edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double weight = distance(points[i], points[j]);

                edgeList.add(new Edge(points[i].num, points[j].num, weight));
            }
        }
        Collections.sort(edgeList); // 간선의 비용을 기준으로 오름차순 정렬.

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        answer = 0;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();
        solution();

        System.out.println(Math.round(answer * 100) / 100.0);

    }

    private static void solution(){
        // 크루스칼 알고리즘 수행.
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            if (union(edge.start, edge.end)) {
                answer += edge.weight;
            }
        }
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
            return true;
        }
        return false;
    }
}