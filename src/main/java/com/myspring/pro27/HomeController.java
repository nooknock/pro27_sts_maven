package com.myspring.pro27;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;//A Locale object represents a specific geographical, political,or cultural region.

import javax.servlet.http.HttpServletResponse;
//SLF4J(Simple Logging Facade for Java)�� �ܼ��� �ۻ�� ������ �������� �ϴ� �ڹ� �α� API�� �����Ѵ�.
//�ۻ��� "�ǹ��� ����"�� �ǹ�
//�ۻ��� Ŭ���� ���̺귯�� ���� � ����Ʈ������ �ٸ� Ŀ�ٶ� �ڵ� �κп� ���� ����ȭ�� �������̽��� �����ϴ� ��ü�̴�.
import org.slf4j.Logger;//
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletResponse response) {
		
		logger.info("Welcome home! The client locale is {}.", locale);//INFO : com.myspring.pro27.HomeController - Welcome home! The client locale is �̺κ��� ��������->ko_KR.
		
		
		response.setContentType("text/html;charset=utf-8");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);//2023�� 3�� 14�� ���� 11�� 5�� 56�� KST
		System.out.println(formattedDate);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
