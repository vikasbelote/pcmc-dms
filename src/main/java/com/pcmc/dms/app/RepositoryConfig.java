package com.pcmc.dms.app;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class RepositoryConfig {

	 //${jdbc.driverClassName}
    @Value("${jdbc.driverClassName}")     private String driverClassName;
    @Value("${jdbc.url}")                 private String url;
    @Value("${jdbc.username}")            private String username;
    @Value("${jdbc.password}")            private String password;
    
    @Value("${hibernate.dialect}")        private String hibernateDialect;
    @Value("${hibernate.show_sql}")       private String hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}")   private String hibernateHbm2ddlAuto;
    @Value("${hibernate.format_sql}")     private boolean hibernateFormatSql;
    @Value("${hibernate.jdbc.batch_size}")   private String hibernateBatchSize;
    @Value("${hibernate.cache.use_second_level_cache}") private String hibernateSecondLevelCacheUsed;
        
    @Bean()    
    public DataSource getDataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);        
        return ds;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }
    
    @Bean
    @Autowired
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory)
    {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }
        
    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    	sessionFactory.setDataSource(getDataSource());
    	sessionFactory.setHibernateProperties(getHibernateProperties());   
    	
    	String[] stringArr = new String[2];
    	stringArr[0] = "com.pcmc.dms.model";
    	stringArr[1] = "com.pcmc.dms.master";
    	
    	sessionFactory.setPackagesToScan(stringArr);
        return sessionFactory;
    }

    @Bean
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.jdbc.batch_size", hibernateBatchSize);
        properties.put("hibernate.cache.use_second_level_cache", hibernateSecondLevelCacheUsed);
        
        return properties;
    }
}
