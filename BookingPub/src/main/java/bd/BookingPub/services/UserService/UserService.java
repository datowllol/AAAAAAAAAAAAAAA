package bd.BookingPub.services.UserService;

import bd.BookingPub.model.Admin;
import bd.BookingPub.model.AdminRoles;
import bd.BookingPub.repository.AdminRepository;
import bd.BookingPub.repository.AdminRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
public class UserService implements UserDetailsService, InterfaceUserService {

    @Autowired
    private AdminRolesRepository adminRolesRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin saveUser(Admin admin) {
        AdminRoles userRole = adminRolesRepository.findByName("ROLE_GODADMIN");
        admin.setAdminRoles(userRole);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin findByLogin(String login) {
        return adminRepository.findByLogin(login);    }

    @Override
    public Admin findByLoginAndPassword(String login, String password) {
        Admin userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public boolean isUnique(String login) {
        return adminRepository.findByLogin(login) == null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByLogin(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return admin;
    }
}
