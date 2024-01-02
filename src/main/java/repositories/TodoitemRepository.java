package repositories;

import models.Todoitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoitemRepository extends JpaRepository<Todoitem,Long> {

}
