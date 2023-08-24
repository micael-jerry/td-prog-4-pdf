package com.spring.boot.employee.service.pdf;

import com.lowagie.text.DocumentException;
import com.spring.boot.employee.controller.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@Slf4j
@AllArgsConstructor
public class ExportPdfService {
    private TemplateEngine templateEngine;

    public ByteArrayInputStream exportEmployeePdf(EmployeeDto employeeDto) {
        Context context = new Context();
        context.setVariable("fileEmployee", employeeDto);
        String htmlContent = templateEngine.process("pdfEmployee", context);
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        }
        return byteArrayInputStream;
    }
}
