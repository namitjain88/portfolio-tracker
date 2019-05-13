package com.springboot.mongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mongodb.document.Investment;
import com.springboot.mongodb.repository.InvestmentService;

@RestController
@RequestMapping(value = "/portfolios/{userId}/investments")
public class InvestmentResource {

	@Autowired
	private InvestmentService investmentService;

	@GetMapping("")
	public List<Investment> getAllInvestmentsByUserId(@PathVariable Integer userId) {
		return investmentService.getAllInvestmentsByUserId(userId);
	}

	@PostMapping("")
	public List<Investment> createInvestment(@PathVariable Integer userId, @RequestBody Investment txn) {
		txn.setUserId(userId);
		investmentService.createInvestment(txn);
		return investmentService.getAllInvestmentsByUserId(userId);
	}

	@PutMapping("")
	public Object saveInvestment(@PathVariable Long schemeCode, @RequestBody Investment txn) {
		txn.setSchemeCode(schemeCode);
		return investmentService.saveInvestment(txn);
	}

	@DeleteMapping("")
	public void deleteInvestment(@PathVariable Long schemeCode, @RequestBody Investment investment) {
		investment.setSchemeCode(schemeCode);
		investmentService.deleteInvestment(investment);
	}
}
