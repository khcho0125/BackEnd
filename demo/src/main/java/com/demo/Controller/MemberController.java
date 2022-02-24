package com.demo.Controller;

import com.demo.Domain.Member;
import com.demo.Dto.LoginDto;
import com.demo.Dto.MemberDto;
import com.demo.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    @GetMapping("/member/signup")
    public String signUpForm(Model model) {
        model.addAttribute("MemberDto", new MemberDto());
        return "/member/signupForm";
    }

    //     , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE // x-www-form-urlencoded 바꿀 때 @RequestBody 사용 불가
    @PostMapping(value = "/member/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.signUp(memberDto));
    }

    @GetMapping("/member/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("LoginDto", new LoginDto());
        return "/member/loginForm";
    }

    @GetMapping(value = "/member/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/member") //
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // 두가지 권한 모두 허용
    public ResponseEntity<Member> getMyUserInfo() {
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities().get());
    }

    @GetMapping("/member/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 호출 가능
    public ResponseEntity<Member> getUserInfo(@PathVariable String username) { // 유저 정보와 권한 정보를 리턴받는 API
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(username).get());
    }
}
