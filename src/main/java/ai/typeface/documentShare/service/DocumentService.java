package ai.typeface.documentShare.service;

import ai.typeface.documentShare.domain.Document;
import ai.typeface.documentShare.models.UserAccess;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface DocumentService {
    public List<Document> getUserCreatedDocumentDetails(String email);
    public boolean saveDocument(MultipartFile file, String email);

    public String saveDocumentAccess(Long documentId, List<UserAccess> userAccessList, Timestamp expirationTime, boolean isPublic);

    public String getDocument(String id, String email);
}
