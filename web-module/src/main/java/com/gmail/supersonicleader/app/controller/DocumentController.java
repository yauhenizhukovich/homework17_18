package com.gmail.supersonicleader.app.controller;

import java.util.List;

import javax.validation.Valid;

import com.gmail.supersonicleader.service.DocumentService;
import com.gmail.supersonicleader.service.model.DocumentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {this.documentService = documentService;}

    @GetMapping("/documents")
    public String getDocuments(Model model) {
        List<DocumentDTO> documents = documentService.findAllDocuments();
        model.addAttribute("documents", documents);
        return "documents";
    }

    @GetMapping("/documents/add")
    public String addDocument(Model model) {
        model.addAttribute("document", new DocumentDTO());
        return "add_document";
    }

    @PostMapping("/documents/add")
    public String addDocument(
            @Valid @ModelAttribute(name = "document") DocumentDTO document,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("document", document);
            return "add_document";
        } else {
            documentService.addDocument(document);
            return "redirect:/documents";
        }
    }

    @GetMapping("/documents/{id}")
    public String getDocumentById(@PathVariable Long id, Model model) {
        DocumentDTO document = documentService.findById(id);
        model.addAttribute("document", document);
        return "document";
    }

    @GetMapping("/documents/{id}/delete")
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteById(id);
        return "redirect:/documents";
    }

}
