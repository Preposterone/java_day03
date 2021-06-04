public class Printer {
	private int ink = 1;

	public synchronized void printMessage(String message, Type type) throws InterruptedException {
		if (type == Type.CONSUMER) {
			while (ink < 1) {
				wait();
			}
			ink = 0;
		} else {
			while (ink == 1) {
				wait();
			}
			ink = 1;
		}
		System.out.println(message);
		notify();
	}
}
