package com.ja.project;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ja.project.vo.ErrorVO;
import com.ja.project.vo.ErrorVOForBusan;
import com.ja.project.vo.RequestData;
import com.ja.project.vo.ServerInfoVO;
import com.ja.project.vo.TargetVO;
import com.ja.project.vo.Targets;

import lombok.Getter;

@Getter
public class Basic {
	
	private String monitorStart, monitorEnd; 	
	private String startDate, endDate; 	
	private String startTime2 ; 
	private List<ErrorVO> finalResult				  = new ArrayList<ErrorVO>();
	private List<ErrorVOForBusan> finalResultForBusan = new ArrayList<ErrorVOForBusan>();
	
	private int n;
	
	
	public static void main(String[] args) throws IOException {
		Basic basic = new Basic(3);
		basic.requestTEST("abc");
	}
	
	public Basic(int n) {
		super();
		this.n = n;
	}
	
	public void basicGet() {
		System.setProperty("webdriver.chrome.driver", "C://selenium/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("no-sandbox");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		options.addArguments("lang=ko_KR");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.mykeystone.com/dashboard.php");
		String html = driver.getPageSource();
		System.out.println(html);
		driver.quit();
	}

	public void basicParseJsoup() {
		System.setProperty("webdriver.chrome.driver", "C://selenium/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("no-sandbox");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		options.addArguments("lang=ko_KR");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.seleniumtest.com2/hello.php");
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		Elements oParse = doc.select("h3.tit_cate");
		System.out.println(oParse.get(0).text());
		driver.quit();
	}

	public void basicParseSelenium() {
		System.setProperty("webdriver.chrome.driver", "C://selenium/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("no-sandbox");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		options.addArguments("lang=ko_KR");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.seleniumtest.com2/hello.php");
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		Elements oParse = doc.select("div.buy_wrap");
		System.out.println(oParse);
		if (oParse.size() > 0) {
			System.out.println(oParse.get(0).text());
		} else {
			System.out.println("no elem");
		}

		driver.quit();
	}
	
	public void basicWaitDom() {
		System.setProperty("webdriver.chrome.driver", "C://selenium/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("no-sandbox");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		options.addArguments("lang=ko_KR");
		options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://122.99.192.218/dynamic/dom_load.php");
		long delay = 3;
		boolean flagLoading = false;
		try {
			WebElement myElem = new WebDriverWait(driver, delay).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.buy_wrap")));
			flagLoading = true;
		} catch (TimeoutException e) {
			flagLoading = false;
		}
		if (flagLoading == true) {
			String html = driver.getPageSource();
			Document doc = Jsoup.parse(html);
//			System.out.println(doc);
			Elements oParse = doc.select("div.buy_wrap");
//			System.out.println(oParse);
			if (oParse.size() > 0) {
				System.out.println(oParse.get(0).text());
			} else {
				System.out.println("no elem");
			}
		} else {
			System.out.println("NO DOM");
		}
		driver.quit();
	}
	
	public Map<String, Object> basicClickDom() throws IOException {
		System.out.println("basicClickDom硫붿냼�뱶");
//		System.setProperty("webdriver.chrome.driver", "C://selenium/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "/home/bizspring/chromedriver");
		 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("no-sandbox");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		options.addArguments("lang=ko_KR");
		options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		driver.get("NCP로그인페이지 URL주소");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.name("username")).sendKeys("아이디");
		driver.findElement(By.name("passwordPlain")).sendKeys("비밀번호");
		// 嚥≪뮄�젃占쎌뵥 甕곌쑵�뱣占쎌뱽 占쎈땭占쎌쑎雅뚯눘�쁽
		driver.findElement(By.tagName("button")).click();
		Cookie ncpTokenCookie = driver.manage().getCookieNamed("ncp");
		String token = ncpTokenCookie.getValue();
		System.out.println("token: " + token);
		driver.quit();
		
		return requestTEST(token);
		
	}
	
