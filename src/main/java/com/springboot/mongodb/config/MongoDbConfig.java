package com.springboot.mongodb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.springboot.mongodb.repository.PortfolioRepository;

@EnableMongoRepositories(basePackageClasses = PortfolioRepository.class)
@Configuration
public class MongoDbConfig {

	@Bean
	CommandLineRunner commandLineRunner(PortfolioRepository portfolioRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				/*
				 * portfolioRepository.save(new Portfolio(new User(1, "namit",
				 * "jain", "namit.jain88@gmail.com"), Arrays.asList(new
				 * Scheme(123456L,
				 * "SBI Magnum Income Plus Fund - Savings Plan (G)"), new
				 * Scheme(123457L,
				 * "SBI MAGNUM NRI - SHORT TERM BOND PLAN-GROWTH"))));
				 * 
				 * portfolioRepository.save(new Portfolio(new User(2,
				 * "priyanka", "jain", "priyanka.jain@gmail.com"),
				 * Arrays.asList(new Scheme(123456L,
				 * "SBI Magnum Income Plus Fund - Savings Plan (G)"), new
				 * Scheme(123457L,
				 * "SBI MAGNUM NRI - SHORT TERM BOND PLAN-GROWTH"))));
				 */
				/*
				 * schemeRepository.save(new Scheme(103187L,
				 * "SBI Magnum Income Plus Fund - Savings Plan (G)"));
				 * schemeRepository.save(new Scheme(102201L,
				 * "SBI MAGNUM NRI - SHORT TERM BOND PLAN-GROWTH"));
				 */
			}
		};
	}
}
