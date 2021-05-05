package com.ja.project.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RequestData {
	private Map<String, String[]> graphInfo;
	private List<String> fileSystem;

	public RequestData() {
		String [] metric = null;
		this.graphInfo = new HashMap<String, String[]>();
		
		metric = new String[]{"mi1.avg.ld.cnt","mi5.avg.ld.cnt","mi15.avg.ld.cnt"};
		this.graphInfo.put("LoadAverage", metric);

		metric = new String[]{"mem.usert"};
		this.graphInfo.put("MemoryUsage", metric);
		
		metric = new String[]{"swap.usert"};
		this.graphInfo.put("SwapUsage", metric);
		
		metric = new String[]{"disk.used.rto"};
		this.graphInfo.put("Disk", metric);
		
		this.fileSystem = new ArrayList<String>();
		this.fileSystem.add("/dev/xvda1");
		this.fileSystem.add("/dev/xvdb1");
		this.fileSystem.add("/dev/xvdc1");
	}
	
}
