package com.premierinc.persistable;

import org.springframework.data.domain.Persistable;
// import ca.uhn.fhir.model.dstu2.resource.Patient;


/**
 *
 */
public class PatientPersistable implements Persistable<Long> {

    private Patient patient;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
