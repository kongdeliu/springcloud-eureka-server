package com.deepexi.admin;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @description: 权限控制配置
 * @program: springboot-admin-server
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/4/1 下午4:21
 **/
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        // Grants public access to all static assets and the login page.
        // Every other request must be authenticated.
        // Configures login and logout.
        // Enables HTTP-Basic support. This is needed for the Spring Boot Admin Client to register.
        // Enables CSRF-Protection using Cookies
        // Disables CRSF-Protection the endpoint the Spring Boot Admin Client uses to register.
        // Disables CRSF-Protection for the actuator endpoints.
        http.authorizeRequests().antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and().httpBasic().and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(adminContextPath + "/instances",
                        adminContextPath + "/actuator/**"
                );
    }
}
