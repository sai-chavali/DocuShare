package ai.typeface.documentShare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
