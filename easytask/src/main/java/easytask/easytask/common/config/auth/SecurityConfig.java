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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()


                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //내가 만든 jwtAuthenticationEntryPoint 사용
                .accessDeniedHandler(jwtAccessDeniedHandler) //내가 만든 jwtAccessDeniedHandler 사용

                // enable h2-console 위한 설정
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .antMatchers("/sign-up/register","/").permitAll() //이 api는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll
                .anyRequest().authenticated() //이외 나머지 요청은 권한이 있어야함


                .and()
                .apply(new JwtSecurityConfig(tokenProvider));



        return httpSecurity.build();
    }
}
/*package easytask.easytask.common.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
//                .antMatchers("/hello").permitAll() //  localhost:8080/hello 에 대한 요청은 인증없이 접근을 허용하겠다는 의미
                .anyRequest().permitAll(); //그 이외 나머지 요청들은 모두 인증되어야 한다.
    }

}
*/