package com.example.quiz_1150720.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig {
	// 使用 Argon2PasswordEncoder 來進行密碼的高安全性雜湊與比對
   @Bean
   public PasswordEncoder passwordEncoder() {
       // 參數分別代表: saltLength, hashLength, parallelism, memory, iterations
       return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
   }
}
