package com.gmail.liliyayalovchenko.Configurations;

import com.gmail.liliyayalovchenko.DAO.*;
import com.gmail.liliyayalovchenko.DAOImplementation.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.PropertyVetoException;
import java.util.Properties;


@Configuration
@ComponentScan("com.gmail.liliyayalovchenko")
@EnableAspectJAutoProxy
@EnableWebMvc
@PropertySource({"classpath:jdbc.properties"})
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class AppConfig extends WebMvcConfigurerAdapter {

    private Environment environment;

        public AppConfig(Environment environment) {
            this.environment = environment;
    }

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BeautyJPA");
        return emf.createEntityManager();
    }

    @Bean
    public AdministratorDAO administratorDAO() {
        return new AdministratorDAOImpl();
    }

    @Bean
    public CategoryDAO categoryDAO() {
        return new CategoryDAOImpl();
    }

    @Bean
    public ClientDAO clientDAO() {
        return new ClientDAOImpl();
    }

    @Bean
    public FeedBackDAO feedBackDAO() {
        return new FeedBackDAOImpl();
    }

    @Bean
    public PostDAO informationDAO() {
        return new PostDAOImpl();
    }

    @Bean
    public OrderDAO orderDAO() {
        return new OrderDAOImpl();
    }

    @Bean
    public ProductDAO productDAO() {
        return new ProductDAOImpl();
    }

    @Bean
    public ProductInCartDAO productInCartDAO() {
        return new ProductInCartDAOImpl();
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }


    @Bean
    AppLogging aspectLogging() {
    return new AppLogging();
}

    @Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getRequiredProperty("jdbc.driver.class"));
            dataSource.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
            dataSource.setUser(environment.getRequiredProperty("jdbc.user"));
            dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
            dataSource.setMinPoolSize(1);
            dataSource.setMaxPoolSize(10);
            dataSource.setAcquireIncrement(1);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager tx() {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory().getObject());
        return tx;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packages.to.scan"));
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format.sql"));
        properties.setProperty("hibernate.use_sql_comments", environment.getProperty("hibernate.use.sql.comments"));
        properties.setProperty("hibernate.connection.characterEncoding", environment.getProperty("hibernate.connection.characterEncoding"));
        properties.setProperty("hibernate.connection.useUnicode", environment.getProperty("hibernate.connection.useUnicode"));
        properties.setProperty("hibernate.connection.charSet", environment.getProperty("hibernate.connection.charSet"));
        properties.setProperty("hibernate.connection.validationInterval", environment.getProperty("hibernate.connection.validationInterval"));
        properties.setProperty("hibernate.connection.validationQuery", environment.getProperty("hibernate.connection.validationQuery"));
        properties.setProperty("hibernate.connection.testOnBorrow", environment.getProperty("hibernate.connection.testOnBorrow"));
        properties.setProperty("hibernate.connection.wait_timeout", environment.getProperty("hibernate.connection.wait_timeout"));
        properties.setProperty("hibernate.connection.interactive_timeout", environment.getProperty("hibernate.connection.interactive_timeout"));
        return properties;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    }

