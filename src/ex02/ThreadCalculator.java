import java.util.List;

public class ThreadCalculator implements Runnable {
	private final List<Integer> sumList;
	private final int[] resultArr;
	private final int threadIndex;
	private final int from;
	private final int to;


	public ThreadCalculator(List<Integer> sumList, int[] resultArr, int threadIndex, int from, int to) {
		this.sumList = sumList;
		this.resultArr = resultArr;
		this.threadIndex = threadIndex;
		this.from = from;
		this.to = to;
	}

	@Override
	public void run() {
		int sum = 0;

		for (Integer num : sumList) {
			sum += num;
		}
		resultArr[threadIndex] = sum;
		System.out.println(this);
	}


	@Override
	public String toString() {
		return ("Thread " + (threadIndex + 1)
				+ ": from " + from
				+ " to " + (to - 1)
				+ " sum is " + resultArr[threadIndex]);
	}
}
