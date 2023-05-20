package ai.typeface.documentShare.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="document_access")
public class DocumentAccess {
    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "documentLinkId", referencedColumnName = "id")
    private DocumentLink documentLinkId;

    private String email;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;
}
