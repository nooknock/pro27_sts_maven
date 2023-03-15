package com.myspring.pro27.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.pro27.member.vo.MemberVO;

public interface MemberService {
	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO memberVO) throws Exception;
	public int removeMember(String id) throws Exception;
	public int modMember(MemberVO memberVO) throws Exception;
	public int findMember(MemberVO memberVO) throws Exception;

}
