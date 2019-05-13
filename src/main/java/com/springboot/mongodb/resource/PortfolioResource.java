package com.springboot.mongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mongodb.document.Portfolio;
import com.springboot.mongodb.document.User;
import com.springboot.mongodb.repository.PortfolioService;

@RestController
@RequestMapping("/portfolios")
public class PortfolioResource {

	@Autowired
	private PortfolioService portfolioService;

	@GetMapping("")
	public List<Portfolio> getAllPortfolios() {
		return portfolioService.getAllPortfolios();
	}

	@PostMapping("")
	public List<Portfolio> createPortfolio(@RequestBody User user) {
		portfolioService.createPortfolio(user);
		return portfolioService.getAllPortfolios();
	}

	@GetMapping("/{userId}")
	public Portfolio getPortfolioByUserId(@PathVariable Integer userId) {
		return portfolioService.getPortfolioByUserId(userId);
	}

	@DeleteMapping("/{userId}")
	public void deletePortfolioByUserId(@PathVariable Integer userId) {
		portfolioService.deletePortfolioByUserId(userId);
	}

	/*
	 * @RequestMapping(value = "/{firstName}/{schemeCode}", method =
	 * RequestMethod.GET) public TxnResource getTxnResource() { return new
	 * TxnResource(); }
	 */

}
