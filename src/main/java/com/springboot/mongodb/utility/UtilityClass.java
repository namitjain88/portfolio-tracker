package com.springboot.mongodb.utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UtilityClass {

	public static final String NAV_HISTORY_DOWNLOAD_URL = "http://portal.amfiindia.com/DownloadNAVHistoryReport_Po.aspx?tp=1&";

	public static LocalDate currentIstDate() {
		ZoneId indiaZone = ZoneId.of(ZoneId.SHORT_IDS.get("IST"));
		return LocalDate.now(indiaZone);
	}

	public static String formatDate(LocalDate dateObj) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu");
		return dateObj.format(formatter);
	}

	public static String createDownloadNavUrl(String frmdt, String todt) {
		return NAV_HISTORY_DOWNLOAD_URL + "frmdt=" + frmdt + "&todt=" + todt;
	}
}
