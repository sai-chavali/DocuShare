package ai.typeface.documentShare.repository.entities;

import ai.typeface.documentShare.domain.Document;
import ai.typeface.documentShare.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends BaseRepository<Document, Long> {
    @Query(value = "select d from Document d where d.uploadedBy=:email order by d.uploadedOn desc")
    List<Document> findByEmail(@Param("email") String email);
}
