package bd.BookingPub.services.UserService;

import bd.BookingPub.model.Admin;
import java.util.List;


public interface InterfaceUserService {
   Admin saveUser (Admin admin);
   Admin findByLogin(String string);
    Admin findByLoginAndPassword(String login, String password);
    List<Admin> getAll();
    boolean isUnique(String login);
}
