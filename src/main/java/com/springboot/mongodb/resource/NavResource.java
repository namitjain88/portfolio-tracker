package com.springboot.mongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mongodb.document.Nav;
import com.springboot.mongodb.repository.NavService;

@RestController
@RequestMapping("/schemes")
public class NavResource {

	@Autowired
	private NavService navService;

	@RequestMapping(value = "/{schemeCode}/navs", method = RequestMethod.GET)
	public List<Nav> getAllNavs(@PathVariable Long schemeCode) {
		return navService.getAllNavsBySchemeCode(schemeCode);
	}

	@RequestMapping(value = "/{schemeCode}/navs/{navDate}", method = RequestMethod.GET)
	public List<Nav> getNav(@PathVariable Long schemeCode, @PathVariable String navDate) {
		return navService.findNavBySchemeCodeAndNavDate(schemeCode, navDate);
	}

	@RequestMapping(value = "/navs/refresh", method = RequestMethod.GET)
	public int refreshNavInDb() {
		return navService.refreshNavInDb();
	}
}
