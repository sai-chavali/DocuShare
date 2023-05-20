package ai.typeface.documentShare.repository.entities;

import ai.typeface.documentShare.domain.DocumentLink;
import ai.typeface.documentShare.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentLinkRepository extends BaseRepository<DocumentLink, String> {
    @Query(value = "select dl from DocumentLink dl where dl.id=:id and dl.expirationTime>CURRENT_TIMESTAMP")
    List<DocumentLink> findValidLinkById(@Param("id") String id);
}
