package adt.search;

import org.junit.Assert;
import org.junit.Test;

import adt.search.Index;
import adt.search.MazeSolver;

public abstract class MazeTest {
    private MazeSolver solver;
    
    protected MazeTest(MazeSolver solver) {
        this.solver = solver;
    }
    
    @Test
    public void testSolve1() {
        char[][] map = toCharArray(new String[] {
                "#S########",
                "#........G",
                "#.######.#",
                "#........#",
                "#.######.#",
                "#........#",
                "#.######.#",
                "#........#",
                "#.######.#",
                "#........#",
                "##########",
        });
        
        String path = solver.solve(map, index(map, 'S'), index(map, 'G'));
        Assert.assertTrue(verifyPath(map, index(map, 'S'), index(map, 'G'), path));
    }

    @Test
    public void testSolve2() {
        char[][] map = toCharArray(new String[] {
                "#####S####",
                "#........#",
                "#.###.##.#",
                "#........#",
                "#.###.##.#",
                "#........#",
                "#.###.##.#",
                "#........#",
                "#.###.##.#",
                "#........#",
                "#####G####",
        });
        
        String path = solver.solve(map, index(map, 'S'), index(map, 'G'));
        Assert.assertTrue(verifyPath(map, index(map, 'S'), index(map, 'G'), path));
    }


    @Test
    public void testSolve3() {
        char[][] map = toCharArray(new String[] {
                "#####S####",
                "#........#",
                "#.###.#..#",
                "#...#.#..#",
                "###.#.#..#",
                "#...#.#..#",
                "#.###.#..#",
                "#...#.#..#",
                "###.###..#",
                "#........#",
                "#####G####",
        });
        
        String path = solver.solve(map, index(map, 'S'), index(map, 'G'));
        Assert.assertTrue(verifyPath(map, index(map, 'S'), index(map, 'G'), path));
    }

    
    private char[][] toCharArray(String[] s) {
        char[][] t = new char[s.length][];
        for (int i = 0; i < s.length; ++i) {
            t[i] = s[i].toCharArray();
        }
        return t;
    }
    
    private Index index(char[][] map, char c) {
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[y].length; ++x) {
                if (map[y][x] == c) { return new Index(x, y); }
            }
        }
        
        return null;
    }
    
    private boolean verifyPath(char[][] map, Index start, Index goal, String path) {
        int x = start.x, y = start.y;
        for (int i = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            switch (c) {
            case 'N': --y; break;
            case 'S': ++y; break;
            case 'E': ++x; break;
            case 'W': --x; break;
            }
            if (map[y][x] == '#') { return false; }
        }
        
        if (x == goal.x && y == goal.y) { return true; }
        return false;
    }
}
