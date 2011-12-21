package adt.search;

import java.util.Comparator;
import java.util.PriorityQueue;

class IndexComparator implements Comparator<Index> {
	private Index goal;
	
	public IndexComparator(Index goal) {
		this.goal = goal;
	}
	
	@Override
	public int compare(Index lhs, Index rhs) {
		int r1 = Math.abs(lhs.y - goal.y) + Math.abs(lhs.x - goal.x);
		int r2 = Math.abs(rhs.y - goal.y) + Math.abs(rhs.x - goal.x);
		
		return r1 - r2;
	}
}

public class MazeSolverAStar implements MazeSolver {

    @Override
    public String solve(char[][] m, Index start, Index goal) {
        final int HEIGHT = m.length;
        final int WIDTH  = m[0].length;
        
        char[][] visited = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                visited[i][j] = ' ';
            }
        }
        
        PriorityQueue<Index> q = new PriorityQueue<Index>(100, new IndexComparator(goal));
        q.add(start);
        
        while (!q.isEmpty()) {
            Index idx = q.poll();
            int x = idx.x, y = idx.y;

            if (x == goal.x && y == goal.y) {
                return getPath(visited, start, goal);
            }
            
            // up
            if (0 <= y - 1 && m[y-1][x] != '#' && visited[y-1][x] == ' ') {
                visited[y-1][x] = 'N';
                q.add(new Index(x, y - 1));
            }
            // down
            if (y + 1 < HEIGHT && m[y+1][x] != '#' && visited[y+1][x] == ' ') {
                visited[y+1][x] = 'S';
                q.add(new Index(x, y + 1));
            }
            // left
            if (0 <= x - 1 && m[y][x-1] != '#' && visited[y][x-1] == ' ') {
                visited[y][x-1] = 'W';
                q.add(new Index(x - 1, y));
            }
            // right
            if (x + 1 < WIDTH && m[y][x+1] != '#' && visited[y][x+1] == ' ') {
                visited[y][x+1] = 'E';
                q.add(new Index(x + 1, y));
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
