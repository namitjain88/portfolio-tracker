package com.springboot.mongodb.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "schemes")
@CompoundIndex(name = "scheme_idx", def = "{ 'schemeName' : 1, 'schemeCode' : 1 }")
public class Scheme {

	@Id
	private Long schemeCode;
	private String schemeName;
	@Transient
	private List<Investment> txnList;

	public Scheme() {
	}

	public Scheme(Long schemeCode, String schemeName, List<Investment> txnList) {
		this.schemeCode = schemeCode;
		this.schemeName = schemeName;
		this.txnList = txnList;
	}

	public Long getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(Long schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public List<Investment> getTxnList() {
		return txnList;
	}

	public void setTxnList(List<Investment> txnList) {
		this.txnList = txnList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schemeCode == null) ? 0 : schemeCode.hashCode());
		result = prime * result + ((schemeName == null) ? 0 : schemeName.hashCode());
		result = prime * result + ((txnList == null) ? 0 : txnList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scheme other = (Scheme) obj;
		if (schemeCode == null) {
			if (other.schemeCode != null)
				return false;
		} else if (!schemeCode.equals(other.schemeCode))
			return false;
		return true;
	}
}
