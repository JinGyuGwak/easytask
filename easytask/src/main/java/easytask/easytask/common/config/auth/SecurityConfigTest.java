//package easytask.easytask.config.auth;
//
//import easytask.easytask.entity.enumClass.Role;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@RequiredArgsConstructor
//@EnableWebSecurity //Spring Security설정들을 활성화 시켜줌
//public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
//
//    //구글 로그인 이후 가져온 사용자의 정보들을 기반으로 가입 및 정보수정, 세션 저장등의 기능 지원하는 클래스 (개발자가 구현해야함)
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점
//
//                    //andMatchers : 권한 관리 대상을 지정하는 옵션, permitAll()을 통해 아래 URL은 전체 열럼 권한을 줌
//                    .antMatchers("/","/css/**","images/**","/js/**").permitAll()
//
//                    //아래 주소를 가진 API는 USER 권한을 가진 사람만 접근 가능
//                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//
//                    //anyRequest : 설정된 값들 이외 나머지 URL들을 나타냅니다.
//                    //authenticated : 인증된 사용자들에게만 접근 허용 (인증된 사용자 = 로그인한 사용자)
//                    .anyRequest().authenticated() // 즉, 설정된 값들 이외에는 로그인한 사용자들만 이용 가능
//
//                //로그아웃 성공시 /주소로 이동함
//                .and()
//                    .logout()
//                        .logoutSuccessUrl("/")
//
//                .and()
//                    //OAuth2 로그인 기능에 대한 여러 설정의 진입점
//                    .oauth2Login()
//
//                        //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
//                        .userInfoEndpoint()
//
//                            //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
//                            //구글에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.
//                            .userService(customOAuth2UserService);
//
//
//    }
//}
