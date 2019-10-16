package cn.itcast;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@MapperScan("cn.itcast.mapper")  mybatis的扫描器
//@MapperScan("cn.itcast.mapper")  //使用的是统一mapper的注解 tk包下的
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisApplication.class, args);
	}

}
