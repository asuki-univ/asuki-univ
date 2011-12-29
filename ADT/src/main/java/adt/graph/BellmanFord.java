package adt.graph;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord implements ShortestPathFinder {

    @Override
    public Result find(Graph g, Vertex s) {
        Map<Vertex, Integer> p = new HashMap<Vertex, Integer>();

        // 初期化
        // 開始点以外は安全に距離を無限大にしておく
        for (Vertex v : g.vertices())
            p.put(v, Integer.MAX_VALUE);
        // 開始店の距離は 0 とする。
        p.put(s, 0);

        // 変更がなくなるまでより短い経路がないか探し、update していく
        // ある点からいける点のうち、より短い距離でいける点があれば距離を update する。
        // もし負の閉路があれば、負の閉路をぐるぐる回ることで距離をいくらでも短くすることが出来る。
        // そのような場合、update が止まらなくなるため、頂点の個数だけ回せば打ち切る。
        // それ以上ループが回っている場合は、負の閉路があると判定できる。
        // NOTE: なぜ頂点の回数だけ回せば良いのか？
        //   もっとも枝を使う経路は、全ての点を回る経路である。それ以上たくさん枝を使う経路は
        //   かならず元に所に戻ってくるような path になっている。
        //   したがって、その場合、無駄な経路をたどっていることになる。
        boolean changed;
        int cnt = 0;
        do {
            changed = false;
            for (Vertex v : g.vertices()) {
                for (Edge e : g.edges(v)) {
                    int oldCost = p.get(e.e);
                    int newCost = p.get(e.s) + e.weight();
                    if (newCost < oldCost) {
                        p.put(e.e, newCost);
                        changed = true;
                    }
                }
            }
        } while (changed && cnt++ <= g.size());
                
        return new Result(p, cnt > g.size());
    }
    
    @Override
    public boolean acceptsNegativeEdge() {
        return true;
    }
}
