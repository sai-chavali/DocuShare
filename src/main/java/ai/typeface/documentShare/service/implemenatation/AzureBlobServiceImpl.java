package ai.typeface.documentShare.service.implemenatation;

import ai.typeface.documentShare.service.AzureBlobService;
import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AzureBlobServiceImpl implements AzureBlobService {
    @Autowired
    private BlobContainerClient blobContainerClient;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
        blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);
        return multipartFile.getOriginalFilename();
    }

    @Override
    public byte[] getFile(String fileName) {
        BlobClient blob = blobContainerClient.getBlobClient(fileName);
        return blob.downloadContent().toBytes();
    }

    @Override
    public List<String> listBlobs() {
        PagedIterable<BlobItem> items = blobContainerClient.listBlobs();
        List<String> names = new ArrayList<>();
        for (BlobItem item : items) {
            names.add(item.getName());
        }
        return names;
    }

    @Override
    public Boolean deleteBlob(String blobName) {
        BlobClient blob = blobContainerClient.getBlobClient(blobName);
        blob.delete();
        return true;
    }
}
