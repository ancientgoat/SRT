package com.premierinc.server;

import ca.uhn.fhir.rest.server.HardcodedServerAddressStrategy;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import com.google.common.collect.Lists;
import com.premierinc.resourceprovider.DumbRestfulOrganizationProvider;
import com.premierinc.resourceprovider.DumbRestfulPatientResourceProvider;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * In this example, we are using Servlet 3.0 annotations to define
 * the URL pattern for this servlet, but we could also
 * define this in a web.xml file.
 */
//@WebServlet(urlPatterns= {"/fhir/*"}, displayName="FHIR Server")
@WebServlet(urlPatterns = {"/dumb/*"}, displayName = "FHIR Server")
public class DumbFHIRRestfulServer extends RestfulServer {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public DumbFHIRRestfulServer() {

		//String serverBaseUrl = "http://foo.com/fhir";
		String serverBaseUrl = "http://206.71.92.71:8080/dumb";
		setServerAddressStrategy(new HardcodedServerAddressStrategy(serverBaseUrl));

		// ...add some resource providers, etc...
		setResourceProviders(getDumbResourceProviders());
	}

	/**
	 *
	 */
	private List<IResourceProvider> getDumbResourceProviders() {
		List<IResourceProvider> resourceProviders = Lists.newArrayList();
		resourceProviders.add(new DumbRestfulPatientResourceProvider());
		resourceProviders.add(new DumbRestfulOrganizationProvider());
		return resourceProviders;
	}
}