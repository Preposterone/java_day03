import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Program {
	private static final int MODULO_LIMIT = 1000;
	private static int arraySize = 13;
	private static int threadsCount = 3;

	public static void main(String[] args) {
		if (args.length > 0) {
			parseArgs(args);
		}

		List<Integer> sumList = generateListOfRandInts();

		System.out.println("Sum: " + sumList.stream().flatMapToInt(IntStream::of).sum());

		int splitBy = (threadsCount > 0 ? arraySize / threadsCount : 1);

		int[] resultArr = new int[threadsCount];
		for (int i = 0, j = 0; i < threadsCount; i++) {
			if (i < threadsCount - 1) {
				new ThreadCalculator(sumList.subList(j, j + splitBy), resultArr, i, j, j + splitBy).run();
				j += splitBy;
			} else {
				new ThreadCalculator(sumList.subList(j, arraySize), resultArr, i, j, arraySize).run();
			}
		}
		System.out.println("Sum by threads: " + Arrays.stream(resultArr).sum());
	}

	private static List<Integer> generateListOfRandInts() {
		return (new Random().ints(arraySize, -MODULO_LIMIT, MODULO_LIMIT)
				.boxed()
				.collect(Collectors.toList()));
	}

	private static void parseArgs(String[] args) {
		Scanner parseArgs = new Scanner(args[0]).useDelimiter("=");

		if (parseArgs.next().equals("--arraySize")) {
			if (parseArgs.hasNextInt()) {
				arraySize = parseArgs.nextInt();
				if (arraySize < 0)	{
					System.err.println("Invalid argument specified, setting arraySize to default (13)");
					arraySize = 13;
				}
			} else {
				System.err.println("Invalid argument specified, setting arraySize to default (13)");
			}
		}
		parseArgs.close();
		if (args.length > 1) {
			parseArgs = new Scanner(args[1]).useDelimiter("=");
			if (parseArgs.next().equals("--threadsCount")) {
				if (parseArgs.hasNextInt()) {
					threadsCount = parseArgs.nextInt();
					if (threadsCount < 0)	{
						System.err.println("Invalid argument specified, setting threadsCount to default (3)");
						threadsCount = 3;
					}
				} else {
					System.err.println("Invalid argument specified, setting threadsCount to default (3)");
				}
			}
		}
		parseArgs.close();
	}
}
