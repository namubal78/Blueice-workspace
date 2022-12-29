package com.blueice.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 인터페이스 구현 => 미완성된 메소드를 구현해서 써야함 (오버라이딩)
public class RewardFileRenamePolicy implements FileRenamePolicy {

	// 기존의 파일을 전달받아서 파일명 수정받업 후에 수정된 파일 자체를 리턴
	@Override
	public File rename(File originFile) {
		
		// 원본 파일명
		String originName = originFile.getName();
		
		// 수정 파일명
		// => 파일 업로드 시간 + 5자리 랜덤값
		
		// 확장자 : 원본파일의 확장자 그대로
		
		// 파일이 업로드된 시간
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 5자리 랜덤값
		int ranNum = (int)(Math.random() * 90000) + 10000;
		
		// 원본 파일의 확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 결합
		String changeName = currentTime + ranNum + ext;
		
		// 원본파일(originFile) 을 수정된 파일명으로 적용시켜서 파일객체로 변환
		return new File(originFile.getParent(), changeName);
	}
}
