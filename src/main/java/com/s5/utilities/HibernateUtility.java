package com.s5.utilities;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.s5.model.DatabaseData;


@Service
public class HibernateUtility {

	private static Logger logger = LoggerFactory.getLogger(HibernateUtility.class);

	public static SessionFactory buildSessionFactory(DatabaseData dbData) {
		SessionFactory sessionFactory = null;
		try {

			if (dbData != null) {
				// logger.info("dbData not null");
				// logger.info("dbData driver : "+dbData.getDataSourceDriver());
				// logger.info("dbData IP : "+dbData.getDatabaseIP());
				logger.info("dbData  DBNAme : " + dbData.getDatabaseSchema());
				// logger.info("dbData Uname "+dbData.getDatabaseUserName() +"
				// pwd :
				// "+dbData.getDatabaseUserName());

				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName(dbData.getDataSourceDriver());
				dataSource.setUrl("jdbc:mysql://" + dbData.getDatabaseIP() + ":" + dbData.getDatabasePort() + "/" + dbData.getDatabaseSchema()
						+ "?autoReconnect=true&useSSL=false");
				dataSource.setUsername(dbData.getDatabaseUserName());
				dataSource.setPassword(dbData.getDatabasePassword());

				LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
				sessionFactoryBean.setDataSource(dataSource);

				Properties hibernateProperties = new Properties();
				// hibernateProperties.put("hibernate.dialect",
				// HIBERNATE_DIALECT);
				hibernateProperties.put("hibernate.show_sql", false);
				//hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
				sessionFactoryBean.setHibernateProperties(hibernateProperties);
				sessionFactoryBean.setPackagesToScan("com.ct.docverification.entity");
				sessionFactoryBean.afterPropertiesSet();
				return sessionFactoryBean.getObject();
			}
		} catch (Exception ex) {
			logger.error("Initial SessionFactory creation failed.", ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
		return sessionFactory;
	}
	
	@PostConstruct
	public void init() {
		
		System.out.println("hibernate Utitlity init called...");
		
		DatabaseData dd = new DatabaseData();
		dd.setDatabaseIP("192.168.201.53");
		dd.setDatabaseName("MYSQL");
		dd.setDatabasePassword("Karan@123");
		dd.setDatabasePort("3306");
		dd.setDatabaseSchema("CT_docverification");
		dd.setDatabaseUserName("karan");
		dd.setDataSourceDriver("com.mysql.jdbc.Driver");
		dd.setTimeout(300000);

		buildSessionFactory(dd);
	}
}