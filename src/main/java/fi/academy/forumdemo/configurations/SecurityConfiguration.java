package fi.academy.forumdemo.configurations;

import fi.academy.forumdemo.repositories.UserRepository;
import fi.academy.forumdemo.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository ur;

    @Autowired
    private UserRoleRepository urr;

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select username, password, active from user where username=?")
                .authoritiesByUsernameQuery("select username, rooli_role from user where username=?")
                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
//                formLogin().loginPage("/login").permitAll()
//                .and().logout().permitAll()
//                .and().httpBasic();
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/alueet").permitAll()
                .antMatchers("/alue*").permitAll()
                .antMatchers("/uusilanka*").permitAll()
                .antMatchers("/viestiketjut").permitAll()
                .antMatchers("/kasitteleKayttaja").permitAll()
                .antMatchers("/viestiketjut").permitAll()
                .antMatchers("/luoUusiVastaus").permitAll()
                .antMatchers("/etusivu").permitAll()
                .antMatchers("/haku").permitAll()
                .antMatchers("/alue*").permitAll()
                .antMatchers("/naytaViestiketju*").permitAll()
                .antMatchers("/vastaa*").permitAll()
                .antMatchers("/kirjoitaVastaus").permitAll()
                .antMatchers("/langat*").permitAll()
                .antMatchers("/nav").permitAll()
                .antMatchers("/uusilanka").permitAll()
                .antMatchers("/viestiketjut?").permitAll()
                //.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .antMatchers("/admin").hasAuthority("admin").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/etusivu")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
