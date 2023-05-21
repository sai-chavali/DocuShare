package ai.typeface.documentShare.controller;

import ai.typeface.documentShare.domain.Document;
import ai.typeface.documentShare.models.SaveAccessDTO;
import ai.typeface.documentShare.service.DocumentService;
import ai.typeface.documentShare.utils.AuthClaims;
import ai.typeface.documentShare.utils.AuthUtils;
import com.azure.spring.cloud.autoconfigure.aad.filter.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/private"})
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping(path = "/upload")
    public Document uploadDocument(@RequestParam("doc") MultipartFile file, Authentication authentication) {
        Map<String,String> userDetails = AuthUtils.filterClaims((UserPrincipal) authentication.getPrincipal());
        String email = userDetails.get(AuthClaims.EMAIL.getLabel());
        return documentService.saveDocument(file, email);
    }

    @PostMapping(path = "/share")
    public String saveAccessAndShareLink(@RequestBody @NotNull SaveAccessDTO saveAccessDTO){
        return documentService.saveDocumentAccess(saveAccessDTO.getDocumentId(), saveAccessDTO.getUserAccessList(), saveAccessDTO.getExpirationTime(), saveAccessDTO.isPublicViewable());
    }

    @GetMapping(path = "/documentDetails")
    public List<Document> getDocumentDetails(Authentication authentication) {

        Map<String,String> userDetails = AuthUtils.filterClaims((UserPrincipal) authentication.getPrincipal());
        String email = userDetails.get(AuthClaims.EMAIL.getLabel());
        return documentService.getUserCreatedDocumentDetails(email);
    }

    @GetMapping(path = "/doc/{name}")
    public String getDocumentByName(@PathVariable("name") String id, Authentication authentication) {
        Map<String,String> userDetails = AuthUtils.filterClaims((UserPrincipal) authentication.getPrincipal());
        String email = userDetails.get(AuthClaims.EMAIL.getLabel());
        return documentService.getDocument(id, email);
    }
}
