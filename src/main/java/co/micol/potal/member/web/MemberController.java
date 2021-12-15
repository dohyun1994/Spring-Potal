package co.micol.potal.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.micol.potal.member.service.MemberService;
import co.micol.potal.member.service.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberDao;	// DAO 자동주입
	
	@RequestMapping("/loginForm.do")			
	public String loginForm() {
		
		return "/member/loginForm";		// 폼 호출
	}

	@PostMapping("/memberLogin.do")
	public String memberLogin(MemberVO vo, Model model, HttpSession session) {
		vo = memberDao.memberSelect(vo);	// db에서 조회한다. 아이디 패스워드로 보내서 결과를 vo 객체에 받음
		if(vo != null) {
			session.setAttribute("id", vo.getId());		// vo 객체가 null이 아니면 id, name, author 값을 세션에 저장.
			session.setAttribute("name", vo.getName());
			session.setAttribute("author", vo.getAuthor());
			model.addAttribute("message", "님 환영합니다.");
		} else {				
			model.addAttribute("message", "아이디 또는 패스워드가 틀립니다.");		// id 또는 password가 틀렸을때.
		}
		return "member/memberLoginResult";		// 성공했을때 돌려줄 페이지
	}
	
	@RequestMapping("/memberLogout.do")		// 로그아웃은 세션을 지워줌으로 로그아웃이 이루어진다.
	public String memberLogout(HttpSession session, Model model) {
		String name = (String)session.getAttribute("name");		// 세션에 보관된 이름 가져오기
		model.addAttribute("message", name + "님 정상적으로 로그아웃 되었습니다.");
		session.invalidate();		// 세션을 완전히 삭제한다.
		
		return "member/memberLoginResult";
	}
	
}
