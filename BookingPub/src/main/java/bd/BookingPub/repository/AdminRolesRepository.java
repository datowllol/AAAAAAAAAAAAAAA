package bd.BookingPub.repository;

import bd.BookingPub.model.AdminRoles;
import bd.BookingPub.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRolesRepository extends JpaRepository<AdminRoles, Integer> {
    AdminRoles findByName(String name);
}
