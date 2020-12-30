package bd.BookingPub.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_GODADMIN,
    ROLE_SIMPADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
