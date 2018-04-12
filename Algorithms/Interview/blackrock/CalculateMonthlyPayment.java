package blackrock;

import java.util.stream.Stream;

/** @author: Hongquan Yu
 *  @date: Mar 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CalculateMonthlyPayment {
	public double monthlyPayment (String s) {
		double loanAmount, yearlyInterest, downPayment;
		int time;
		double monthlyPayment = 0.0;
	    int totalInterest = 0;
		
		String[] paras = s.split("~");
		double [] dbParas = Stream.of(paras).mapToDouble(Double::parseDouble).toArray();
		
		if (dbParas.length != 4)
			throw new IllegalArgumentException();
		
		loanAmount = dbParas[0];
		yearlyInterest = dbParas[2];
		time = (int)dbParas[1];
		downPayment = dbParas[3];
		int periods = time * 12;
		
		loanAmount -= downPayment;
		
		double monthInterest = yearlyInterest / (100 * 12);
		
		monthlyPayment =  monthInterest * loanAmount / (1 - Math.pow((1 + monthInterest), -periods));
		
//		double roundPayment = Math.round(monthlyPayment * 100.0) / 100.0;
//		System.out.println(String.format("%.2f", monthlyPayment));
//		System.out.println("New: " + String.format("%.2f", roundPayment));
		double interest = monthlyPayment * periods - (loanAmount + downPayment);
	    totalInterest = (int)Math.rint(interest);
	    
	    String output = "$" + String.format("%.2f", monthlyPayment) + "~$" + totalInterest;
		System.out.println(output);
		
		return monthlyPayment;
	}
	
	public static void main(String[] args) {
		String s = "6000~6~5~0";			// $116.00~$960
		String s2 = "30000~10~6~5000";	// $277.55~$8306
		String s3 = "25000~10~6~0";		// $277.55~$8306
		String s4 = "5000~5~6~0";		// $96.66~$800
		
		CalculateMonthlyPayment c = new CalculateMonthlyPayment();
		c.monthlyPayment(s);
		c.monthlyPayment(s2);
		c.monthlyPayment(s3);
		c.monthlyPayment(s4);
	}
}
