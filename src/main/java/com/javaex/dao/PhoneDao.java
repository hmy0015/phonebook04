package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	@Autowired
	private SqlSession sqlSession;
	

	// 리스트(검색안할때)
	public List<PersonVo> getPersonList() {
		return getPersonList("");
	}

	// 리스트(검색할때)
	public List<PersonVo> getPersonList(String keword) {
		List<PersonVo> personList = sqlSession.selectList("phonebook.getList");
		
		return personList;
	}

	// 등록
	public void personInsert(PersonVo personVo) {
		sqlSession.insert("phonebook.insert", personVo);
	}

	// 등록02 (map 방식)
	public void personInsert2(String name, String hp, String company) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("name", name);
		pMap.put("hp", hp);
		pMap.put("company", company);
				
		sqlSession.insert("phonebook.insert2", pMap);
	}
	
	// 수정 할 사용자 정보 가져오기
	public PersonVo getPerson(int personId) {
		PersonVo personVo = sqlSession.selectOne("phonebook.getPerson", personId);
		
		return personVo;
	}
	
	// 수정
	public void personUpdate(PersonVo personVo) {
		sqlSession.update("phonebook.update", personVo);
	}

	// 사람 삭제
	public void personDelete(int personId) {
		sqlSession.delete("phonebook.delete", personId);
	}
}