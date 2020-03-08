package com.gmail.supersonicleader.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gmail.supersonicleader.repository.DocumentRepository;
import com.gmail.supersonicleader.repository.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl extends GeneralRepositoryImpl<Document> implements DocumentRepository {

    @Override
    public List findAll(Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, unique_number, description FROM document"
                )
        ) {
            List<Document> documents = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Document document = getDocument(rs);
                    documents.add(document);
                }
                return documents;
            }
        }
    }

    @Override
    public Document add(Connection connection, Document document) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO document (unique_number, description) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            UUID uniqueNumberUUID = document.getUniqueNumber();
            String uniqueNumber = String.valueOf(uniqueNumberUUID);
            statement.setString(1, uniqueNumber);
            statement.setString(2, document.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating document failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    document.setId(id);
                    return document;
                } else {
                    throw new SQLException("Creating document failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public Document findById(Connection connection, Long id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, unique_number, description FROM document WHERE id = ?"
                )
        ) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getDocument(rs);
                }
            }
            return null;
        }
    }

    @Override
    public void deleteById(Connection connection, Long id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM document WHERE id = ?"
                )
        ) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting document failed, no rows affected.");
            }
        }
    }

    private Document getDocument(ResultSet rs) throws SQLException {
        Document document = new Document();
        Long id = rs.getLong("id");
        document.setId(id);
        String uniqueNumber = rs.getString("unique_number");
        document.setUniqueNumber(UUID.fromString(uniqueNumber));
        String description = rs.getString("description");
        document.setDescription(description);
        return document;
    }

}
