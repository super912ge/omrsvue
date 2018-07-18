package com.proship.omrs;

import com.proship.omrs.base.repository.CustomizedRepositoryImpl;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = CustomizedRepositoryImpl.class)
public class OmrsApplication {

	@Bean
	public ConfigurableServletWebServerFactory containerCustomizer() {

        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
			return factory;
	}

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		return template;
//	}
//
//	@Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//		jedisConFactory.setHostName("localhost");
//		jedisConFactory.setPort(6379);
//		return jedisConFactory;
//	}


	@Bean(name = "contractEventCase")
	public static PropertiesFactoryBean mapper() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource(
				"contractEventCase.properties"));

		return bean;
	}
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }
//
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                dispatcherServlet(), "/api/*");
//        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
//        return registration;
//    }

//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactory() {
//		return new HibernateJpaSessionFactoryBean();
//	}

	public static void main(String[] args) {
		SpringApplication.run(
				new Class[]{OmrsApplication.class},
				args);

	}
	
	
}
