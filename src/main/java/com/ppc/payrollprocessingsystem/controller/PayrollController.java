package com.ppc.payrollprocessingsystem.controller;

import com.ppc.payrollprocessingsystem.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/files")
    public String uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        StringBuilder response = new StringBuilder();

        for (MultipartFile file : files) {
            try {
                payrollService.processFile(file.getInputStream());
                response.append("Successfully uploaded: ").append(file.getOriginalFilename()).append("\n");
            } catch (IOException e) {
                response.append("Failed to upload: ").append(file.getOriginalFilename()).append(" - ").append(e.getMessage()).append("\n");
            }
        }

        return response.toString();
    }
}
