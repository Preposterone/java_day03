import java.util.Scanner;

public class Program {
	private static final String HEN = "Hen";
	private static final String EGG = "Egg";
	private static final String HUMAN = "Human";

	private static int count = 10;
	private static int sleepFor = 0;

	public static void main(String[] args) throws InterruptedException {
		if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
			getCountFromArgs(args);
		}

		MyThread henPrinter = new MyThread(HEN, count, sleepFor);
		MyThread eggPrinter = new MyThread(EGG, count, sleepFor);
		henPrinter.start();
		eggPrinter.start();

		for (int i = 0; i < count; i++) {
			if (sleepFor > 0) {
				Thread.sleep(sleepFor);
			}
			System.out.println(HUMAN);
		}
	}

	private static void getCountFromArgs(String[] args) {
		Scanner parseArgs = new Scanner(args[0]).useDelimiter("=");

		if (parseArgs.next().equals("--count")) {
			if (parseArgs.hasNextInt()) {
				count = parseArgs.nextInt();
			} else {
				System.out.println("Invalid argument specified, setting count to default (10)");
			}
		}
		parseArgs.close();
		if (args.length > 1) {
			parseArgs = new Scanner(args[1]);
			if (parseArgs.hasNextInt()) {
				sleepFor = parseArgs.nextInt();
			}
		}
	}
}
