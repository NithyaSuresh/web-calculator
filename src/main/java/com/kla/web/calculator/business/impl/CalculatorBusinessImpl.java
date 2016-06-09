package com.kla.web.calculator.business.impl;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.kla.web.calculator.business.spi.CalculatorBusiness;
/**
 * Implementation for calculator expression
 * @author
 *
 */
public class CalculatorBusinessImpl implements CalculatorBusiness {

	@Override
	public double evaluateExpression(String expression) {
		
		// parsing the expression using Spring Expression Parser to get the value
		ExpressionParser parser = new SpelExpressionParser();
		double value = parser.parseExpression(expression).getValue(Double.class); 
		
		return value;
	}

}
