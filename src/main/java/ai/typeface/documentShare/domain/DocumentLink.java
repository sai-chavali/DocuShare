package ai.typeface.documentShare.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="document_link")
public class DocumentLink {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private Timestamp expirationTime;

    private boolean isPublicViewable;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documentId", referencedColumnName = "id")
    private Document documentId;

    private Timestamp createdOn;
}
