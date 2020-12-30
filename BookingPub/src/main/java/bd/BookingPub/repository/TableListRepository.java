package bd.BookingPub.repository;

import bd.BookingPub.model.TableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableListRepository extends JpaRepository<TableList, Integer> {
}
