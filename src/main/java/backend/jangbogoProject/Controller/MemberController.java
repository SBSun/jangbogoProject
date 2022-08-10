package backend.jangbogoProject.Controller;

import com.google.gson.JsonObject;
import backend.jangbogoProject.domain.Member;
import backend.jangbogoProject.dto.MemberDto;
import backend.jangbogoProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // http://localhost:8080/member/login
    @GetMapping("/member/login")
    public ModelAndView loginForm(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "exception", required = false) String exception,
                                  Model model) {
        ModelAndView mav = new ModelAndView("member/login");
        mav.addObject("error", error);
        mav.addObject("exception", exception);

        return mav;
    }

    // http://localhost:8080/member/register
    @GetMapping("/member/register")
    public ModelAndView registerForm(){
        ModelAndView mav = new ModelAndView("member/register");
        return mav;
    }

    // http://localhost:8080/member/register
    @PostMapping("/member/register")
    public ModelAndView create(MemberDto memberDto){

        memberService.save(memberDto.toEntity());
        //존재하는 이메일이면
        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }

    // http://localhost:8080/member/emailCheck
    @PostMapping("/member/emailCheck")
    @ResponseBody
    public Map<String, Object> emailCheck(@RequestParam("email") String email)
    {
        System.out.println("이메일 체크");
        // 존재하는 이메일이면 1, 아니면 0 반환
        int result = memberService.isCheckEmail(email);

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(result == 0)
        {
            System.out.println(email + " 사용 가능 이메일입니다.");
            resultMap.put("result", 0);
        }
        else
        {
            System.out.println(email + " 이미 존재하는 이메일입니다.");
            resultMap.put("result", 1);
        }

        return resultMap;
    }

    // http://localhost:8080/member/mypage
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/mypage")
    public ModelAndView mypageForm(Principal principal, Model model) {
        Optional<Member> member = memberService.findEmail(principal.getName());
        model.addAttribute("member", member);

        ModelAndView mav = new ModelAndView("member/mypage");
        return mav;
    }

    // http://localhost:8080/member/editInfo
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/editInfo")
    public ModelAndView editInfo(Principal principal, Model model){
        Optional<Member> member = memberService.findEmail(principal.getName());
        model.addAttribute("member", member);

        ModelAndView mav = new ModelAndView("member/edit-info");
        return mav;
    }

    // http://localhost:8080/member/favorite
    @GetMapping("/member/favorite")
    public ModelAndView favorite() {

        ModelAndView mav = new ModelAndView("member/favorite");
        return mav;
    }
}
