package ai.typeface.documentShare.repository.entities;

import ai.typeface.documentShare.domain.DocumentAccess;
import ai.typeface.documentShare.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DocumentAccessRepository extends BaseRepository<DocumentAccess, String> {
    @Query(value = "select count(da.documentLinkId) from DocumentAccess da where da.documentLinkId.id=:id and da.email=:email and da.accessLevel in ('READ','WRITE','COMMENT','OWNER')")
    int findByIdAndEmail(@Param("id") String id, @Param("email") String email);
}
