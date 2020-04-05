package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class SqliDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqliDemoApplication.class, args);
    }

    @RequiredArgsConstructor
    @RestController
    @RequestMapping("/")
    private static class Controller {

        private final JdbcTemplate jdbcTemplate;

        @GetMapping("/users")
        public List<User> getUsers(@RequestParam String id) {
            RowMapper<User> userRowMapper = (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"));

            return jdbcTemplate.query("SELECT * from users where id = '" + id + "'" , userRowMapper);
        }
    }

    @Data
    @AllArgsConstructor
    private static class User {
        private int id;
        private String name;
    }

}
