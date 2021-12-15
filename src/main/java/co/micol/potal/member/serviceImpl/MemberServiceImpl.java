package co.micol.potal.member.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.micol.potal.member.service.MemberMapper;
import co.micol.potal.member.service.MemberService;
import co.micol.potal.member.service.MemberVO;

@Repository("memberDao")
public class MemberServiceImpl implements MemberService {
	
	// 의존주입  @AutoWired = 컨테이너에 있는놈을 자동으로 주입해달라.
	@Autowired
	MemberMapper map;
	

	@Override
	public List<MemberVO> memberSelectList() {
		
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		
		return map.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		
		return map.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		
		return map.memberDelete(vo);
	}

	@Override
	public boolean memberIdCheck(String id) {
		
		return map.memberIdCheck(id);
	}

}
