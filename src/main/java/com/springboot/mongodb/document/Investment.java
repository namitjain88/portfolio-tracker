package com.springboot.mongodb.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "investments")
@CompoundIndex(name = "inv_idx", def = "{ 'userId' : 1, 'schemeCode' : -1, 'invDate' : -1}", unique = true)
public class Investment {

	@Id
	private Long id;
	private Integer userId;
	private Long schemeCode;
	private LocalDate invDate;
	private Double invAmount;
	private Double invNav;
	private Double invUnits;

	public Investment() {
	}

	public Investment(Long id, Integer userId, Long schemeCode, LocalDate invDate, Double invAmount, Double invNav,
			Double invUnits) {
		super();
		this.id = id;
		this.userId = userId;
		this.schemeCode = schemeCode;
		this.invDate = invDate;
		this.invAmount = invAmount;
		this.invNav = invNav;
		this.invUnits = invUnits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(Long schemeCode) {
		this.schemeCode = schemeCode;
	}

	public LocalDate getInvDate() {
		return invDate;
	}

	public void setInvDate(LocalDate invDate) {
		this.invDate = invDate;
	}

	public Double getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(Double invAmount) {
		this.invAmount = invAmount;
	}

	public Double getInvNav() {
		return invNav;
	}

	public void setInvNav(Double invNav) {
		this.invNav = invNav;
	}

	public Double getInvUnits() {
		return invUnits;
	}

	public void setInvUnits(Double invUnits) {
		this.invUnits = invUnits;
	}

}
