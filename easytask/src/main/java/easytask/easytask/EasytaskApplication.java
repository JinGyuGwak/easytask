package easytask.easytask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.web.config.SpringDataWebConfiguration;

@SpringBootApplication()
public class EasytaskApplication {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setData("시작이 되었어요");

		SpringApplication.run(EasytaskApplication.class, args);
		System.out.println(hello.getData());
	}

}
