package ru.dankoy.hw12.core.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dankoy.hw12.config.security.LibraryUserDetails;
import ru.dankoy.hw12.core.repository.user.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    return new LibraryUserDetails(user);

  }
}
