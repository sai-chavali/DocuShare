package ai.typeface.documentShare.config;

import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.azure.storage.blob.BlobContainerClient;

@Configuration
public class AzureBlobConfig {
    @Value("${azure.storage.connection.string}")
    private String connectionString;
    @Value("${azure.storage.container.name}")
    private String containerName;

    @Bean
    public BlobContainerClient blobContainerClient() {
        return new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();

    }
}
