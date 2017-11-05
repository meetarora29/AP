// Meet Arora 2016055

import java.io.*;
import java.util.concurrent.*;
import java.util.*;

class PascalTriangle extends RecursiveTask<Long> {
	private final int n, k;
	private static Map<String, PascalTriangle> instances=new HashMap<String, PascalTriangle>();

	private PascalTriangle(int n, int k) {
		this.n=n;
		this.k=k;
	}

	public synchronized static PascalTriangle getInstance(int n, int k) {
		String key=n+", "+k;
		if(!instances.containsKey(key)) {
			instances.put(key, new PascalTriangle(n, k));
		}
		return instances.get(key);
	}

	public Long compute() {
		if(n==0 || k==0 || n==k)
			return 1L;

		PascalTriangle left=PascalTriangle.getInstance(n-1, k-1);
		PascalTriangle right=PascalTriangle.getInstance(n-1, k);

		left.fork();
		return right.compute() + left.join();
	}
}

public class Main {
	static int recurse(int n, int k) {
		if(n==0 || n==k || k==0)
			return 1;

		int left=recurse(n-1, k-1);
		int right=recurse(n-1, k);

		return left+right;
	}

	public static void main(String[] args) {
		Scanner Reader=new Scanner(System.in);
		int n, k;
		n=Reader.nextInt();
		k=Reader.nextInt();
		while(n<0 || k<0) {
			System.out.println("Wrong Input");
			n=Reader.nextInt();
			k=Reader.nextInt();
		}

		long startTime=System.nanoTime();
		ForkJoinPool pool=new ForkJoinPool(1);
		PascalTriangle task=PascalTriangle.getInstance(n, k);
		Long result=pool.invoke(task);
		System.out.println(result);
		System.out.println();
		long endTime=System.nanoTime();
		long flyweightTime=endTime-startTime;
		System.out.println(flyweightTime);

		startTime=System.nanoTime();
		pool=new ForkJoinPool(2);
		task=PascalTriangle.getInstance(n, k);
		result=pool.invoke(task);
		// System.out.println(result);
		endTime=System.nanoTime();
		long flyweightTime2=endTime-startTime;
		System.out.println(flyweightTime2);

		startTime=System.nanoTime();
		pool=new ForkJoinPool(3);
		task=PascalTriangle.getInstance(n, k);
		result=pool.invoke(task);
		// System.out.println(result);
		endTime=System.nanoTime();
		long flyweightTime3=endTime-startTime;
		System.out.println(flyweightTime3);

		startTime=System.nanoTime();
		int temp=recurse(n, k);
		endTime=System.nanoTime();
		long recursiveTime=endTime-startTime;
		System.out.println(recursiveTime);
	}
}
