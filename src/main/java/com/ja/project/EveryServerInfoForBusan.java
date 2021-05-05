package com.ja.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ja.project.vo.ServerInfoVO;

import lombok.Getter;

@Getter
public class EveryServerInfoForBusan {
//	private Map<String, ServerInfoVO> esi;
	private List<Map<String, ServerInfoVO>> everyServerInfo;
	

	public EveryServerInfoForBusan(String token) {
		super();
		// TODO Auto-generated constructor stub
		everyServerInfo = new ArrayList<Map<String,ServerInfoVO>>();
		Map<String, ServerInfoVO> esi = null;
		
		ServerInfoVO siv = null;
		esi = new LinkedHashMap<String, ServerInfoVO>();
		
		siv = new ServerInfoVO("서버명", "A1:B2:CD:5A:3C:F7", 4, 8, 50);//예시
		esi.put(siv.getServerName(), siv);
		
		everyServerInfo.add(esi);
		
	}
}
