package ai.typeface.documentShare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveAccessDTO implements Serializable {
    private Long documentId;

    private boolean publicViewable;

    private List<UserAccess> userAccessList;
    private Timestamp expirationTime;

    public void setExpirationTime(Timestamp expirationTime) {
        if (expirationTime == null ) {
            this.expirationTime = Timestamp.valueOf("9999-12-31 23:59:59.999999");
        } else {
            this.expirationTime = expirationTime;
        }
    }
}
