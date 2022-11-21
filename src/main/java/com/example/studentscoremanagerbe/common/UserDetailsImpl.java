package com.example.studentscoremanagerbe.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Some javadoc. // OK
 *
 * @author Vuong  
 * @version Some javadoc.
 * @since 20/11/2022  
 * @serial Some javadoc.  
 * @deprecated Some javadoc.  
 */
public class UserDetailsImpl implements UserDetails {

  private final int userId;

  private final String username;

  @JsonIgnore
  private final String password;

  private final boolean isActive;

  private final Collection<? extends GrantedAuthority> authorities;

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022  
   * @serialData  
   * @deprecated Some javadoc.  
   */
  public UserDetailsImpl(int id, String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
    this.userId = id;
    this.username = username;
    this.password = password;
    this.isActive = true;
    this.authorities = authorities;
  }

  /**
   * Some javadoc. // OK
   *
   * @author Vuong
   * @since 20/11/2022
   * @serialData
   * @deprecated Some javadoc.
   */
  public static UserDetailsImpl build(int id, String username, String password, String role) {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"
                                                          + role.toUpperCase());
    List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();
    updatedAuthorities.add(authority);
    return new UserDetailsImpl(id, username, password, updatedAuthorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public int getUserId() {
    return userId;
  }


  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(userId, user.userId);
  }
}
