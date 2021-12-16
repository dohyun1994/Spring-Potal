package co.micol.potal.member.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.micol.potal.member.service.MemberService;
import co.micol.potal.member.service.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberDao;	// DAO 자동주입
	
	@Autowired
	ServletContext servletContext;		// 실행되는 서버의 루트 패스를 사용하기 위해
	
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
	
	@RequestMapping("/memberJoinForm.do")
	public String memberJoinForm() {
		return "member/memberJoinForm";
	}
	
	@PostMapping("/idCheck.do")
	@ResponseBody		// ajax 리턴을 위해 사용하는 어노테이션	// 호출한 페이지에 페이지 변환없이 결과를 던지겠다.
	public String idCheck(@RequestParam("id") String id) {
		boolean b = memberDao.memberIdCheck(id);
		if(b) {
			return "0";	// 존재할 때	
		} else {
			return "1";	// 존재하지 않을 때
		}
	}
	
	@PostMapping("/memberJoin.do")
	public String memberJoin(MemberVO vo, MultipartFile file, Model model) {
		String upload = servletContext.getRealPath("resources");   		// 배포시 사용할 파일저장공간.
		upload = upload + "//fileUpload//";
		String sourceFileName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();		// 서버 저장 파일명 충돌방지를 위해 알리아스명을 사용(UUID)
		String targetFileName = uuid + sourceFileName.substring(sourceFileName.lastIndexOf("."));		// .부터 ~~.jsp를 붙여서 출력.
		
		// sourceFileName이 비어있지 않다면,
		if(!sourceFileName.isEmpty()) {
			try {
				file.transferTo(new File(upload, targetFileName));	// 저장할 공간과 파일명 전달
				vo.setImgFile(targetFileName);
				vo.setPimgFile(targetFileName);
				int result = memberDao.memberInsert(vo);
				if(result == 0) {
					model.addAttribute("message", "회원가입이 실패 했습니다.<br>다시 가입해주세요.");
					
				} else {
					model.addAttribute("message", "축하합니다. <br>회원가입이 성공 했습니다.<br> 로그인 후 이용가능합니다.");
					
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
	         int result = memberDao.memberInsert(vo);
	         if(result != 0) {
	            model.addAttribute("message","<br>회원가입이 성공 했습니다.<br> 로그인 후 이용가능 합니다.");
	         }else {
	            model.addAttribute("message","회원가입이 실패 했습니다.,<br>다시 가입해주세요.");
	         }
	      }

		return "member/memberJoin";
	}
	
	@RequestMapping("/memberInfo.do")		// 호출명, 메서드명, 돌려줄 jsp페이지명 동일하게 만들어준다. 그래야 편하다.
	public String memberInfo(MemberVO vo, Model model, HttpSession session) {
		
		vo.setId((String) session.getAttribute("id"));
		model.addAttribute("member", memberDao.memberSelect(vo));
		
		return "/member/memberInfo";
	}
	
	@RequestMapping("ajaxMemberList.do")
	@ResponseBody
	public List<MemberVO> ajaxMemberList() {
		return memberDao.memberSelectList();
	}
		
}
