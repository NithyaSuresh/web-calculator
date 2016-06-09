package com.kla.web.calculator.services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kla.web.calculator.business.spi.CalculatorBusiness;
import com.kla.web.calculator.services.util.CalculatorServiceUtil;


/**
 * Creates webservice for web calculator
 * @author 
 *
 */
@Component
@Path("/calculator")
public class CalculatorService {

	//Logger Variable
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);
		

	@Autowired
	private CalculatorBusiness calculatorBusiness;
	
	/**
	 * Accepts the input request for the calculator and evaluate the expression and provide the output
	 * @param expression String
	 * @return Response
	 * @throws JSONException
	 */
	@POST
	@Path("/{expression}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response evalExpression(@PathParam("expression") String expression) throws JSONException {
		
		Response response;
		try{
			// Removing white spaces
			expression = expression.replaceAll("\\s", "");
			expression = expression.replaceAll("@", "/");
			
			// Validating the input expression using regex
			if(!CalculatorServiceUtil.validateCalculatorExpression(expression)){
				// constructing the JSON object for error
				LOGGER.error("Invalid Format Exception in /calculator/{expression} service, for input {} {}", expression);
				
				// constructing the JSON error object
				JSONObject errorJSON = CalculatorServiceUtil.constructErrorJSON("406 - /calculator/{expression}", 
						"Invalid Format expression created. Please check and correct the input.",
						"Not Valid Regex expression for the calculator",
						expression);
				
				// constructing the error response with http status code as 406 i.e. Not Acceptable because of invalid expression passed
				response = CalculatorServiceUtil.constructResponse(406, errorJSON);
			}
			
			// Invoking the business layer to evaluate the expression
			double value = calculatorBusiness.evaluateExpression(expression);
			
			// constructing the JSON output object
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("input", expression);
			jsonObject.put("output", value); 
			
			// constructing the response with http status code as 200 i.e. success (OK)
			response = CalculatorServiceUtil.constructResponse(200, jsonObject);
		} catch (Exception e) {
			LOGGER.error("Unexpected Exception in /calculator/{expression} service, for input {} {}", expression, e);
			
			// constructing the JSON error object
			JSONObject errorJSON = CalculatorServiceUtil.constructErrorJSON("403 - /calculator/{expression}", 
					"Unexpected error occurred when processing the given expression. Please contact IT Support.",
					e.getMessage(),
					expression);
			
			// constructing the error response with http status code as 403 i.e. forbidden because of unexpected error
			response = CalculatorServiceUtil.constructResponse(403, errorJSON);
		}
		
		return response;
	}
}
