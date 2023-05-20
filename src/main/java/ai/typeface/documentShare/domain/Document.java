package ai.typeface.documentShare.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storageLocation;

    private Timestamp uploadedOn;

    private String uploadedBy;

    private String fileName;
}
