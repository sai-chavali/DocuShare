package ai.typeface.documentShare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveAccessDTO {
    private Long documentId;
    private boolean isPublic;
    private List<UserAccess> userAccessList;
    private Timestamp expirationTime;
}
