package com.tulingxueyuan.mall;

import cn.hutool.crypto.digest.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        //System.out.println(BCrypt.hashpw("123456"));
    }

}
