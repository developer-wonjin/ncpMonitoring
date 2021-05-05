package com.ja.project.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorVOForBusan {
	private ServerInfoVO serverInfo;
	private String monitorDate;
	private String startDate;
	private String endDate;
	
	private Map<String, Double> xvd;
	
	//LoadAverage
	private Boolean isOverLoadAvg;
	
	private Map<String, Double> overLoadAvgTime_Mi01;
//	private Double avgPerMi01;
	
	private Map<String, Double> overLoadAvgTime_Mi05;
//	private Double avgPerMi05;
	
	private Map<String, Double> overLoadAvgTime_Mi15;
//	private Double avgPerMi15;

	//Swap
	private Boolean isOverSwap; 
	private Map<String, Double> overSwapTime;
	private Double avgOverSwap;

	//Memory
	private Boolean isOverMemory; 
	private Map<String, Double> overMemoryTime;
	private Double avgOverMemory;
	
	//Disk
	private Boolean isOverDisk;
	private Map<String, Double> overDiskTime;
	private Double avgOverDisk;
	
	
}