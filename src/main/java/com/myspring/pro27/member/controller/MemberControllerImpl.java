package com.myspring.pro27.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro27.member.service.MemberService;
import com.myspring.pro27.member.vo.MemberVO;


@Controller("memberController")
public class MemberControllerImpl  implements MemberController {

	
	/*
	 * Marks a constructor, field, setter method, or config method as to be
	 * autowired by Spring's dependency injection facilities.
	 * �깮�꽦�옄, �븘�뱶, �뀑�꽣 硫붿꽌�뱶, 硫붿꽌�뱶 �꽕�젙�뿉 �쓽議댁꽦 二쇱엯�븿
	 */	
	@Autowired 
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;

	/*
	 * public void setMemberService(MemberService memberService) {
	 * this.memberService = memberService; } @Autowired媛� ��泥�
	 */

	@Override
	@RequestMapping(value = "/member/listMembers.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		System.out.println("asd"+mav);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/member/addMember.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		int result =0;
		result=memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/member/removeMember.do",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/*Form.do",method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = getViewName(request);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.setViewName(viewName);
		
		return mav;
	}
	
	
	
//	@Override
//	public ModelAndView modMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
//		
//		ModelAndView mav = new ModelAndView(viewName);
//
//		System.out.println("asdas"+mav);
//		return mav;
//		
//	}
	
	@Override
	@RequestMapping(value = "/member/updateMember.do",method = RequestMethod.GET)
	public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		
		int result =0;
		result=memberService.modMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		System.out.println("�뀋�뀋"+mav);
		return mav;
	}
	
	
	

	
	private String getViewName(HttpServletRequest request) throws Exception {

		String contextPath = request.getContextPath();

		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		
		System.out.println(uri);

		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		

		int begin = 0;

		if (!((contextPath == null) || ("".equals(contextPath)))) {
			System.out.println(contextPath);
			begin = contextPath.length();
			
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		

		String fileName = uri.substring(begin, end);// /test/memberInfo.do
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf(".")); // /test/memberInfo
		}
		if (fileName.indexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}

		return fileName;
	}


	

	

	
}
