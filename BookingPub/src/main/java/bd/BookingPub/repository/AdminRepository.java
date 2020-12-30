package bd.BookingPub.repository;

import bd.BookingPub.model.Admin;
import bd.BookingPub.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByLogin(String login);
}
