package app.illl.xmsger.datasource.repository;

import app.illl.xmsger.datasource.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
}