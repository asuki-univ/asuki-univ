package adt.sort;

public class RadixSort implements Sorter {

    public void sort(int[] x) {
        if (x == null) { return; }

        // 32bit を 8bit * 4 に分けてソートする。
        // ここで、負号に対応するために最後の 8bit は符号を考慮する。
        // 実際には、分布数えソートを用いる。

        for (int i = 0; i < 4; ++i) {
            // バケツ作成
            int[][] buckets = countPopulations(x, i);

            // バケツに挿入
            int[] nums = new int[256];
            for (int v : x) {
                int idx = (v >> i * 8) & 0xFF;
                buckets[idx][nums[idx]++] = v;
            }

            // バケツから取り出し
            if (i < 3) {
                // ３つめまでは負号を考慮しなくて良い。
                int idx = 0;
                for (int[] bucket : buckets) {
                    for (int v : bucket) {
                        x[idx++] = v;
                    }
                }
            } else {
                // ３つめからは負号を考慮する必要がある。
                // 10000000 -> 10000001 -> ... -> 01111111 の順番にならなければならない
                int idx = 0;
                for (int j = 0; j < 256; ++j) {
                    int[] bucket = buckets[(j + 0x80) & 0xFF];
                    for (int v : bucket) {
                        x[idx++] = v;
                    }
                }
            }
        }
    }

    private int[][] countPopulations(int[] x, int i) {
        // 分布数え
        int[] counts = new int[256];
        for (int v : x) {
            counts[(v >> (i * 8)) & 0xFF]++;
        }

        // バケツ作成
        int[][] buckets = new int[256][];
        for (int j = 0; j < 256; ++j) {
            buckets[j] = new int[counts[j]];
        }
        return buckets;
    }
}
