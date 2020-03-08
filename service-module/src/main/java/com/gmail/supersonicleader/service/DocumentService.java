package com.gmail.supersonicleader.service;

import java.util.List;

import com.gmail.supersonicleader.service.model.DocumentDTO;

public interface DocumentService {

    List<DocumentDTO> findAllDocuments();

    void addDocument(DocumentDTO documentDTO);

    DocumentDTO findById(Long id);

    void deleteById(Long id);

}
