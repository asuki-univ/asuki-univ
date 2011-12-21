package adt.datastruct;

public class Heap {
	private int pos;
	private int[] values;
	
	public Heap(int n) {
		this.pos = 0;
		this.values = new int[n];
	}
	
	int[] debugValuess() {
	    return values;
	}
	       
	public void push(int v) {
		if (values.length <= pos)
			throw new RuntimeException("Exceeds the limit.");
		
		int p = pos++;
		values[p] = v;

		while (p > 0) {
			if (values[(p - 1) / 2] <= values[p])
				break;
 			int t = values[(p - 1) / 2];
 			values[(p - 1) / 2] = values[p];
 			values[p] = t;
 			
 			p = (p - 1) / 2;
		}
	}
	
	public int pop() {
		if (pos <= 0)
			throw new RuntimeException("Exceeds the limit.");
		
		int result = values[0];
		values[0] = values[--pos];
		
		int p = 0;
		while (p < pos) {
			int left = 2*p+1 < pos ? values[2*p+1] : Integer.MAX_VALUE;
			int right = 2*p+2 < pos ? values[2*p+2] : Integer.MAX_VALUE;
			if (values[p] <= left && values[p] <= right)
				break;
			
			if (left < right) {
				values[2 * p + 1] = values[p];
				values[p] = left;
				p = 2 * p + 1;
			} else {
				values[2 * p + 2] = values[p];
				values[p] = right;
				p = 2 * p + 2;
			}
		}
		
		return result;
	}
}
