
package com.mycompany.jujutsukaisen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mycompany.jujutsukaisen", "controller", "service", "repository"})
@EnableJpaRepositories(basePackages = "repository")
public class JujutsuKaisenArchive {
    public static void main(String[] args) {
        SpringApplication.run(JujutsuKaisenArchive.class, args);
    }
}
