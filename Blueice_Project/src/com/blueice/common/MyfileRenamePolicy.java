package com.blueice.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyfileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {

		// 원본 파일명
		String originName = originFile.getName();
		
		// 수정파일명
		// -> 파일업로드시간(년월일시분초) + 3자리 랜덤값
		// -> 최대한 이름이 겹치지 않게
		
		// 확장자 == 원본파일의 확장자 그대로
		
		// 1. 파일이 업로드된 시간(년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 2. 3자리 랜덤값
		int ranNum = (int)(Math.random() * 900) + 100;
		
		// 3. 원본 파일 확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 결합
		String changeName = currentTime + ranNum + ext;		
		
		// 원본 파일(originFile)을 수정된 파일명으로 적용시켜서 파일 객체로 변환
		return new File(originFile.getParent(), changeName);
	}

}
