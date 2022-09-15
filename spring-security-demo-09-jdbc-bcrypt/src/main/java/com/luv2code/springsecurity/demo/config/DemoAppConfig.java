package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration // eksplicitno receno da je klasa Configuration
@EnableWebMvc
@ComponentScan (basePackages = "com.luv2code.springsecurity.demo") // paketi i subpaketi koje ce Spring da uzme pod ComponentScan
@PropertySource("classpath:persistence-mysql.properties") // unosi se pun naziv naseg connection pool resource fajla -> src/main/resources/ persistence-mysql.properties
public class DemoAppConfig {
	
	// set up/inject a variable to hold the properties
	@Autowired
	private Environment env; // varijabla ce sacuvati podatke iz naseg connection pool propertiesa
	
	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	// define a bean for ViewResolver
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	
	// define a bean for our security datasource, definisemo connection pool i povezujemo preko Environmenta sa ComboDataSourceom, ostvarujemo konekciju sa DB schemom
	
	@Bean
	public DataSource securityDataSource() {
		
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver")); // env -> reference gde smo smestili podatke iz connection pool propertiesa
		} catch (PropertyVetoException exc) {

			throw new RuntimeException(exc);
		}
		
		// log the connection properties, just to be sure we are reading data from properties file!
		
		logger.info(">>> jdbc.url=" +env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user=" +env.getProperty("jdbc.user")); // ako je sve ok, trebalo bi da ispise >>> jdbc.user= springstudent, kao sto je definisano u properties fajlu
		
		// set database connection properties
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url")); // definisemo url DB
		securityDataSource.setUser(env.getProperty("jdbc.user")); // setujemo username (ime) DB konekcije, NE TABELE NEGO DB KONEKCIJE
		securityDataSource.setPassword(env.getProperty("jdbc.password")); // setujemo password DB konekcije
		
		// set connection pool props
		
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize")); // preko helper metode ispod ce parsirati u int i setovati vrednost
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize")); // preko helper metode ispod ce parsirati u int i setovati vrednost
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize")); // preko helper metode ispod ce parsirati u int i setovati vrednost
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime")); // preko helper metode ispod ce parsirati u int i setovati vrednost
		
		
		return securityDataSource;
	}
	
	// need a helper method
	// read environment propery and convert to int, jer se svi podaci prakticno dobijaju kao String
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
	
	
	

}
