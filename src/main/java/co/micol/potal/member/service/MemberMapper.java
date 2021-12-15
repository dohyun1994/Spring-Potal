package co.micol.potal.member.service;

import java.util.List;

public interface MemberMapper {
	List<MemberVO> memberSelectList(); //전체리스트
	MemberVO memberSelect(MemberVO vo);//한명의 정보 또는 로그인 체크
	int memberInsert(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	boolean memberIdCheck(String id); // 아이디 중복 체크
	
}