package com.ja.project.vo;

import java.util.List;

import lombok.Data;

@Data

public class Targets {
	private List<TargetVO> targets;

	public Targets(List<TargetVO> targets) {
		super();
		this.targets = targets;
	}
}
