package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration      //설정파일에 대한 것
@EnableJpaAuditing  //jpa에 대한 감지를 활성화
public class JpaConfig {

}
