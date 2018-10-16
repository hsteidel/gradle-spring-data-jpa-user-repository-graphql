package com.hxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *   Sample CRUD User Application Using GraphQL
 *
 *   Available Profiles:
 *    - rdbms: Uses Database (Currently PostgreSQL)
 *
 */
@SpringBootApplication
public class UserCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCrudApplication.class, args);
    }

}