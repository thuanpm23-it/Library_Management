package com.example.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Member;
import com.example.repository.MemberRepository;

@RestController
@RequestMapping(value = "/api/member")
public class MemberRestController {
	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("")
	public List<Member> getAllAuthors() {
		return memberRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable Integer id) {
		return memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Member Id: " + id));
	}
	
	@PostMapping("")
	public Member createMember(@RequestBody Member member) {
		return memberRepository.save(member);
	}
	
	@PutMapping("/{id}")
	public Member updateMember(@PathVariable Integer id, @RequestBody Member updatedMember) {
	    return memberRepository.findById(id).map(member -> {
	        member.setMemberName(updatedMember.getMemberName());
	        member.setMemberAddress(updatedMember.getMemberAddress());
	        member.setEmail(updatedMember.getEmail());
	        member.setPhoneNumber(updatedMember.getPhoneNumber());
	        member.setDateOfBirth(updatedMember.getDateOfBirth());
	        member.setRegistrationDate(updatedMember.getRegistrationDate());
	        return memberRepository.save(member);
	    }).orElseThrow(() -> new IllegalArgumentException("Invalid Member Id: " + id));
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable Integer id) {
	    try {
	        memberRepository.deleteById(id);
	        return ResponseEntity.ok("Delete Member Successfully!");
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Member!");
	    }
	}

}
