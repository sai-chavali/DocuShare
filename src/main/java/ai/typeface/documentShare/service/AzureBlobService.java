package ai.typeface.documentShare.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AzureBlobService {
    public String upload(MultipartFile file) throws IOException;

    public List<String> listBlobs();

    public Boolean deleteBlob(String blobName);

    public default byte[] getFile(String blobName) {
        return new byte[0];
    }

}
