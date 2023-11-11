package k24.testjava.controller;

import k24.testjava.model.Member;
import k24.testjava.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

    @Autowired
    private AdminService service;

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/member/login")
    public String viewMemberLoginPage() {
        return "member/member_login";
    }

    @GetMapping("/register")
    public String  showRegister(){
        return "member_form";
    }

    @GetMapping("/member/home")
    public String  showHome(Model model){
        model.addAttribute("member", new Member());
        return "member/member_home";
    }

    @PostMapping("/register/save")
    public String saveRegister(Member member, MultipartFile file, RedirectAttributes ra) {
        service.save(member);
        ra.addFlashAttribute("massage", "The member has been saved successfully.");
        return "redirect:/member_form_success";
    }
}
