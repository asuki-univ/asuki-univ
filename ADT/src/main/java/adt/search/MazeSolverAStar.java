package adt.search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class IndexWithDistance {
    public int x;
    public int y;
    public int d;

    public IndexWithDistance(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

class IndexWithDistanceComparator implements Comparator<IndexWithDistance> {
    private Index goal;

    public IndexWithDistanceComparator(Index goal) {
        this.goal = goal;
    }

    @Override
    public int compare(IndexWithDistance lhs, IndexWithDistance rhs) {
        int r1 = Math.abs(lhs.y - goal.y) + Math.abs(lhs.x - goal.x) + lhs.d;
        int r2 = Math.abs(rhs.y - goal.y) + Math.abs(rhs.x - goal.x) + rhs.d;

        return r1 - r2;
    }
}

public class MazeSolverAStar implements MazeSolver {
    private final char[] DIR = new char[] { 'E', 'W', 'S', 'N' };
    private final int[] dx = new int[] { 1, -1, 0, 0 };
    private final int[] dy = new int[] { 0, 0, 1, -1 };

    @Override
    public String solve(char[][] m, Index start, Index goal) {
        final int H = m.length, W = m[0].length;
        char[][] visited = new char[H][W];
        for (int i = 0; i < H; ++i) { Arrays.fill(visited[i], ' '); }

        PriorityQueue<IndexWithDistance> q = new PriorityQueue<IndexWithDistance>(100, new IndexWithDistanceComparator(goal));
        q.add(new IndexWithDistance(start.x, start.y, 0));

        while (!q.isEmpty()) {
            IndexWithDistance idx = q.poll();
            int x = idx.x, y = idx.y;

            if (x == goal.x && y == goal.y) {
                return getPath(visited, start, goal);
            }

            // up
            for (int i = 0; i < 4; ++i) {
                int xx = x + dx[i], yy = y + dy[i];
                if (xx < 0 || W <= xx || yy < 0 || H <= yy) { continue; }
                if (visited[yy][xx] != ' ' || m[yy][xx] == '#') { continue; }

                visited[yy][xx] = DIR[i];
                q.add(new IndexWithDistance(xx, yy, idx.d + 1));
            }
        }

        return null;
    }

    String getPath(char[][] m, Index start, Index goal) {
        StringBuilder builder = new StringBuilder();
        int x = goal.x, y = goal.y;
        while (x != start.x || y != start.y) {
            builder.append(m[y][x]);
            switch (m[y][x]) {
            case 'N': ++y; break;
            case 'S': --y; break;
            case 'E': --x; break;
            case 'W': ++x; break;
            default: throw new RuntimeException();
            }
        }

        return builder.reverse().toString();
    }

}
