package server;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PI {

	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal FOUR = new BigDecimal("4");
	private static final BigDecimal FIVE = new BigDecimal("5");
	private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");

	/* (non-Javadoc)
	 * @see at.tm.rmi.server.Calculator#pi(int)
	 * 
	 * Takes the amount of decimal places as an input and returns the desired amount of decimal places of PI.
	 */
	public BigDecimal pi(int numDigits) {
		if (numDigits < 0)	{
			throw new IllegalArgumentException("Enter a value bigger than -1");
		}
		int calcDigits = numDigits + 10;
		return FOUR.multiply((FOUR.multiply(arccot(FIVE, calcDigits))).subtract(arccot(TWO_THIRTY_NINE, calcDigits))).setScale(numDigits, RoundingMode.DOWN);
	}

	/**
	 * @param x a BigInteger number
	 * @param numDigits the amount of decimal places of PI that will be calculated
	 * @return sum of some complex arithmetic operation
	 */
	private static BigDecimal arccot(BigDecimal x, int numDigits) {

		BigDecimal unity = BigDecimal.ONE.setScale(numDigits, RoundingMode.DOWN);
		BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
		BigDecimal xpower = new BigDecimal(sum.toString());
		BigDecimal term = null;

		boolean add = false;

		for (BigDecimal n = new BigDecimal("3"); term == null || term.compareTo(BigDecimal.ZERO) != 0; n = n.add(TWO)) {
			xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
			term = xpower.divide(n, RoundingMode.DOWN);
			sum = add ? sum.add(term) : sum.subtract(term);
			add = ! add;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.print(new PI().pi(1000));
	}
}
