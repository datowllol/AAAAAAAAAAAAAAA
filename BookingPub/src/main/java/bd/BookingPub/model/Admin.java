package bd.BookingPub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements UserDetails  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "roleId")
    private AdminRoles adminRoles;

    @JsonBackReference
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    public boolean isGODADMIN() {
        if(adminRoles.getName().equals("ROLE_GODADMIN"))
            return true;
        else
            return false;
    }

    public boolean isSIMPADMIN() {
        if(adminRoles.getName().equals("ROLE_SIMPADMIN"))
            return true;
        else
            return false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(adminRoles.getName().equals("ROLE_SIMPADMIN"))
            roles.add(Role.ROLE_GODADMIN);
        else
            roles.add(Role.ROLE_SIMPADMIN);
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
