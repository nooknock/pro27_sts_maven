package com.myspring.pro27.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro27.member.service.MemberService;
import com.myspring.pro27.member.vo.MemberVO;

@Controller("memberController")
@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController {

	/*
	 * Marks a constructor, field, setter method, or config method as to be
	 * autowired by Spring's dependency injection facilities. 생성자, 필드, 셋터 메서드, 메서드
	 * 설정에 의존성 주입함
	 */
	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberVO memberVO;
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	/*
	 * public void setMemberService(MemberService memberService) {
	 * this.memberService = memberService; } @Autowired가 대체
	 */

	@Override
	@RequestMapping(value = "/member/listMembers.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		String viewName = getViewName(request);
		String viewName=(String)request.getAttribute("viewName");
//		System.out.println(viewName+"||||");
		logger.info("info 레벨: viewName" + viewName);
		logger.debug("debug 레벨: viewName" + viewName);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
//		System.out.println("asd"+mav);
		return mav;
	}

	// A specialization of the Model interface that controllers can use to select
	// attributes for a redirect scenario. Since the intent of adding redirect
	// attributes is very explicit -- i.e. to be used for a redirect URL, attribute
	// values may be formatted as Strings and stored that way to make them eligible
	// to be appended to the query string or expanded as URI variables in
	// org.springframework.web.servlet.view.RedirectView.
	@Override
	@RequestMapping(value = "/member/login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/member/listMembers.do");
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/addMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/removeMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	
	@RequestMapping(value = { "/member/loginForm.do", "/member/memberForm.do" }, method = RequestMethod.GET)
//	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
		
		String viewName=(String)request.getAttribute("viewName");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		logger.info("info 레벨: viewName" + viewName);
		return mav;
	}

	/*
	 * @RequestMapping(value = "/member/*Form.do", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public ModelAndView form(@RequestParam(value =
	 * "result", required = false) String result, HttpServletRequest request,
	 * HttpServletResponse response) throws Exception {
	 * 
	 * String viewName = (String) request.getAttribute("viewName");
	 * logger.debug("debug 레벨: viewName" + viewName); ModelAndView mav = new
	 * ModelAndView(viewName); mav.addObject("result", result);
	 * mav.setViewName(viewName); System.out.println("ㅇㅇ"); return mav; }
	 */

//	private String getViewName(HttpServletRequest request) throws Exception {
//
//		String contextPath = request.getContextPath();
//
//		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
//
//		System.out.println(uri + "ㅋ");
//
//		if (uri == null || uri.trim().equals("")) {
//			uri = request.getRequestURI();
//		}
//
//		System.out.println(uri + "ㅋㅋ");
//		int begin = 0;
//
//		if (!((contextPath == null) || ("".equals(contextPath)))) {
//			System.out.println(contextPath);
//			begin = contextPath.length();
//
//		}
//
//		int end;
//		if (uri.indexOf(";") != -1) {
//			end = uri.indexOf(";");
//		} else if (uri.indexOf("?") != -1) {
//			end = uri.indexOf("?");
//		} else {
//			end = uri.length();
//		}
//
//		String fileName = uri.substring(begin, end);// /test/memberInfo.do
//		System.out.println(fileName + "   확인");
//		if (fileName.indexOf(".") != -1) {
//			fileName = fileName.substring(0, fileName.lastIndexOf(".")); // /test/memberInfo
//		}
//		System.out.println(fileName + "   확인2");
////		System.out.println(fileName.lastIndexOf("/", 0));
////		System.out.println(fileName.lastIndexOf("/", 1));
//		if (fileName.indexOf("/") != -1) {
//			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
//		}
//		System.out.println(fileName);
//		return fileName;
//	}

}
