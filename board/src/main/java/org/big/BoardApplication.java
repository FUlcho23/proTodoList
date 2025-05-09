package org.big;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
public class BoardApplication {

   public static void main(String[] args) {
      SpringApplication.run(BoardApplication.class, args);
   }

}