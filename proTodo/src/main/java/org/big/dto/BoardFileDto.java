package org.big.dto;

import lombok.Data;

@Data
public class BoardFileDto {
	private int boardIdx;
	private long fileSize;
	private String originalFileName;
	private String storedFilePath;
}
