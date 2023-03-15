package com.myspring.pro27.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;//A factory for connections to the physical data source that this DataSource object represents.

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;//This class executes SQL queries or updates, initiating iteration over ResultSets and catching JDBC exceptions and translating them to the generic, more informative exception hierarchy defined in the org.springframework.dao package.
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.myspring.pro27.member.vo.MemberVO;

@Repository("mamberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public List selectAllMembers() throws DataAccessException {
		
		List<MemberVO> membersList=sqlSession.selectList("mapper.member.selectAllMemberList");
		
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws Exception {
		
		int result=sqlSession.insert("mapper.member.insertMember",memberVO);
		System.out.println(result+"�씤�꽕�듃");
		return result;
	}

	@Override
	public int deleteMember(String id) throws Exception {
		int result=sqlSession.insert("mapper.member.deleteMember",id);
		return result;
	}
	
	@Override
	public int updateMember(MemberVO memberVO) {
		int result=sqlSession.update("mapper.member.updateMember",memberVO);
		System.out.println(result+"�뀑�뀑");
		return result;
	}

	@Override
	public MemberVO findMember(String id) throws Exception {
		MemberVO memberVO=(MemberVO) sqlSession.selectList("mapper.member.findMember",id);
		return memberVO;
	}
	
	
	
}
