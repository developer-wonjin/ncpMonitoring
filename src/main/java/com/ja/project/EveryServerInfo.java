package com.ja.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ja.project.vo.ServerInfoVO;

import lombok.Getter;

@Getter
public class EveryServerInfo {
//	private Map<String, ServerInfoVO> esi;
	private List<Map<String, ServerInfoVO>> everyServerInfo;
	
	public EveryServerInfo(String token) {
		super();
		// TODO Auto-generated constructor stub
		everyServerInfo = new ArrayList<Map<String,ServerInfoVO>>(6);
		Map<String, ServerInfoVO> esi = null;
		
		ServerInfoVO siv = null;
		//bizspring(1)
		esi = new LinkedHashMap<String, ServerInfoVO>();
				
		siv = new ServerInfoVO("¼­¹ö¸í", "F1:20:CD:64:6D:6F", 4, 8, 50);
		esi.put(siv.getServerName(), siv);
		
		everyServerInfo.add(esi);
		
	}
}