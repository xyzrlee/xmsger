package app.illl.xmsger.datasource.repository;

import app.illl.xmsger.datasource.entity.AirData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirDataRepository extends CrudRepository<AirData, Integer> {
}