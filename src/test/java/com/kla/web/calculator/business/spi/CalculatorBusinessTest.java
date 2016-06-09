package com.kla.web.calculator.business.spi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kla.web.calculator.business.impl.CalculatorBusinessImpl;



public class CalculatorBusinessTest {

	@Test
	public void testEvaluateExpression() {
		CalculatorBusiness calculatorBusiness  = new CalculatorBusinessImpl();
		String expression = "   123.0 + 123. 		++ 123.	* 123 ++123.00 +- 123 / 100 -123	.";
		expression = expression.replaceAll("\\s", "");
		double actual = calculatorBusiness.evaluateExpression(expression);
		assertEquals("Expected Value is 15374.0", 15374.0, actual, 4);
	}

}
