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

	private AnnotationConfigWebApplicationContext myAppCtx;

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

//	/**
//	 * The initialize method is automatically called when the servlet is starting up, so it can
//	 * be used to configure the servlet to define resource providers, or set up
//	 * configuration, interceptors, etc.
//	 */
//	@Override
//	protected void initialize() throws ServletException {
//	  /*
//       * The servlet defines any number of resource providers, and
//       * configures itself to use them by calling
//       * setResourceProviders()
//       */
//		super.initialize();
//
////		// /////////////////////////////////////////////////////////////////////////////
////		// Get the spring context from the web container (it's declared in web.xml)
////
////		WebApplicationContext parentAppCtx = ContextLoaderListener.getCurrentWebApplicationContext();
////		myAppCtx = new AnnotationConfigWebApplicationContext();
////		myAppCtx.setServletConfig(getServletConfig());
////
////		myAppCtx.refresh();
////
////		setFhirContext(FhirContext.forDstu2());
////
////		// /////////////////////////////////////////////////////////////////////////////
////
////		setResourceProviders(getDumbResourceProviders());
////
////		/*
////		 * Load interceptors for the server from Spring
////		 */
////		Collection<IServerInterceptor>
////				interceptorBeans = myAppCtx.getBeansOfType(IServerInterceptor.class).values();
////		for (IServerInterceptor interceptor : interceptorBeans) {
////			this.registerInterceptor(interceptor);
////		}
//
//
//	}

	@Override
	public void destroy() {
		super.destroy();
		//ourLog.info("Server is shutting down");
		myAppCtx.destroy();
	}
	/**
	 *
	 */
	private List<IResourceProvider> getDumbResourceProviders() {
		List<IResourceProvider> resourceProviders = Lists.newArrayList();
		resourceProviders.add(new DumbRestfulPatientResourceProvider());
		resourceProviders.add(new DumbRestfulOrganizationProvider());
		//resourceProviders.add(new RestfulObservationResourceProvider());
		return resourceProviders;
	}
}