package com.springboot.mongodb.utility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.springboot.mongodb.document.Nav;
import com.springboot.mongodb.document.Scheme;

@Component
public class NavDataUtility {

	public static void main(String args[]) {
		// downloadDailyNav();

		/*
		 * Map<Long, String> schemeMap = getSchemes(); System.out.println(
		 * "Number of schemes = " + schemeMap.keySet().size());
		 * 
		 * // Write name of schemes in a text file File file = new File(
		 * "C:\\Users\\TCS Profile\\Desktop\\Nav\\SchemeList.txt");
		 * FileOutputStream fout = null; StringBuffer sb = new StringBuffer();
		 * try { fout = new FileOutputStream(file); } catch
		 * (FileNotFoundException e1) { e1.printStackTrace(); } for (Long
		 * schemeId : schemeMap.keySet()) { sb.append(schemeMap.get(schemeId));
		 * sb.append("\n"); } try { fout.write(sb.toString().getBytes()); }
		 * catch (IOException e) { }
		 */

		String filePath = "C:\\Users\\TCS Profile\\Desktop\\NavDownload_2018.txt";
		String url = "http://portal.amfiindia.com/DownloadNAVHistoryReport_Po.aspx?tp=1&frmdt=01-Jan-2018&todt=20-Apr-2018";
		long start, end;
		try {
			start = new Date().getTime();
			System.out.println("Start Time = " + new Date());
			saveUrl(filePath, url);
			end = new Date().getTime();
			System.out.println("End Time = " + new Date());
			System.out.println("Execution time in minutes = " + (((end - start) / 1000)) / 60f);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	public static void saveUrl(final String filename, final String urlString)
			throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			fout = new FileOutputStream(filename);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
	}

	public static Map<Long, String> getScheme() {
		Map<Long, String> schemesMap = new HashMap<>();
		Map<Long, Map<String, Double>> schemeNavHistoryMap = new HashMap<>();
		try {
			File folder = new File("C:\\Users\\TCS Profile\\Desktop\\Nav\\");
			int lineNum = 0;
			long lineCount = 0;
			long totalLines = 0;
			Map<String, Double> dateNavMap = null;
			FileReader fileReader;
			BufferedReader bufferedReader;
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isFile()) {
					fileReader = new FileReader(fileEntry);
					bufferedReader = new BufferedReader(fileReader);

					String[] schemesAttr = null;
					Long schemeId = null;
					String schemeName = null;
					Double nav = null;
					String navDate = null;

					String line;
					lineNum = 0;
					lineCount = 0;

					while ((line = bufferedReader.readLine()) != null) {
						lineCount++;
						if (lineNum == 0) {
							lineNum++;
							continue;
						}
						if (line.contains(";") == true) {
							schemesAttr = line.split(";");
							try {
								schemeId = Long.valueOf(schemesAttr[0]);
								schemeName = schemesAttr[1];
								nav = Double.valueOf(schemesAttr[2]);
								navDate = schemesAttr[5];
							} catch (Exception e) {
								// e.printStackTrace();
								nav = 0.0;
							}

							if (!schemesMap.containsKey(schemeId)) {
								schemesMap.put(schemeId, schemeName);
							}

							/* scheme code with its all historical navs */
							if (!schemeNavHistoryMap.containsKey(schemeId)) {
								dateNavMap = new HashMap<>();
								dateNavMap.put(navDate, nav);
								schemeNavHistoryMap.put(schemeId, dateNavMap);
							} else {
								dateNavMap = schemeNavHistoryMap.get(schemeId);

								if (!dateNavMap.containsKey(navDate)) {
									dateNavMap.put(navDate, nav);
								}
							}
							/* scheme code with its all historical navs */
						}
					}
					totalLines += lineCount;
					System.out.println("File Name = " + fileEntry.getName() + ", Line Count = " + lineCount);
					fileReader.close();
					bufferedReader.close();
				}
			}
			System.out.println("Total lines read = " + totalLines);

			long numOfNavRecords = 0;
			for (Long schemeId : schemeNavHistoryMap.keySet()) {
				numOfNavRecords += schemeNavHistoryMap.get(schemeId).size();
			}
			System.out.println("Number of nav records = " + numOfNavRecords);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return schemesMap;
	}

