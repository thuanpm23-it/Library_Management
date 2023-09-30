package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Member;
import com.example.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<Member> getAllMember() {
		// TODO Auto-generated method stub
		return memberRepository.findAll();
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		memberRepository.save(member);
	}

	@Override
	public Member editMember(Integer id) {
		// TODO Auto-generated method stub
		return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Member Id: " + id));
	}

	@Override
	public void deleteMember(Integer id) {
		// TODO Auto-generated method stub
		memberRepository.deleteById(id);
	}

}
