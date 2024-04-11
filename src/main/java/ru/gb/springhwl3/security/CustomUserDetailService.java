package ru.gb.springhwl3.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springhwl3.entity.Reader;
import ru.gb.springhwl3.repository.ReaderRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final ReaderRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ищем " + username);
        Reader reader = repository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("Пользователь " + username + " не найден"));
        System.out.println("нашли " + reader);
        return new User(reader.getLogin(), reader.getPassword(), List.of(
                new SimpleGrantedAuthority(reader.getRole())
        ));
    }
}
