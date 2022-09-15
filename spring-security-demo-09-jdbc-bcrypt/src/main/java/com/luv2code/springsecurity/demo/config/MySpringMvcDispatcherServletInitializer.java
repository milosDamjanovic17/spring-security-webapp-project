package com.luv2code.springsecurity.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override // DEFINISE INSTANCU KLASE IZ KOJE CE JAVA COMPILER DA CITA PREFIXE I SUFFIXE STRANE I IZ KOG PAKETA JE MAPIRAN @ComponentScanning
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { DemoAppConfig.class };
	}

	@Override // DEFINISE ZAPRAVO PRVU INDEX STRANU
	protected String[] getServletMappings() {


		return new String[] { "/" };
	}

}
