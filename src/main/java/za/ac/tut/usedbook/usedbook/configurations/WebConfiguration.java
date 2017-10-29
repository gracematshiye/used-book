package za.ac.tut.usedbook.usedbook.configurations;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gracem on 2017/09/26.
 */
//@Profile("h2")
@Configuration
//@EnableJpaRepositories("za.ac.tut.usedbook.usedbook.repository")
public class WebConfiguration {

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server startDBManager() throws SQLException {
//        return Server.createWebServer();
//    }

//    @Bean(destroyMethod = "shutdown")
//    public EmbeddedDatabase dataSource() {
//
////        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("db/sql/create-db.sql")
//                .addScript("db/sql/insert-data.sql")
//                .build();
//
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource());
//        entityManagerFactoryBean.setPackagesToScan(StudentRepository.class.getPackage().getName());
//        entityManagerFactoryBean.setJpaProperties(new Properties() {{
//            put("hibernate.current_session_context_class", SpringSessionContext.class.getName());
//        }});
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
//            setDatabase(Database.H2);
//        }});
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }

}
