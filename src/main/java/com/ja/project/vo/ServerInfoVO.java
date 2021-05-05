package com.ja.project.vo;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerInfoVO {
	private String serverName;
	private String serverKey;
	private int cntCPUCore;
	private int memoryVolume;
	private int diskVolume;
	
	public ServerInfoVO(String serverName, String token) throws IOException {
		super();
		this.serverName = serverName;
		String urlStr = "https://console.ncloud.com/server/api/v1/servers/instances?subAccountNo=23607&isOwner=false&searchField=serverName&inputText=" + serverName;
		URL url = new URL(urlStr);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		httpCon.setRequestProperty("Content-Type","application/json");
		httpCon.setRequestProperty("X-NCP-access-token", token);
		
		OutputStream os = httpCon.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		
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
		
		System.out.println(element);
		
	}
}