	public Map<String, Object> busanNews() throws IOException {
		System.out.println("busanNews硫붿냼�뱶");
		
		String OStype = System.getProperty("os.name");
		System.getProperties().list(System.out);
		
//		System.setProperty("webdriver.chrome.driver", "C://selenium/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "/home/bizspring/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("no-sandbox");
		options.addArguments("window-size=1920x1080");
		options.addArguments("disable-gpu");
		options.addArguments("lang=ko_KR");
		options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		driver.get("NCP로그인페이지 URL주소");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.name("username")).sendKeys("아이디");
		driver.findElement(By.name("passwordPlain")).sendKeys("비밀번호");
		// 嚥≪뮄�젃占쎌뵥 甕곌쑵�뱣占쎌뱽 占쎈땭占쎌쑎雅뚯눘�쁽
		driver.findElement(By.tagName("button")).click();
		Cookie ncpTokenCookie = driver.manage().getCookieNamed("ncp");
		String token = ncpTokenCookie.getValue();
		System.out.println("token: " + token);
		driver.quit();
		
		return busanRequestTest(token);
		
	}
	
	public Map<String, Object> busanRequestTest(String token) throws IOException {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		
		Date today = new Date();
		
		long todayUnixTimestamp = today.getTime();
		monitorEnd 	= format.format(new Date(todayUnixTimestamp));
		monitorStart 	= format.format(new Date(todayUnixTimestamp-24*60*60*1000));
		startTime2 	= format.format(new Date(todayUnixTimestamp-60*1000));
		
		Targets targets = null;
		EveryServerInfoForBusan esi = new EveryServerInfoForBusan(token);		
		RequestData rd = new RequestData();
		
		List<Map<String, ServerInfoVO>> serverGroup = esi.getEveryServerInfo();
		Map<String, String[]> graphInfo = rd.getGraphInfo();
		
		for(Entry<String, ServerInfoVO> serverEntry : serverGroup.get(n-1).entrySet()) {
			ServerInfoVO serverInfo = serverEntry.getValue();
			
			URL url = new URL("https://monitoring-api.ncloud.com/monapi/pfmnc");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			httpCon.setRequestProperty("X-NCP-access-token", token);
			
			
			OutputStream os = httpCon.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			
			List<TargetVO> tg = new ArrayList<TargetVO>();
			
		    for(Entry<String, String[]> graphEntry : graphInfo.entrySet()) {
		    	String graphType = graphEntry.getKey();
		    	String[] metricArr = graphEntry.getValue();
		    	for(String metric : metricArr) {
		    		tg.add(new TargetVO(metric, serverInfo.getServerKey(), monitorStart, monitorEnd));
		    	}
		    }
		    
		    targets = new Targets(tg);
			Gson gson = new Gson();
			String json = gson.toJson(targets);
			osw.write(json);
			osw.flush();
			osw.close();
			os.close();  
			httpCon.connect();

			String result;
			BufferedInputStream bis = new BufferedInputStream(httpCon.getInputStream());
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result2 = bis.read();
			while(result2 != -1) {
			    buf.write((byte) result2);
			    result2 = bis.read();
			}
			result = buf.toString();
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			JsonObject jsonObject = element.getAsJsonObject();
			JsonElement jsonEle = jsonObject.get("csv");
			String csvString = jsonEle.getAsString();
			String[] fragments = csvString.split("\\n");
			
			String header = fragments[0];//占쎈엘占쎈쐭
			String[] grapData = Arrays.copyOfRange(fragments, 1, fragments.length - 1);//占쎈쑓占쎌뵠占쎄숲
			
			
			
			//File System
			
			StringBuffer sb = new StringBuffer();
			sb.append("https://monitoring-api.ncloud.com/monapi/servers/");
	        sb.append(serverInfo.getServerKey());
	        sb.append("/filesystem?");
	        sb.append("startTime=");
	        sb.append(startTime2);
	        sb.append("&endTime=");
	        sb.append(monitorEnd);
	        
			String urlStr = sb.toString();
//			System.out.println(urlStr + " 占쎌뒄筌ｏ옙");
			url = new URL(urlStr);
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("Content-Type","application/json");
			httpCon.setRequestProperty("X-NCP-access-token", token);

			httpCon.connect();

			//read the inputstream and print it
			bis = new BufferedInputStream(httpCon.getInputStream());
			buf = new ByteArrayOutputStream();
			result2 = bis.read();
			while(result2 != -1) {
			    buf.write((byte) result2);
			    result2 = bis.read();
			}
			result = buf.toString();
			parser = new JsonParser();
			element = parser.parse(result);
			jsonObject = element.getAsJsonObject();
			JsonArray jsonArr = jsonObject.get("rows").getAsJsonArray();
			
			Map<String, Double> xvd = new LinkedHashMap<String, Double>();
//			double[] xvd = new double[6];
			
			for(JsonElement jsonRow : jsonArr) {
				Type type = new TypeToken<Map<String, String>>(){}.getType();
				Map<String, String> myMap = gson.fromJson(jsonRow, type);
				if(myMap.get("devNm").equals("/dev/xvda1")) {
					xvd.put("/dev/xvda1", Double.parseDouble(myMap.get("usedPer")));
				}
				if(myMap.get("devNm").equals("/dev/xvda2")) {
					xvd.put("/dev/xvda2", Double.parseDouble(myMap.get("usedPer")));
				}
				if(myMap.get("devNm").equals("/dev/xvdb1")) {
					xvd.put("/dev/xvdb1", Double.parseDouble(myMap.get("usedPer")));
				}
				if(myMap.get("devNm").equals("/dev/xvdb2")) {
					xvd.put("/dev/xvdb2", Double.parseDouble(myMap.get("usedPer")));
				}
				if(myMap.get("devNm").equals("/dev/xvdc1")) {
					xvd.put("/dev/xvdc1", Double.parseDouble(myMap.get("usedPer")));
				}
				if(myMap.get("devNm").equals("/dev/xvdc2")) {
					xvd.put("/dev/xvdc2", Double.parseDouble(myMap.get("usedPer")));
				}
			}
			
			
			analysisDataForBusan(serverInfo, fragments[0].split(","), grapData, xvd);
						
		}
		
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse

		String finalResultStr = gson.toJson(finalResultForBusan);
		String prettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalResultForBusan);
