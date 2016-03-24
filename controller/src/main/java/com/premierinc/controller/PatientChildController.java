package com.premierinc.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.StringDt;
import com.google.common.collect.Lists;
import com.premierinc.persistable.PatientPersistable;
import com.premierinc.util.ExceptionHelper;
import com.premierinc.util.JsonHelper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/patient")
public class PatientChildController {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String getPatient() {

		FhirContext ctx = FhirContext.forDstu2();
		PatientPersistable patientPersistable = new PatientPersistable();
		//Patient patientPersistable = new Patient();

		// Set Some Name - wow what a lot of work
		patientPersistable.addName("Pete");
		patientPersistable.addIdentifier(new IdentifierDt("theSystem", "theValue"));

		try {
			String jsonString = ctx.newJsonParser().encodeResourceToString(
					patientPersistable.getPatient());
			System.out.println(jsonString);
			return jsonString;
		} catch (Exception e) {
			System.out.println(ExceptionHelper.toString(e));
			throw new IllegalArgumentException(e);
			//return "{ 'err' : 'ouch' }";
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public PatientPersistable savePatient(final @RequestBody PatientPersistable inPatient) {
		return inPatient;
	}
}
