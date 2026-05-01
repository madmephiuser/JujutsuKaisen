
package com.mycompany.jujutsukaisen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mycompany.jujutsukaisen", "controller", "service", "report", "repository", "validate"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"com.mycompany.jujutsukaisen"})
public class JujutsuKaisenArchive {
    public static void main(String[] args) {
        SpringApplication.run(JujutsuKaisenArchive.class, args);
    }
}