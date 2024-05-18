package com.example.jibi.controller;

import com.example.jibi.service.FormService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/{debtCode}")
    public Map<String, Object> getFormByDebtCode(@PathVariable String debtCode) {
        return formService.getFormByDebtCode(debtCode);
    }
}
