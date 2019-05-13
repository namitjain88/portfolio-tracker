package com.springboot.mongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mongodb.document.Scheme;
import com.springboot.mongodb.repository.SchemeService;

@RestController
@RequestMapping("/schemes")
public class SchemeResource {

	@Autowired
	private SchemeService schemeService;

	@GetMapping("")
	public List<Scheme> getAllSchemes() {
		return schemeService.getListOfSchemes();
	}

	@GetMapping("/{schemeCode}")
	public Scheme getSchemeByCode(@PathVariable Long schemeCode) {
		return schemeService.getSchemeByCode(schemeCode);
	}

	@RequestMapping("/refresh")
	public void refreshSchemesInDb() {
		schemeService.refreshSchemesInDb();
	}
}
