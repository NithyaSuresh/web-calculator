package com.kla.web.calculator.services.util;

import javax.ws.rs.core.Response;

import org.json.JSONObject;

/**
 * Utility class to handle the util method for the calculator service
 * @author
 *
 */
public final class CalculatorServiceUtil {
	
	/**
	 * Validate the passed expression again the defined calculated regex.
	 * @param expression String
	 * @return boolean
	 */
	public static boolean validateCalculatorExpression(String expression){
		
		boolean result = false;
		/*
		 * Regular Expression checks for the following details
		 * 		Number
		 * 			Can start with (+-) as optional (i.e. 0 or 1 occurrence)
		 * 			Followed by any numbers  
		 * 			Numbers followed by decimal point (.) as optional (i.e. 0 or 1 occurrence)
		 * 			Decimal point followed by any numbers maximum of 2 digits
		 * 		Operator
		 * 			Allows (plus, minus, multiply, division) with max occurrence of 1
		 * 		Number
		 * 			Can start with (+-) as optional (i.e. 0 or 1 occurrence)
		 * 			Followed by any numbers  
		 * 			Numbers followed by decimal point (.) as optional (i.e. 0 or 1 occurrence)
		 * 			Decimal point followed by any numbers maximum of 2 digits	
		 * 		Repeated occurrence of the above steps 		
		 */
		final String regexCalculatorExpression = "^[-+]?[0-9]+[\\.]?([0-9]?){2}+(([-+*/]){1}+[-+]?[0-9]+[\\.]?([0-9]?){2}+)*$";
		expression = expression.replaceAll("\\s", "");
		if (expression.matches(regexCalculatorExpression)) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Construct the error json object for the given error
	 * @param errorCode String
	 * @param errorDesc String
	 * @param exceptionMessage String
	 * @return
	 */
	public static JSONObject constructErrorJSON(String errorCode, String errorDesc, String exceptionMessage, String inputExpression) {
		// constructing the JSON error object
		JSONObject errorJSONObject = new JSONObject();
		errorJSONObject.put("ErrorCode", errorCode);
		errorJSONObject.put("ErrorDescription", errorDesc);
		errorJSONObject.put("ExceptionMessage", exceptionMessage);
		errorJSONObject.put("input", inputExpression);
		return errorJSONObject;
	}
	
	/**
	 * Construct the json response for the below given parameters 
	 * @param httpStatusCode int
	 * @param jsonObject JSONObject
	 * @return Response
	 */
	public static Response constructResponse(int httpStatusCode, JSONObject jsonObject) {
		return Response.status(httpStatusCode).entity(jsonObject.toString()).build();
	}


}