	public List<Scheme> getListOfSchemes() {
		List<Scheme> schemeList = new ArrayList<>();
		Map<Long, Scheme> schemesMap = new HashMap<>();
		File folder = new File("C:\\Users\\TCS Profile\\Desktop\\Nav\\");
		int lineNum = 0;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isFile()) {
					fileReader = new FileReader(fileEntry);
					bufferedReader = new BufferedReader(fileReader);

					String[] schemesAttr = null;
					Long schemeId = null;
					String schemeName = null;
					String line;
					lineNum = 0;

					while ((line = bufferedReader.readLine()) != null) {
						if (lineNum == 0) {
							lineNum++;
							continue;
						}
						if (line.contains(";")) {
							schemesAttr = line.split(";");
							schemeId = Long.valueOf(schemesAttr[0]);
							schemeName = schemesAttr[1];
							if (!schemesMap.containsKey(schemeId)) {
								schemesMap.put(schemeId, new Scheme(schemeId, schemeName, null));
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Long schemeId : schemesMap.keySet())
			schemeList.add(schemesMap.get(schemeId));
		return schemeList;
	}

	public List<Nav> getNavHistoryList() {
		List<Nav> navHistoryList = new ArrayList<>();
		Map<Long, Map<LocalDate, Double>> schemeNavHistoryMap = new HashMap<>();
		Map<LocalDate, Double> dateNavMap = null;
		try {
			File folder = new File("C:\\Users\\TCS Profile\\Desktop\\Nav\\");
			int lineNum = 0;
			FileReader fileReader;
			BufferedReader bufferedReader;
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isFile()) {
					fileReader = new FileReader(fileEntry);
					bufferedReader = new BufferedReader(fileReader);

					String[] schemesAttr = null;
					Long schemeId = null;
					Double nav = null;
					LocalDate navDate = null;
					String line;
					lineNum = 0;

					while ((line = bufferedReader.readLine()) != null) {
						if (lineNum == 0) {
							lineNum++;
							continue;
						}
						if (line.contains(";")) {
							schemesAttr = line.split(";");
							try {
								schemeId = Long.valueOf(schemesAttr[0]);
								nav = Double.valueOf(schemesAttr[2]);
								navDate = LocalDate.parse(schemesAttr[5], DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
							} catch (Exception e) {
								// e.printStackTrace();
								nav = 0.0;
							}

							/* scheme code with its all historical navs */
							if (!schemeNavHistoryMap.containsKey(schemeId)) {
								dateNavMap = new HashMap<>();
								dateNavMap.put(navDate, nav);
								schemeNavHistoryMap.put(schemeId, dateNavMap);
							} else {
								dateNavMap = schemeNavHistoryMap.get(schemeId);
								if (!dateNavMap.containsKey(navDate)) {
									dateNavMap.put(navDate, nav);
								}
							}
							/* scheme code with its all historical navs */
						}
					}
					fileReader.close();
					bufferedReader.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Long schemeId : schemeNavHistoryMap.keySet()) {
			dateNavMap = schemeNavHistoryMap.get(schemeId);
			for (LocalDate navDate : dateNavMap.keySet()) {
				navHistoryList.add(new Nav(schemeId, navDate, dateNavMap.get(navDate)));
			}
		}
		return navHistoryList;
	}

	public static List<Nav> downloadDailyNav() {
		List<Nav> navList = new ArrayList<>();
		BufferedInputStream in = null;
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		String urlString = "https://www.amfiindia.com/spages/NAVOpen.txt?t=";
		ZoneId indiaZone = ZoneId.of(ZoneId.SHORT_IDS.get("IST"));
		LocalDateTime currentDate = LocalDateTime.now(indiaZone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuuhhmmss");
		String runTime = currentDate.format(formatter);
		String[] schemeAttrs = null;
		String inputLine = null;
		System.out.println("URL = " + urlString + runTime);
		try {
			Long schemeCode;
			LocalDate navDate;
			Double nav;
			int lineNum = 0;
			in = new BufferedInputStream(new URL(urlString + runTime).openStream());
			bufferedReader = new BufferedReader(new InputStreamReader(in));

			while ((inputLine = bufferedReader.readLine()) != null) {
				if (lineNum++ == 0) {
					continue;
				}
				stringBuilder.append(inputLine);
				stringBuilder.append("\n");

				if (inputLine.contains(";")) {
					schemeAttrs = inputLine.split(";");
					schemeCode = Long.valueOf(schemeAttrs[0]);
					try {
						nav = Double.valueOf(schemeAttrs[4]);
					} catch (NumberFormatException nfe) {
						nav = 0.0;
					}
					navDate = LocalDate.parse(schemeAttrs[7], DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
					navList.add(new Nav(schemeCode, navDate, nav));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(inputLine);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return navList;
	}
}