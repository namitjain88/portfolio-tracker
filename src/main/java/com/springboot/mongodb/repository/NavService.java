package com.springboot.mongodb.repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.mongodb.document.Nav;
import com.springboot.mongodb.utility.NavDataUtility;
import com.springboot.mongodb.utility.UtilityClass;

@Component
public class NavService {

	@Autowired
	private NavRepository navRepository;

	@Autowired
	private NavDataUtility navDataUtility;

	public List<Nav> findNavBySchemeCodeAndNavDate(Long schemeCode, String navDateStr) {
		LocalDate navDate = LocalDate.parse(navDateStr, DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
		return navRepository.findNavBySchemeCodeAndNavDate(schemeCode, navDate);
	}

	public List<Nav> getAllNavsBySchemeCode(Long schemeCode) {
		return navRepository.findNavBySchemeCode(schemeCode);
	}

	public int refreshNavInDb() {
		// navRepository.deleteAll();
		// return
		// (navRepository.saveAll(navDataUtility.getNavHistoryList())).size();
		syncNavData();
		return 0;
	}

	public void syncNavData() {
		// 1. Current date in India
		LocalDate currentDate = UtilityClass.currentIstDate();

		// 2. Fetch max nav date
		LocalDate maxNavDateInDb = navRepository.findFirstByOrderByNavDateDesc().getNavDate();

		// 3. MaxNavDate is before sysdate
		if (maxNavDateInDb.isBefore(currentDate)) {

			// 4. Changing max date to sysdate - 1
			currentDate = currentDate.minusDays(1);

			// 5. Changing max date to sysdate - 1
			maxNavDateInDb = maxNavDateInDb.plusDays(1);

			// 6. Create url after formating the dates in API required format
			String url = UtilityClass.createDownloadNavUrl(UtilityClass.formatDate(maxNavDateInDb),
					UtilityClass.formatDate(currentDate));

			String fileName = "/Nav.txt";
			try {
				NavDataUtility.saveUrl(fileName, url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
