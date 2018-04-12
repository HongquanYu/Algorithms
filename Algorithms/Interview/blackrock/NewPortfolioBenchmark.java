package blackrock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;

/** @author: Hongquan Yu
 *  @date: Mar 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NewPortfolioBenchmark {

	/**
	 * Iterate through each line of input.
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		String line, input = "";
		while ((line = in.readLine()) != null) {
			input += line;
		}

		String s1 = "PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20";
		// Expected: AXN:-15.0,BGT:15.0,CXZ:30.0,DFG:-30.0
		String s2 = "PORT:AXN,0,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20;XYZ,0,10";
		// Expected: AXN:-25.00,BGT:21.67,CXZ:33.33,DFG:-30.00,XYZ:-0.00
		String s3 = "PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20";
		// Expected: AXN:-15.00,BGT:15.00,CXZ:30.00,DFG:-30.00

		calculateDiff(input);
	}

	/**
	 * Calculate the difference between portfolio and benchmark To solve it,
	 * there are 3 steps: 1, parse input string and store to TreeMap; 2,
	 * calculate the ratio of the two; 3, generate output according to question
	 * description.
	 * 
	 * Why we use TreeMap, instead of HashMap? Because TreeMap maintains natural
	 * orders of key!
	 */

	public static void calculateDiff(String s) {
		if (s == null || s.length() == 0)
			return;

		Map<String, QuantityPrice> portMap = new TreeMap<>();
		Map<String, QuantityPrice> benchMap = new TreeMap<>();

		// Step 1.
		String[] half = s.split("\\|");
		if (half.length != 2)
			throw new IllegalArgumentException("Input format should be port|bench");

		if (!half[0].substring(0, 5).equals("PORT:") || !half[1].substring(0, 6).equals("BENCH:"))
			throw new IllegalArgumentException("Port should start with PORT:, so as bench");

		int totalPort = putIntoMap(half[0], portMap, 5);
		int totalBench = putIntoMap(half[1], benchMap, 6);

		// Step 2.
		Map<String, Double> benchRatio = new TreeMap<>();
		Map<String, Double> portRatio = new TreeMap<>();

		calculateRatio(portMap, portRatio, totalPort);
		calculateRatio(benchMap, benchRatio, totalBench);

		// Step 3.
		generateOutput(portRatio, benchRatio);
	}

	/**
	 * generate formated ouput using the two ratios maps!
	 */

	public static void generateOutput(Map<String, Double> portRatio, Map<String, Double> benchRatio) {
		StringBuilder output = new StringBuilder();
		for (Entry e : benchRatio.entrySet()) {
			String key = (String) e.getKey();
			if (portRatio.containsKey(key)) {
				double portValue = portRatio.get(key);
				double benchValue = benchRatio.get(key);
				portRatio.put(key, portValue - benchValue);
			} else {
				double benchValue = benchRatio.get(key);
				portRatio.put(key, -benchValue);
			}
		}

		for (Entry e : portRatio.entrySet()) {
			String key = (String) e.getKey();
			double value = (double) e.getValue();

			output.append(key).append(":" + String.format("%.2f", value));
			output.append(",");
		}
		output.setLength(output.length() - 1);
		System.out.print(output);
	}

	/**
	 * Calculate %NAV of portforlio and benchmark
	 */

	private static void calculateRatio(Map<String, QuantityPrice> map, Map<String, Double> ratio, int total) {
		int ptr = 0;
		for (Entry e : map.entrySet()) {
			int qty = ((QuantityPrice) e.getValue()).quantity;
			int price = ((QuantityPrice) e.getValue()).price;
			ratio.put((String) e.getKey(), qty * price * 100 / (double) total);
		}
	}

	/**
	 * Parse the data and store them into Maps.
	 */

	private static int putIntoMap(String s, Map<String, QuantityPrice> map, int discard) {
		int totalAmount = 0;

		s = s.substring(discard);

		String[] subArray = s.split(";");
		for (int i = 0; i < subArray.length; ++i) {
			String[] portElement = subArray[i].split(",");

			int qty = Integer.parseInt(portElement[1]);
			int price = Integer.parseInt(portElement[2]);

			totalAmount += (qty * price);
			map.put(portElement[0], new QuantityPrice(qty, price));
		}

		return totalAmount;
	}

	/**
	 * Entity to hold quantity and price!
	 */

	static class QuantityPrice {
		int quantity;
		int price;

		public QuantityPrice(int q, int p) {
			this.quantity = q;
			this.price = p;
		}
	}
}
