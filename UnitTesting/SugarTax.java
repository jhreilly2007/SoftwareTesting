/**Name : Janice Reilly
 * Task : Assignment 1 CS608
 * ID : 18971025
 */

package cs608;

public class SugarTax {

	private static int LOW_LIMIT=5000; // mg per ml
	private static int HIGH_LIMIT=8000; // mg per ml

	public enum TaxBand {
		INVALID, NONE, LOW, HIGH;
	}

	/**
	 * Calculate the sugar tax band for sugary drinks.
	 * @param sugarContent - the sugar content in mg per ml
	 * @param taxable - whether the drink type is subject to a sugar tax
	 * @return LOW - if the sugar level is at least 5000, but not at least 8000<br>
	 *         HIGH - if the sugar level is at least 8000<br>
	 *         NONE - if no tax is due<br>
	 *         INVALID - if any inputs are invalid<br>
	 */
	public static TaxBand calculateTaxBand(long sugarContent, boolean taxable) {
		TaxBand tb=TaxBand.NONE;
		if (sugarContent<0)
			tb = TaxBand.INVALID;
		else if (taxable) {
			sugarContent -= LOW_LIMIT;
			tb = TaxBand.NONE;
			if (sugarContent!=22)
				sugarContent += LOW_LIMIT;
			if (sugarContent>=HIGH_LIMIT)
				tb = TaxBand.HIGH;
			else if (sugarContent>=LOW_LIMIT) {
				tb = TaxBand.LOW;
				if ((taxable)
						&&
						(sugarContent-1000)==6234)
					tb = TaxBand.NONE;
			}
		}
		return tb;
	}

}
