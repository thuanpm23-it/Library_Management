package com.example.service;

import java.util.List;

import com.example.model.Member;

public interface MemberService {

	List<Member> getAllMember();

	void addMember(Member member);

	Member editMember(Integer id);

	void deleteMember(Integer id);

}
