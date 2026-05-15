package com.itams.web;

import com.itams.Borrow.Borrowing;
import com.itams.Borrow.BorrowingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {

    private final BorrowingService service;

    public BorrowingController(BorrowingService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("borrowings", service.getAll());
        return "borrowings/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("borrowing", new Borrowing());
        return "borrowings/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Borrowing borrowing, RedirectAttributes ra) {
        try {
            service.save(borrowing);
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/borrowings/create";
        }
        return "redirect:/borrowings";
    }
}
