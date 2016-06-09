package com.kla.web.calculator.services.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculatorServiceUtilTest {

	@Test
	public void testValidateCalculatorExpressionWithAddition() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("1+1"));
	}
	
	// Test Multiplication 
	@Test
	public void testValidateCalculatorExpressionWithMultiplication() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("1*1"));
	}
	
	// Test Division 
	@Test
	public void testValidateCalculatorExpressionWithDivision() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("1/1"));
	}
	
	// Test Subtraction 
	@Test
	public void testValidateCalculatorExpressionWithSubtraction() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("1-1"));
	}
	
	// Test Spaces 
	@Test
	public void testValidateCalculatorExpressionWithSpace() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("1 + 1"));
	}
	
	// Test decimal 
	@Test
	public void testValidateCalculatorExpressionWithDecimal() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("		1.0	 +1"));
	}
	
	// Test decimal with more than two digits 
	@Test
	public void testValidateCalculatorExpressionWithDecimalAbove2() {
		assertFalse(CalculatorServiceUtil.validateCalculatorExpression("		1.000	 +1"));
	}
	
	// Test Space and tabs 
	@Test
	public void testValidateCalculatorExpressionWithSpaceAndTabs() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("		1	 +1"));
	}
	
	// Test Positive and Negative numbers 
	@Test
	public void testValidateCalculatorExpressionWithPositivieAndNegative() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("		+1	 + -1"));
	}
	
	// Test multiple operators one after other 
	@Test
	public void testValidateCalculatorExpressionWithMultipleOperatorsOneAfter() {
		assertFalse(CalculatorServiceUtil.validateCalculatorExpression("		+1	 ++ -1"));
	}
	
	// Test decimal, positive/negative numbers 
	@Test
	public void testValidateCalculatorExpressionWithPositiveNegativeDecimal() {
		assertTrue(CalculatorServiceUtil.validateCalculatorExpression("   123.0 + 123. 		++ 123.	* 123 ++123.00 +- 123 / 100 -123	."));
	}

}
