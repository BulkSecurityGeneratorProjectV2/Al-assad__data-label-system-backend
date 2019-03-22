package site.assad.datalabel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("site.assad.datalabel.mapper")
public class DataLabelApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataLabelApplication.class, args);
    }

}
