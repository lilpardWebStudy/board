package com.example.board.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.Member;
import com.example.board.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	MemberService service;
	
	@PostMapping("api/member/idCheck")
	public Map<String, Object> idCheck(@RequestParam(value = "id") String id){
		return service.idCheck(id);
	}
	
	@PostMapping(value =  "api/member", produces = "application/json; charset=UTF-8")
	public Map<String, Object> join(@RequestBody Member member){
		return service.addMember(member);
	}
	
	@PostMapping(value = "api/member/login", produces = "application/json; charset=UTF-8")
	public Map<String, Object> login(@RequestBody Member member, HttpServletRequest request, Model model){
		model.addAttribute("member", member);
		model.addAttribute("request", request);
		
		return service.loginMember(model);
	}
	
	@PutMapping(value = "api/member", produces = "application/json; charset=UTF-8")
	public Map<String, Object> modifyMember(@RequestBody Member member, HttpServletRequest request, Model model){
		model.addAttribute("member", member);
		model.addAttribute("request", request);
		
		return service.modifyMember(model);
	}
	
	@DeleteMapping(value = "api/member/{mno}")
	public Map<String, Object> deleteMember(@PathVariable (value = "mno") Long mno, HttpServletRequest request, Model model){
		model.addAttribute("mno", mno);
		model.addAttribute("request", request);
		
		return service.deleteMember(model);
	}
}
