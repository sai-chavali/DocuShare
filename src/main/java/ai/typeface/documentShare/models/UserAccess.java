package ai.typeface.documentShare.models;

import ai.typeface.documentShare.domain.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccess implements Serializable {
    private String email;
    private AccessLevel accessLevel;
}
