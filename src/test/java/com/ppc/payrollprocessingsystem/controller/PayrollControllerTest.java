package com.ppc.payrollprocessingsystem.controller;

import com.ppc.payrollprocessingsystem.service.PayrollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PayrollControllerTest {

    @Mock
    private PayrollService payrollService;

    @InjectMocks
    private PayrollController payrollController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadFiles_withSingleFile() throws Exception {
        String fileContent = "1, E001, John, Doe, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        MultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", fileContent.getBytes());

        doNothing().when(payrollService).processFile(any(InputStream.class));

        String response = payrollController.uploadFiles(List.of(file));
        assertTrue(response.contains("Successfully uploaded: test.csv"));

        verify(payrollService, times(1)).processFile(any(InputStream.class));
    }

    @Test
    void testUploadFiles_withMultipleFiles() throws Exception {
        String fileContent1 = "1, E001, John, Doe, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        String fileContent2 = "2, E002, Jane, Smith, Designer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        MultipartFile file1 = new MockMultipartFile("file", "test1.csv", "text/csv", fileContent1.getBytes());
        MultipartFile file2 = new MockMultipartFile("file", "test2.txt", "text/plain", fileContent2.getBytes());

        doNothing().when(payrollService).processFile(any(InputStream.class));

        String response = payrollController.uploadFiles(List.of(file1, file2));
        assertTrue(response.contains("Successfully uploaded: test1.csv"));
        assertTrue(response.contains("Successfully uploaded: test2.txt"));

        verify(payrollService, times(2)).processFile(any(InputStream.class));
    }

    @Test
    void testUploadFiles_withIOException() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("test.csv");
        when(file.getInputStream()).thenThrow(new IOException("Test IOException"));

        String response = payrollController.uploadFiles(List.of(file));
        assertTrue(response.contains("Failed to upload: test.csv - Test IOException"));

        verify(payrollService, never()).processFile(any(InputStream.class));
    }
}