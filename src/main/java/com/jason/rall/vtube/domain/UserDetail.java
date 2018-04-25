package com.jason.rall.vtube.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jrall
 */
@Document(collection = "users")
public class UserDetail implements UserDetails {

    @Indexed(name = "username", unique = true)
    private String username;
    private String password; //todo: salt using libbcrypt
    private List<GrantedAuthority> grantedAuthorities;

    public UserDetail(){}

    public UserDetail(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetail(String username,String password,String[] authorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
