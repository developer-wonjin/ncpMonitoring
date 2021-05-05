package com.ja.project.vo;

import lombok.Data;

@Data
public class TargetVO {
	private String metric;
	private String name;
	private String key;
	private String startTime;
	private String endTime;
	
	public TargetVO(String metric, String Serverkey, String startTime, String endTime) {
		super();
		this.metric = metric;
		this.name = metric + "____0";
		this.key = Serverkey;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
