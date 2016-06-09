package com.kla.web.calculator.business.spi;

/**
 * Interface for calculation 
 * @author
 *
 */
public interface CalculatorBusiness {
	
	/**
	 * Get expression and evaluate the operation
	 * @param exppression - String
	 * @return - double
	 */
	public double evaluateExpression(String expression);
		

}
