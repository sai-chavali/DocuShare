package ai.typeface.documentShare.service.implemenatation;

import ai.typeface.documentShare.domain.Document;
import ai.typeface.documentShare.domain.DocumentAccess;
import ai.typeface.documentShare.domain.DocumentLink;
import ai.typeface.documentShare.exceptions.*;
import ai.typeface.documentShare.models.UserAccess;
import ai.typeface.documentShare.repository.entities.DocumentAccessRepository;
import ai.typeface.documentShare.repository.entities.DocumentLinkRepository;
import ai.typeface.documentShare.repository.entities.DocumentRepository;
import ai.typeface.documentShare.service.AzureBlobService;
import ai.typeface.documentShare.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentAccessRepository documentAccessRepository;

    @Autowired
    private DocumentLinkRepository documentLinkRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private AzureBlobService azureBlobService;

    @Override
    public List<Document> getUserCreatedDocumentDetails(String email){
        return documentRepository.findByEmail(email);
    }

    @Override
    public Document saveDocument(MultipartFile file, String email) {
        try {
            azureBlobService.upload(file);
        } catch (Exception ex) {
            throw new FileUploadFailureException();
        }
        Document document = Document.builder().uploadedBy(email).storageLocation("Azure Blob").fileName(file.getOriginalFilename()).uploadedOn(Timestamp.from(Instant.now())).build();
        return documentRepository.save(document);
    }

    public String getDocument(String id, String email) {
        List<DocumentLink> documentLink = documentLinkRepository.findValidLinkById(id);
        if (!documentLink.isEmpty()) {
            DocumentLink docLink = documentLink.get(0);
            if (!docLink.isPublicViewable()) {
                int documentAccess = documentAccessRepository.findByIdAndEmail(id, email);
                if (documentAccess == 0)
                    throw new DocumentAccessException("User doesn't have access to the mentioned document");
            }
            return Base64.getEncoder().encodeToString(azureBlobService.getFile(docLink.getDocumentId().getFileName()));
        } else
            throw new DocumentLinkExpiredException();
    }

    @Override
    public String saveDocumentAccess(Long documentId, List<UserAccess> userAccessList, Timestamp expirationTime, boolean isPublic) {
        if(expirationTime.toInstant().isAfter(Instant.now())){
            Optional<Document> document = documentRepository.findById(documentId);
            if(document.isPresent()) {
                DocumentLink documentLink = DocumentLink.builder().documentId(document.get()).isPublicViewable(isPublic).expirationTime(expirationTime).createdOn(Timestamp.from(Instant.now())).build();
                documentLinkRepository.save(documentLink);
                List<DocumentAccess> documentAccessList = new ArrayList<>();
                userAccessList.forEach(userAccess ->
                        documentAccessList.add(DocumentAccess.builder().documentLinkId(documentLink).email(userAccess.getEmail()).accessLevel(userAccess.getAccessLevel()).build()));
                documentAccessRepository.saveAll(documentAccessList);
                return documentLink.getId();
            } else {
                throw new DocumentNotFoundException(documentId);
            }
        } else{
            throw new BadInputException("Expiration Time cannot before current time"); // Create another exception
        }
    }
}
