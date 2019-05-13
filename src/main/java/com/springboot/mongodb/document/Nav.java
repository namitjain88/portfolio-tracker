package com.springboot.mongodb.document;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nav_data")
@CompoundIndex(name = "nav_idx", def = "{ 'schemeCode' : 1, 'navDate' : -1 }", unique = true)
public class Nav {

	private Long schemeCode;
	private LocalDate navDate;
	private Double nav;

	public Nav(Long schemeCode, LocalDate navDate, Double nav) {
		super();
		this.schemeCode = schemeCode;
		this.navDate = navDate;
		this.nav = nav;
	}

	public Long getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(Long schemeCode) {
		this.schemeCode = schemeCode;
	}

	public LocalDate getNavDate() {
		return navDate;
	}

	public void setNavDate(LocalDate navDate) {
		this.navDate = navDate;
	}

	public Double getNav() {
		return nav;
	}

	public void setNav(Double nav) {
		this.nav = nav;
	}
}