//		System.out.println(finalResultStr);
//		System.out.println(prettyString);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("allInfo", finalResult);
		ret.put("allInfoStr", finalResultStr);
//		ret.put("allInfoStr", prettyString);
		
		return ret;
	}

	
	
	public Map<String, Object> requestTEST(String token) throws IOException {
//		token = "xTqunp9V7tCAfZOIAfV3nzkmfH+FLmttx1pd2QB/ikP+FHvQaSla1xqLS/SrErCRgbuu/a1mfUbmGFjNU46moHHoKIR1/pBaKtyrlt7c62QFO9BDRsSfoFFj169VUdOEfvXsdoHOJpQTxjR3pQJXEU2Rdp7Lvs4TQ/nMfxXUSxCKzP+sflW4BTGyTn1VfZv2gJt+m7cyCQA3bAN1r4jQZ2WQaJp6+jeZMjXkFUe6AY8eZD5I9Ldy1vVQx1+sxtgs63R1Lo1fkSeJ2vT8uxXo3PXwLv0zgS7c4ZgJYikdjXt1bz8pfRqba7DANgP+lEU2mBE9nW8bQ3Wn+vmYgII53/IZpEhTzo8qor9aeCRzdJKZxoYgc8PSuRnIrV0+LpO751O5T14ZOjrDrhdkNbsV2GwnnpN5nll1uuxuLuGwBmk=";
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		
		Date today = new Date();
		System.out.println("today: " + today);
		
		long todayUnixTimestamp = today.getTime();
		monitorEnd 	= format.format(new Date(todayUnixTimestamp));
		monitorStart 	= format.format(new Date(todayUnixTimestamp-24*60*60*1000));
		startTime2 	= format.format(new Date(todayUnixTimestamp-60*1000));
		
		Targets targets = null;
		EveryServerInfo esi = new EveryServerInfo(token);		
		RequestData rd = new RequestData();
		
		List<Map<String, ServerInfoVO>> serverGroup = esi.getEveryServerInfo();
		Map<String, String[]> graphInfo = rd.getGraphInfo();
		
		for(Entry<String, ServerInfoVO> serverEntry : serverGroup.get(n-1).entrySet()) {
//			System.out.println("https://monitoring-api.ncloud.com/monapi/pfmnc 占쎌뒄筌ｏ옙");
			ServerInfoVO serverInfo = serverEntry.getValue();
			
//			System.out.println("占쎄퐣甕곤옙" + serverInfo.getServerName() + ": ");
			
			URL url = new URL("https://monitoring-api.ncloud.com/monapi/pfmnc");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			httpCon.setRequestProperty("X-NCP-access-token", token);
			
			
			
			OutputStream os = httpCon.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			
			List<TargetVO> tg = new ArrayList<TargetVO>();
			
		    for(Entry<String, String[]> graphEntry : graphInfo.entrySet()) {
		    	String graphType = graphEntry.getKey();
		    	String[] metricArr = graphEntry.getValue();
		    	for(String metric : metricArr) {
		    		tg.add(new TargetVO(metric, serverInfo.getServerKey(), monitorStart, monitorEnd));
		    	}
		    }
		    
		    targets = new Targets(tg);
			Gson gson = new Gson();
			String json = gson.toJson(targets);
//			System.out.println("request payload Data: " + json);
			osw.write(json);
			osw.flush();
			osw.close();
			os.close();  
			httpCon.connect();

			//read the inputstream and print it
			String result;
			BufferedInputStream bis = new BufferedInputStream(httpCon.getInputStream());
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result2 = bis.read();
			while(result2 != -1) {
			    buf.write((byte) result2);
			    result2 = bis.read();
			}
			result = buf.toString();
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			JsonObject jsonObject = element.getAsJsonObject();
			JsonElement jsonEle = jsonObject.get("csv");
			String csvString = jsonEle.getAsString();
			String[] fragments = csvString.split("\\n");
			
			String header = fragments[0];//占쎈엘占쎈쐭
			String[] grapData = Arrays.copyOfRange(fragments, 1, fragments.length - 1);//占쎈쑓占쎌뵠占쎄숲
			
			
			
			//File System
			
			StringBuffer sb = new StringBuffer();
			sb.append("https://monitoring-api.ncloud.com/monapi/servers/");
	        sb.append(serverInfo.getServerKey());
	        sb.append("/filesystem?");
	        sb.append("startTime=");
	        sb.append(startTime2);
	        sb.append("&endTime=");
	        sb.append(monitorEnd);
	        
			String urlStr = sb.toString();
//			System.out.println(urlStr + " 占쎌뒄筌ｏ옙");
			url = new URL(urlStr);
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("Content-Type","application/json");
			httpCon.setRequestProperty("X-NCP-access-token", token);

			httpCon.connect();

			//read the inputstream and print it
			bis = new BufferedInputStream(httpCon.getInputStream());
			buf = new ByteArrayOutputStream();
			result2 = bis.read();
			while(result2 != -1) {
			    buf.write((byte) result2);
			    result2 = bis.read();
			}
			result = buf.toString();
			parser = new JsonParser();
			element = parser.parse(result);
			jsonObject = element.getAsJsonObject();
			JsonArray jsonArr = jsonObject.get("rows").getAsJsonArray();
			
			
			double[] xvd = new double[3];
			
			for(JsonElement jsonRow : jsonArr) {
				Type type = new TypeToken<Map<String, String>>(){}.getType();
				Map<String, String> myMap = gson.fromJson(jsonRow, type);
				if(myMap.get("devNm").equals("/dev/xvda1")) {
					xvd[0] = Double.parseDouble(myMap.get("usedPer"));
				}
				if(myMap.get("devNm").equals("/dev/xvdb1")) {
					xvd[1] = Double.parseDouble(myMap.get("usedPer"));
				}
				if(myMap.get("devNm").equals("/dev/xvdc1")) {
					xvd[2] = Double.parseDouble(myMap.get("usedPer"));
				}
			}
			
			analysisData(serverInfo, fragments[0].split(","), grapData, xvd);
						
		}
		
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse

		String finalResultStr = gson.toJson(finalResult);
		String prettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalResult);
