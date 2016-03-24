package com.premierinc.persistable;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.IdDt;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

/**
 *
 */
//@ResourceDef(name="PatientPersistable", profile="http://hl7.org/fhir/profiles/Patient", id="patient")
@ResourceDef(name = "Patient", profile = "http://hl7.org/fhir/profiles/Patient", id = "patient")
public class PatientPersistable implements Persistable<IdDt> {

	@Id
	private Long id;

	private Patient patient = new Patient();

	@Override
	public IdDt getId() {
		IdentifierDt identifierDt = null;
		final List<IdentifierDt> identifiers = patient.getIdentifier();
		if (0 < identifiers.size()) {
			identifierDt = identifiers.get(0);
		}
		if (null != identifierDt) {
			String elementSpecificId = identifierDt.getElementSpecificId();
			if (null != elementSpecificId) {
				return new IdDt(elementSpecificId);
			}
		}
		return null;
	}

	@Override
	public boolean isNew() {
		return null == this.patient.getIdentifier() || 0 == this.patient.getIdentifier().size();
	}

	public PatientPersistable addName(final String inName) {
		this.patient.addName(new HumanNameDt().addFamily(inName));
		return this;
	}

	public PatientPersistable addIdentifier(final IdentifierDt inIdentifierDt) {
		this.patient.addIdentifier(inIdentifierDt);
		return this;
	}

	public Patient getPatient(){
		return this.patient;
	}


	//@Override
	public String toString() {
		String s = super.toString();
		return null != s ? s : "*NULL*";
	}
}
