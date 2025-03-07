package org.big.commom;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.big.dto.BoardFileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUtils {
	public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) {
	    if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {
	        return null; // 업로드 요청 자체가 없는 경우
	    }

	    List<BoardFileDto> fileList = new ArrayList<>();
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
	    ZonedDateTime current = ZonedDateTime.now();
	    String path = "C:/eclipse/eclipse-workspace/board/src/main/resources/file/images" + current.format(format);
	    File file = new File(path);

	    if (!file.exists()) {
	        file.mkdirs();
	    }

	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    String newFileName, orignalFileExtension, contentType;

	    while (iterator.hasNext()) {
	        List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
	        for (MultipartFile multipartFile : list) {
	            if (!multipartFile.isEmpty()) {
	                contentType = multipartFile.getContentType();

	                if (ObjectUtils.isEmpty(contentType)) {
	                    break;
	                } else {
	                    if (contentType.contains("image/jpeg")) {
	                        orignalFileExtension = ".jpg";
	                    } else if (contentType.contains("image/png")) {
	                        orignalFileExtension = ".png";
	                    } else if (contentType.contains("image/gif")) {
	                        orignalFileExtension = ".gif";
	                    } else {
	                        break;
	                    }
	                }

	                newFileName = Long.toString(System.nanoTime()) + orignalFileExtension;
	                BoardFileDto boardFile = new BoardFileDto();
	                boardFile.setBoardIdx(boardIdx);
	                boardFile.setFileSize(multipartFile.getSize());
	                boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
	                boardFile.setStoredFilePath(path + "/" + newFileName);
	                fileList.add(boardFile);

	                file = new File(path + "/" + newFileName);
	                try {
	                    multipartFile.transferTo(file);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    // 유효한 파일이 하나도 없으면 null 반환
	    return fileList.isEmpty() ? null : fileList;
	}

}
