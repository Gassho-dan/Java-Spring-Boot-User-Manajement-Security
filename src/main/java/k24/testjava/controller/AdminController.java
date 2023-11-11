package k24.testjava.controller;

import com.lowagie.text.DocumentException;
import k24.testjava.model.Member;
import k24.testjava.reporting.ExcelExporter;
import k24.testjava.reporting.PdfExporter;
import k24.testjava.service.AdminNotFoundException;
import k24.testjava.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {
    @Autowired private AdminService service;

    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }

    @GetMapping("/admin/member")
    public String showMemberList(Model model){
        List<Member> memberList = service.listAll();
        model.addAttribute("memberList", memberList);

        return "admin/admin_home";
    }

    @GetMapping("/admin/member/new")
    public String  showNewForm(Model model){
        model.addAttribute("member", new Member());
        model.addAttribute("pageTitle", "Add new User");
        return "admin/admin_members_form";
    }

    @PostMapping("/admin/member/save")
    public String saveMember(Member member, MultipartFile file, RedirectAttributes ra) {
        service.save(member);
        ra.addFlashAttribute("massage", "The member has been saved successfully.");
        return "redirect:/admin/admin_home";
    }

    @GetMapping("/admin/member/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Member member = service.get(id);
            model.addAttribute("member", member);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return  "members_form";
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("massage", "The user has been saved successfully.");
            return "redirect:/admin/admin_home";
        }
    }

    @GetMapping("/admin/member/delete/{id}")
    public String deleteMember(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("massage", "The user ID " + id + " has been deleted.");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("massage", e.getMessage());
        }
        return "redirect:/admin/admin_home";
    }

    @GetMapping("/admin/member/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=members_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Member> memberList = service.listAll();

        ExcelExporter excelExporter = new ExcelExporter(memberList);

        excelExporter.exportToEXCEL(response);
    }

    @GetMapping("/admin/member/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=members_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Member> memberList = service.listAll();

        PdfExporter exporter = new PdfExporter(memberList);
        exporter.exportToPDF(response);

    }
}
