package easytask.easytask.common.config.auth;
import easytask.easytask.common.jwt.JwtAccessDeniedHandler;
import easytask.easytask.common.jwt.JwtAuthenticationEntryPoint;
import easytask.easytask.common.jwt.JwtSecurityConfig;
import easytask.easytask.common.jwt.TokenProvider;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web){ //mariadb 관련 요청은 스프링 시큐리티 로직을 수행하지 않도록 설정
        web.ignoring().antMatchers("/css/**", "/js/**",
                "/img/**", "/lib/**",
                "/sign-up/**","/work-request");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //HttpServletRequest를 사용하는 요청들에 대한 접근체한을 설정
                .antMatchers("/","/hello","/sign-up/register").permitAll() //  여기서 설정한 url에 대한 요청은 인증없이 접근을 허용하겠다는 의미
                .anyRequest().authenticated(); //그 이외 나머지 요청들은 모두 인증되어야 한다.
    }

}