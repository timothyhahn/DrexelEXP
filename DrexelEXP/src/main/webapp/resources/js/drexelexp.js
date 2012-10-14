 $(document).ready(function() {
 });
 
 function doThings() {
	if ($("#login").is(":hidden")) {
		$("#login").show("slide", { direction: "right" }, 5000);
	} else {
		$("#login").hide("slide", { direction: "left" }, 5000);
	}

 }