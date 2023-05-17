package ru.dankoy.hw19.core.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Document("users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  private String id;

  @Field(name = "username")
  private String username;

  @Setter
  @Field(name = "password")
  private String password;

  @Field(name = "enabled")
  private boolean enabled;

  @Field(name = "account_non_locked")
  private boolean accountNonLocked;

  @Field(name = "account_non_expired")
  private boolean accountNonExpired;

  @Field(name = "credentials_non_expired")
  private boolean credentialsNonExpired;

  @Getter
  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  private Set<UserRole> roles = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
        .collect(Collectors.toSet());
  }

  public void addRole(UserRole role) {

    roles.add(role);

  }
}
