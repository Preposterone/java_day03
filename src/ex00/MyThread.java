public class MyThread extends Thread {

	private final String creature;
	private final int count;
	private final int sleepFor;

	public MyThread(String creature, int count, int sleepFor) {
		this.creature = creature;
		this.count = count;
		this.sleepFor = sleepFor;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.count; i++) {
			try {
				if (sleepFor > 0) {
					Thread.sleep(sleepFor);
				}
			} catch (InterruptedException e) {
				System.exit(-1);
			}
			System.out.println(this.creature);
		}
	}
}
