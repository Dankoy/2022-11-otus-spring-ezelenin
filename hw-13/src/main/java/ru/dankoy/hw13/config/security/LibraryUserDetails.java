package ru.dankoy.hw13.config.security;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dankoy.hw13.core.domain.User;

/*
 Декоратор, имплементирующий интерфейс UserDetails. Сделан,
  что бы доменный класс юзера был никак не связан со спрингом.
 */
@RequiredArgsConstructor
public class LibraryUserDetails implements UserDetails {

  private final transient User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    var roles = user.getRoles();

    return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
        .collect(Collectors.toSet());
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return user.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return user.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }
}
