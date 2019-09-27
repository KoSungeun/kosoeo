function toggleVaild(selector, msg, checkOk) {
	if(checkOk) {
		$(selector + " > input").addClass("is-valid");
    	$(selector + " > div").addClass("valid-feedback");
    	$(selector + " > input").removeClass("is-invalid");
    	$(selector + " > div").removeClass("invalid-feedback");	
	} else {
		$(selector + " > input").addClass("is-invalid");
    	$(selector + " > div").addClass("invalid-feedback");
    	$(selector + " > input").removeClass("is-valid");
    	$(selector + " > div").removeClass("valid-feedback");		
	}
	$(selector + " > div").text(msg);
}