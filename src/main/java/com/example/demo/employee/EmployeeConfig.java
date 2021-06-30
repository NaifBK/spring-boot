package com.example.demo.employee;

import com.sun.tools.javac.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee naif = new Employee(
                    "Naif",
                    "Naif.Binkhathlan.Optiim@gmail.com",
                    LocalDate.of(2001, Month.OCTOBER, 22)
            );

            Employee murat = new Employee(
                    "Murat",
                    "Murat.Yildiz@Optiim.com",
                    LocalDate.of(1995, Month.JANUARY, 1)
            );

            repository.saveAll(
                    List.of(naif, murat)
            );
        };
    }
}
