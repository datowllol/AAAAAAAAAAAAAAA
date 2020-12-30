package bd.BookingPub.repository;

import bd.BookingPub.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Ordering, Integer> {
}
