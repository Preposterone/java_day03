
public class MyThread extends Thread {

	private final String creature;
	private final int count;
	private final Type type;
	private final static Printer printer = new Printer();

	public MyThread(String creature, int count, Type type) {
		this.creature = creature;
		this.count = count;
		this.type = type;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.count; i++) {
			try {
				printer.printMessage(creature, this.type);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
