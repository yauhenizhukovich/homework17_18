package com.gmail.supersonicleader.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.gmail.supersonicleader.repository.ConnectionRepository;
import com.gmail.supersonicleader.repository.DocumentRepository;
import com.gmail.supersonicleader.repository.model.Document;
import com.gmail.supersonicleader.service.DocumentService;
import com.gmail.supersonicleader.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ConnectionRepository connectionRepository;
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(
            ConnectionRepository connectionRepository,
            DocumentRepository documentRepository
    ) {
        this.connectionRepository = connectionRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<DocumentDTO> findAllDocuments() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Document> documents = documentRepository.findAll(connection);
                List<DocumentDTO> documentsDTO = documents.stream().
                        map(this::convertDatabaseObjectToDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return documentsDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public void addDocument(DocumentDTO documentDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                UUID uniqueNumber = UUID.randomUUID();
                documentDTO.setUniqueNumber(uniqueNumber);
                Document document = convertDTOToDatabaseObject(documentDTO);
                documentRepository.add(connection, document);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public DocumentDTO findById(Long id) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Document document = documentRepository.findById(connection, id);
                DocumentDTO documentDTO = convertDatabaseObjectToDTO(document);
                connection.commit();
                return documentDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                documentRepository.deleteById(connection, id);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Document convertDTOToDatabaseObject(DocumentDTO documentDTO) {
        Document document = new Document();
        UUID uniqueNumber = documentDTO.getUniqueNumber();
        document.setUniqueNumber(uniqueNumber);
        String description = documentDTO.getDescription();
        document.setDescription(description);
        return document;
    }

    private DocumentDTO convertDatabaseObjectToDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        Long id = document.getId();
        documentDTO.setId(id);
        UUID uniqueNumber = document.getUniqueNumber();
        documentDTO.setUniqueNumber(uniqueNumber);
        String description = document.getDescription();
        documentDTO.setDescription(description);
        return documentDTO;
    }

}
