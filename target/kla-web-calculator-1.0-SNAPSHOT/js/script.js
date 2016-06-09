function calculate($scope, $http) {

	var testNumLength = function(number) {
		if (number.length > 9) {
			totaldiv.text(number.substr(number.length - 9, 9));
			if (number.length > 15) {
				number = "";
				totaldiv.text("Err");
			}
		}
	};


	var number = "";
	var newnumber = "";
	var operator = "";
	var divioperator = "";
	var totaldiv = $("#total");
	var expdiv = $("#expression");
	var inputdiv = $("#input");
	var exp = "";
	totaldiv.text("0");
	$("#numbers a").not("#clear,#clearall").click(function() {
		number += $(this).text();
		totaldiv.text(number);
		testNumLength(number);
		expdiv.text(exp + number);
		divioperator = exp;		
		inputdiv.text(divioperator.replace("@","/") + number);
	});

	$("#operators a").not("#equals").click(function() {
		operator = $(this).text();
		if(operator === "/"){
			operator = "@";
		}
		newnumber = number;
		number = "";
		totaldiv.text("0");
		exp = expdiv.text() + "" + operator;
		expdiv.text(exp);
		divioperator = exp;		
		inputdiv.text(divioperator.replace("@","/"));
	});
	$("#clear,#clearall").click(function() {
		number = "";
		totaldiv.text("0");
		expdiv.text("");
		inputdiv.text("");
		exp = "";
		if ($(this).attr("id") === "clearall") {
			newnumber = "";
		}
	});
	$("#equals").click(function() {
		var webserviceUrl = "rest/calculator/" + expdiv.text();
		/*webservice call starts here*/
		$http.post(webserviceUrl).success(function(data) {
			$scope.calc = data;
		}).error(function(data) {
			$scope.calc = data;
		});
		/*webservice call ends here*/

		totaldiv.text(number);
		testNumLength(number);
		number = "";
		newnumber = "";
		expdiv.text("");
		inputdiv.text("");
		exp = "";
	});
}
