package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Member;

import com.example.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/list")
	public String getAllMember(Model model) {
		List<Member> member = memberService.getAllMember();
		model.addAttribute("member", member);
		return "member/list";
	}

	@GetMapping("/add")
	public String addMember(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "member/add";
	}

	@PostMapping("/save")
	public String saveMember(@ModelAttribute("member")@Validated Member member,BindingResult result) {
		if(result.hasErrors()) {
			return "member/add";
		}else {
			memberService.addMember(member);
			return "redirect:/member/list";
		}
	}

	@GetMapping("/edit/{id}")
	public String editMember(@PathVariable("id") Integer id, Model model) {
		Member member = memberService.editMember(id);
		model.addAttribute("member", member);
		return "member/edit";
	}


	@GetMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") Integer id) {
		memberService.deleteMember(id);
		return "redirect:/member/list";
	}
}
