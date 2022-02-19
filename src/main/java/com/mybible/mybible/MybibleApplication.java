package com.mybible.mybible;

import com.mybible.mybible.model.Transaction;
import com.mybible.mybible.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class MybibleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybibleApplication.class, args);
	}

}
