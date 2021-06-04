
import java.util.Scanner;

enum Type
{
	CONSUMER,
	PRODUCER
}

public class Program {
	private static final String HEN = "Hen";
	private static final String EGG = "Egg";

	private static int count = 10;

	public static void main(String[] args) {
		if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
			getCountFromArgs(args);
		}

		MyThread henPrinter = new MyThread(EGG, count, Type.CONSUMER);
		MyThread eggPrinter = new MyThread(HEN, count, Type.PRODUCER);

		henPrinter.start();
		eggPrinter.start();
	}

	private static void getCountFromArgs(String [] args)	{
		Scanner parseArgs = new Scanner(args[0]).useDelimiter("=");

		if (parseArgs.next().equals("--count"))	{
			if (parseArgs.hasNextInt()) {
				count = parseArgs.nextInt();
			}	else 	{
				System.out.println("Invalid argument specified, setting count to default (10)");
			}
		}
		parseArgs.close();
	}
}
