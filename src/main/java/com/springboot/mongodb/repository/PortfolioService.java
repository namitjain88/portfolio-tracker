package com.springboot.mongodb.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.document.Investment;
import com.springboot.mongodb.document.Portfolio;
import com.springboot.mongodb.document.Scheme;
import com.springboot.mongodb.document.User;

@Service
public class PortfolioService {

	@Autowired
	private UserService userService;

	@Autowired
	private SchemeService schemeService;

	@Autowired
	private InvestmentService investmentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PortfolioService.class);

	public List<Portfolio> getAllPortfolios() {
		List<Portfolio> portfolios = new ArrayList<>();
		userService.getAllUsers().forEach((p) -> portfolios.add(new Portfolio(p, null)));

		/* Traditional way */
		/*
		 * List<Scheme> schemeList = null; for (Portfolio p : portfolios) {
		 * schemeList = getInvestmentsBySchemes(p.getUser().getId());
		 * p.setSchemeList(schemeList); }
		 */
		/* Lambda way */
		portfolios.forEach(p -> p.setSchemeList(getInvestmentsBySchemes(p.getUser().getId())));
		LOGGER.trace("Trace level log");
		LOGGER.debug("Debug level log");
		LOGGER.info("Info level log");
		LOGGER.warn("Warn level log");

		return portfolios;
	}

	public Portfolio createPortfolio(User newUser) {
		Portfolio p = new Portfolio();
		p.setUser(userService.createUser(newUser));
		return p;
	}

	public Portfolio getPortfolioByUserId(Integer userId) {
		Portfolio p = new Portfolio();
		p.setUser(userService.getUserById(userId));
		List<Scheme> schemeList = getInvestmentsBySchemes(userId);
		p.setSchemeList(schemeList);
		return p;
	}

	public void deletePortfolioByUserId(Integer userId) {
		userService.deleteUser(userId);
		investmentService.deleteInvestmentsByUserId(userId);
	}

	/**
	 * @param p
	 * @return
	 */
	private List<Scheme> getInvestmentsBySchemes(Integer userId) {

		List<Scheme> schemeList = new ArrayList<>();
		Map<Long, Scheme> schemeMap = new HashMap<>();
		List<Investment> investments = investmentService.getAllInvestmentsByUserId(userId);

		investments.forEach(i -> {
			Scheme scheme = new Scheme(i.getSchemeCode(), null, null);
			if (!schemeMap.containsKey(scheme.getSchemeCode())) {
				scheme.setSchemeName(schemeService.getSchemeByCode(scheme.getSchemeCode()).getSchemeName());
				scheme.setTxnList(new ArrayList<>());
				schemeMap.put(scheme.getSchemeCode(), scheme);
			}
			schemeMap.get(scheme.getSchemeCode()).getTxnList().add(i);
		});
		schemeMap.values().forEach(s -> schemeList.add(s));

		/*
		 * Scheme scheme = null; for (Investment i : investments) { scheme = new
		 * Scheme(i.getSchemeCode(), null, null); if
		 * (!schemeMap.containsKey(scheme.getSchemeCode())) {
		 * scheme.setSchemeName(schemeService.getSchemeByCode(scheme.
		 * getSchemeCode()).getSchemeName()); scheme.setTxnList(new
		 * ArrayList<>()); schemeMap.put(scheme.getSchemeCode(), scheme); }
		 * schemeMap.get(scheme.getSchemeCode()).getTxnList().add(i); }
		 * schemeMap.values().forEach(s -> schemeList.add(s));
		 */
		return schemeList;
	}
}
