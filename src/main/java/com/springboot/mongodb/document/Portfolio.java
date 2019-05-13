package com.springboot.mongodb.document;

import java.util.List;

//@Document(collection = "portfolios")
// @CompoundIndex(name = "portfolio_idx", unique = true, def = "{ 'userId' : 1,
// 'schemeCode' : -1}")
public class Portfolio {

	private User user;
	private List<Scheme> schemeList;

	public Portfolio() {
	}

	public Portfolio(User user, List<Scheme> schemeList) {
		this.user = user;
		this.schemeList = schemeList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Scheme> getSchemeList() {
		return schemeList;
	}

	public void setSchemeList(List<Scheme> schemeList) {
		this.schemeList = schemeList;
	}

}
