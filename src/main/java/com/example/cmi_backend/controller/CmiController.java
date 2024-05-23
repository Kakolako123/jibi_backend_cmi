package com.example.cmi_backend.controller;

import com.example.cmi_backend.entity.Creditor;
import com.example.cmi_backend.entity.Form;
import com.example.cmi_backend.entity.Impaye;
import com.example.cmi_backend.service.CmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cmi")
public class CmiController {

    @Autowired
    private CmiService cmiService;

    @GetMapping("/creanciers")
    public List<Creditor> getAllCreanciers() {
        return cmiService.getAllCreanciers();
    }

    @GetMapping("/forms/{creanceId}")
    public Form getFormByCreanceId(@PathVariable Long creanceId) {
        return cmiService.getFormByCreanceId(creanceId);
    }

    @GetMapping("/impayes/{creanceId}")
    public List<Impaye> getImpayesByCreanceId(@PathVariable Long creanceId) {
        return cmiService.getImpayesByCreanceId(creanceId);
    }

    @PostMapping("/confirmePayer")
    public boolean confirmePayer(@RequestParam Long impayeId, @RequestParam String phoneNumber) {
        return cmiService.confirmePayer(impayeId, phoneNumber);
    }
}