//		System.out.println(finalResultStr);
//		System.out.println(prettyString);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("allInfo", finalResult);
		ret.put("allInfoStr", finalResultStr);
//		ret.put("allInfoStr", prettyString);
		
		return ret;
	}

//	private void printAPIResponse(String header, String[] data) {
//		//API占쎌벓占쎈뼗占쎈쑓占쎌뵠占쎄숲 �빊�뮆�젾
//		System.out.println(header);
//		for(String row : data) {
//			System.out.println(row);
//		}
//	}
	
	public void analysisDataForBusan(ServerInfoVO serverInfo, String[] header, String[] data, Map<String, Double> xvd) {
		
		int cntCPUCore = serverInfo.getCntCPUCore();
		long unixTime = 0;
//		ErrorVO errorVO = new ErrorVO(isOverLoad, isOverSwap, isOverMemory, isOverDisk, listOfTime)
		
		//Day
		Boolean isOverLoadAvg = false;
		Boolean isOverSwap = false;
		Boolean isOverMemory = false;
		Boolean isOverDisk = false;
		
		Map<String, Double> overLoadAvgTime_Mi01 = new LinkedHashMap<String, Double>();
		Map<String, Double> overLoadAvgTime_Mi05 = new LinkedHashMap<String, Double>();
		Map<String, Double> overLoadAvgTime_Mi15 = new LinkedHashMap<String, Double>();

		Map<String, Double> overSwapTime		 = new LinkedHashMap<String, Double>();
		Map<String, Double> overMemoryTime		 = new LinkedHashMap<String, Double>();
		Map<String, Double> overDiskTime		 = new LinkedHashMap<String, Double>();
		
		
		
		for (String row : data) { //row占쎈릭占쎄돌揶쏉옙 占쎈립 占쏙옙占쎌뿫占쎌뱽 占쎌벥沃섓옙
			//moment
			String[] parts = row.split(",");
			DateFormat format = new SimpleDateFormat("MM-dd HH:mm");
			unixTime = Long.parseLong(parts[0]);
			String errorTime = format.format(new Date(unixTime));


			for (int i = 1; i < header.length; i++) {
				double yData = Double.parseDouble(parts[i]);
				
				if(header[i].contains("ld") && yData > cntCPUCore ) {
					//LoadAvg
					if(header[i].contains("mi1")) {
						overLoadAvgTime_Mi01.put(errorTime, yData);
					}
					if(header[i].contains("mi5")) {
						overLoadAvgTime_Mi05.put(errorTime, yData);
					}
					if(header[i].contains("mi15")) {
						overLoadAvgTime_Mi15.put(errorTime, yData);
					}
					isOverLoadAvg = true;
				}
				if(header[i].contains("swap") && yData > 90 ) {
					overSwapTime.put(errorTime, yData);
					isOverSwap = true;
				}
				if(header[i].contains("mem") && yData > 90 ) {
					overMemoryTime.put(errorTime, yData);
					isOverMemory = true;
				}
				if(header[i].contains("disk") && yData > 90 ) {
					overDiskTime.put(errorTime, yData);
					isOverDisk = true;
				}
			}
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String monitorDate = format.format(new Date());
//		System.out.println(overLoadAvgTime_Mi01);
		Double avgPerMi01 = null; 
		Double avgPerMi05 = null; 
		Double avgPerMi15 = null; 
		
		Double avgOverSwap = null; 
		Double avgOverMemory = null; 
		Double avgOverDisk = null; 
		if(overLoadAvgTime_Mi01.size() != 0)avgPerMi01 = overLoadAvgTime_Mi01.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overLoadAvgTime_Mi05.size() != 0)avgPerMi01 = overLoadAvgTime_Mi05.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overLoadAvgTime_Mi15.size() != 0)avgPerMi01 = overLoadAvgTime_Mi15.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();

		if(overSwapTime.size() != 0)avgOverSwap = overSwapTime.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overMemoryTime.size() != 0)avgOverMemory = overMemoryTime.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overDiskTime.size() != 0)avgOverDisk = overDiskTime.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		
		
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date today = new Date();
		long todayUnixTimestamp = today.getTime();
		endDate 	= format.format(new Date(todayUnixTimestamp));
		startDate 	= format.format(new Date(todayUnixTimestamp-24*60*60*1000)); 
		ErrorVOForBusan errorVO = new ErrorVOForBusan(
				serverInfo,
				monitorDate, 
				startDate,
				endDate, 
				xvd, 
				isOverLoadAvg,
				overLoadAvgTime_Mi01,
//				avgPerMi01,
				overLoadAvgTime_Mi05,
//				avgPerMi05,
				overLoadAvgTime_Mi15,
//				avgPerMi15, 
				isOverSwap, 
				overSwapTime, 
				avgOverSwap, 
				isOverMemory,
				overMemoryTime, 
				avgOverMemory, 
				isOverDisk, 
				overDiskTime,
				avgOverDisk
		);
				
		finalResultForBusan.add(errorVO);
	}
	
	
	// 占쎄퐣甕곌쑴�뵥占쎈７, header : date, mi5, .... , data array
	public void analysisData(ServerInfoVO serverInfo, String[] header, String[] data, double[] xvd) {
		
		int cntCPUCore = serverInfo.getCntCPUCore();
		long unixTime = 0;
//		ErrorVO errorVO = new ErrorVO(isOverLoad, isOverSwap, isOverMemory, isOverDisk, listOfTime)
		
		//Day
		Boolean isOverLoadAvg = false;
		Boolean isOverSwap = false;
		Boolean isOverMemory = false;
		Boolean isOverDisk = false;
		
		Map<String, Double> overLoadAvgTime_Mi01 = new LinkedHashMap<String, Double>();
		Map<String, Double> overLoadAvgTime_Mi05 = new LinkedHashMap<String, Double>();
		Map<String, Double> overLoadAvgTime_Mi15 = new LinkedHashMap<String, Double>();

		Map<String, Double> overSwapTime		 = new LinkedHashMap<String, Double>();
		Map<String, Double> overMemoryTime		 = new LinkedHashMap<String, Double>();
		Map<String, Double> overDiskTime		 = new LinkedHashMap<String, Double>();
		
		
		
		for (String row : data) { //row占쎈릭占쎄돌揶쏉옙 占쎈립 占쏙옙占쎌뿫占쎌뱽 占쎌벥沃섓옙
			//moment
			String[] parts = row.split(",");
			DateFormat format = new SimpleDateFormat("MM-dd HH:mm");
			unixTime = Long.parseLong(parts[0]);
			String errorTime = format.format(new Date(unixTime));


			for (int i = 1; i < header.length; i++) {
				double yData = Double.parseDouble(parts[i]);
				
				if(header[i].contains("ld") && yData > cntCPUCore ) {
					//LoadAvg
					if(header[i].contains("mi1")) {
						overLoadAvgTime_Mi01.put(errorTime, yData);
					}
					if(header[i].contains("mi5")) {
						overLoadAvgTime_Mi05.put(errorTime, yData);
					}
					if(header[i].contains("mi15")) {
						overLoadAvgTime_Mi15.put(errorTime, yData);
					}
					isOverLoadAvg = true;
				}
				if(header[i].contains("swap") && yData > 90 ) {
					overSwapTime.put(errorTime, yData);
					isOverSwap = true;
				}
				if(header[i].contains("mem") && yData > 90 ) {
					overMemoryTime.put(errorTime, yData);
					isOverMemory = true;
				}
				if(header[i].contains("disk") && yData > 90 ) {
					overDiskTime.put(errorTime, yData);
					isOverDisk = true;
				}
			}
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String monitorDate = format.format(new Date());
//		System.out.println(overLoadAvgTime_Mi01);
		Double avgPerMi01 = null; 
		Double avgPerMi05 = null; 
		Double avgPerMi15 = null; 
		
		Double avgOverSwap = null; 
		Double avgOverMemory = null; 
		Double avgOverDisk = null; 
		if(overLoadAvgTime_Mi01.size() != 0)avgPerMi01 = overLoadAvgTime_Mi01.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overLoadAvgTime_Mi05.size() != 0)avgPerMi01 = overLoadAvgTime_Mi05.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overLoadAvgTime_Mi15.size() != 0)avgPerMi01 = overLoadAvgTime_Mi15.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();

		if(overSwapTime.size() != 0)avgOverSwap = overSwapTime.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overMemoryTime.size() != 0)avgOverMemory = overMemoryTime.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		if(overDiskTime.size() != 0)avgOverDisk = overDiskTime.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		
		
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date today = new Date();
		long todayUnixTimestamp = today.getTime();
		endDate 	= format.format(new Date(todayUnixTimestamp));
		startDate 	= format.format(new Date(todayUnixTimestamp-24*60*60*1000)); 
		ErrorVO errorVO = new ErrorVO(
				serverInfo,
				monitorDate, 
				startDate,
				endDate, 
				xvd, 
				isOverLoadAvg,
				overLoadAvgTime_Mi01,
//				avgPerMi01,
				overLoadAvgTime_Mi05,
//				avgPerMi05,
				overLoadAvgTime_Mi15,
//				avgPerMi15, 
				isOverSwap, 
				overSwapTime, 
				avgOverSwap, 
				isOverMemory,
				overMemoryTime, 
				avgOverMemory, 
				isOverDisk, 
				overDiskTime,
				avgOverDisk
		);
				
		finalResult.add(errorVO);
	}




}