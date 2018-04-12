package blackrock;

import java.util.Map;
import java.util.Map.Entry;

import java.util.TreeMap;
import java.util.TreeSet;

/** @author: Hongquan Yu
 *  @date: Mar 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PortifolioBenchmark {
	
	/**
	 * How to adjust portfolio to become equal with benchmark?
	 * Steps to solve this problem:
	 * 1, Parse the inputed string and store the original data into two TreeMap(Keep alphabetical order)
	 * 2, Calculate the transactions portfolio needs to be done, and store intermediate result into a TreeSet
	 * 3, Parse the intermediate result to the formated output.
	 */
	
	String s1 = "PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20";
	
	public String adjust(String s) {
		if (s == null || s.length() == 0)		// Corner case check
			return null;
		
		Map<String, Integer> portfolio = new TreeMap<>();	// Use TreeMap to keep key in order!
		Map<String, PriceQuantity> benchmark = new TreeMap<>();
		TreeSet<TickerChange> result = new TreeSet<>((a, b) -> a.ticker.compareTo(b.ticker));	// Java 8, sorting using ticker
		
		// Step 1: Parse inputed string and store to HashMap
		String [] splited = s.split(":");
		
		if (splited.length != 2)
			throw new IllegalArgumentException("Input Format should be a Portfoilio:Benchmark!");
		
		String [] subPort = splited[0].split("@");
		for (int i = 0; i < subPort.length; ++i) {
			String[] singlePort = subPort[i].split(",");
			portfolio.put(singlePort[0], Integer.parseInt(singlePort[2]));
		}
		
		String[] subBench = splited[1].split("@");
		for (int i = 0; i < subBench.length; ++i) {
			String[] singleBench = subBench[i].split(",");
			benchmark.put(singleBench[0], new PriceQuantity(Integer.parseInt(singleBench[2]), Double.parseDouble(singleBench[3])));
		}
		
		/*if (!isSame(portfolio.keySet(), benchmark.keySet()))
			throw new IllegalArgumentException("Portfolio and BenchMark should have same stocks!");*/
		
		// Step 2: Calculate the transactions portfolio needs to be made!
		
		// Calculate the sum of benchmark 
		double benchTotal = 0.0;
		for (Entry<String, PriceQuantity> e : benchmark.entrySet()) {
			PriceQuantity tmp = (PriceQuantity)e.getValue();
			benchTotal +=  tmp.price * tmp.quantity;
		}
		
		// Calculate the sum of portfolio 
		double portTotal = 0.0;
		for (Entry<String, Integer> e : portfolio.entrySet()) {
			int qty = portfolio.get(e.getKey());
			double unitPrice = benchmark.get(e.getKey()).price;
			
			portTotal +=  qty * unitPrice;
		}
		
		// Calculate individual ratio of benchmark
		double [] benchRatio = new double[benchmark.size()];
		int i = 0;
		for (Entry<String, PriceQuantity> e : benchmark.entrySet()) {
			PriceQuantity tmp = e.getValue();
			benchRatio[i++] = (tmp.price * tmp.quantity) / benchTotal;
		}
		
		// Calculate changes of portfolio
		calculateChange(portfolio, benchmark, benchRatio, result, portTotal);
		
		// Step 3: Parse the result set to output format
		
		String output = generateOutput(result);
		
		return output;
	}
	
	/**
	 * Calculate the transactions the portfolio needs to be done to be equal with benchmark
	 */
	
	private double calculateChange (Map<String, Integer> port, Map<String, PriceQuantity> bench,
			double [] benchRatio, TreeSet<TickerChange> result, double portTotal) {
		double res = 0.0;
		
		int k = 0;		// benchRatio pointer
		for (Entry<String, PriceQuantity> e : bench.entrySet()) {
			double currQuantity = e.getValue().quantity;
			double unitPrice = e.getValue().price;
			double changeQuantity = 0.0;
			
			if (!port.containsKey(e.getKey()) && currQuantity == 0) {
				result.add(new TickerChange(e.getKey(), -0.0));
				continue;
			}
			
			changeQuantity = ((portTotal * benchRatio[k]) - (currQuantity * unitPrice)) / unitPrice;
			
			result.add(new TickerChange(e.getKey(), changeQuantity));
			k++;
		}
		
//		for (Entry<String, Integer> e : port.entrySet()) {
//			double currQuantity = e.getValue();
//			double unitPrice = bench.get(e.getKey()).price;
//			double changeQuantity = 0.0;
//			
//			changeQuantity = ((portTotal * benchRatio[k]) - (currQuantity * unitPrice)) / unitPrice;
//			
//			result.add(new TickerChange(e.getKey(), changeQuantity));
//			k++;
//		}
		
		return res;
	}
	
	/**
	 * Generate output according to a [transaction type, ticker, quantity] format.
	 */
	
	private String generateOutput(TreeSet<TickerChange> result) {
		StringBuilder sb = new StringBuilder();
		
//		System.out.println("size " + result.size());
		for (TickerChange t : result) {
			if (t.change < 0)
				sb.append("[SELL, ");
			else 
				sb.append("[BUY, ");
			sb.append(t.ticker + ", ");
			
			String formated = String.format("%.2f", t.change);	// round to 2 decimal places.
			sb.append(formated.trim() + "], ");
		}
		
		
		return sb.substring(0, sb.length() - 2);
	}
	
	/**
	 * Check if two set contains same strings
	 */
	
	/*private boolean isSame(Set<String> set1, Set<String> set2) {
		if (set1.size() != set2.size())
			return false;
		
		for (String s : set1) {
			if (!set2.contains(s))
				return false;
		}
		
		return true;
	}*/
	
	/**
	 * Entity contains stock quantity and price!
	 */
	
	class PriceQuantity {
		int quantity;
		double price;
		
		public PriceQuantity(int q, double p) {
			this.quantity = q;
			this.price = p;
		}
	}
	
	/**
	 * Entity contains stock name and it's adjustment!
	 * BUY - when change is positive and 0;
	 * SELL - when change is negative.
	 */
	
	class TickerChange {
		String ticker;
		double change;
		
		public TickerChange(String t, double c) {
			this.ticker = t;
			this.change = c;
		}
	}
	
	/**
	 * Unit Test
	 */
	
	public static void main(String[] args) {
		String s = "RIO,RIO RINTO PLC,746@AAL,ANGLO AMERICAN PLC,271:RIO,RIO RINRO PLC,688,13.9@AAL,ANGLO AMERICAN PLC,293,49.7";
		String s1 = "AXN,RIO RINTO PLC,10@BGT,ANGLO AMERICAN PLC,30@CXZ,ANGLO AMERICAN PLC,50:AXN,RIO RINRO PLC,68,17.8@BGT,ANGLO AMERICAN PLC,23,98.7@CXZ,ANGLO AMERICAN PLC,11,551.7";
		String s2 = "RIO,RIO RINTO PLC,746:RIO,RIO RINRO PLC,688,13.9";
		String s3 = null;
		String s4 = "";
		String s5 = "RIO,RIO RINTO PLC,746@AAL,ANGLO AMERICAN PLC,271:RIO,RIO RINRO PLC,688,13.9@AAL,ANGLO AMERICAN PLC,293,49.7";
		String s6 = "RIO,RIO RINTO PLC,746@AAL,ANGLO AMERICAN PLC,271:RIO,RIO RINRO PLC,688,13.9@AAL,ANGLO AMERICAN PLC,293,49.7@MSFT,MICROSOFT INC,0,49.7";
		PortifolioBenchmark pb = new PortifolioBenchmark();
		
		System.out.println(pb.adjust(s));
		System.out.println(pb.adjust(s1));
		System.out.println(pb.adjust(s2));
		System.out.println(pb.adjust(s3));
		System.out.println(pb.adjust(s4));
		System.out.println(pb.adjust(s5));
		System.out.println(pb.adjust(s6));
	}
}
