package com.example.PokemonAPI.service;


import com.example.PokemonAPI.model.dto.Favorite;
import com.example.PokemonAPI.model.dto.User;
import com.example.PokemonAPI.model.util.Role;
import com.example.PokemonAPI.repository.FavoriteRepository;
import com.example.PokemonAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.example.PokemonAPI.repository.FavoriteSpecifications.idLike;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, FavoriteRepository favoriteRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<Favorite> findAllFavorites() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username);

        return favoriteRepository.findAll(where(idLike(Integer.toString(user.getId()))));
    }

    public User emptyUser() {
        return new User();
    }

    public void newUser(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);

        user.setPassword(passwordEncoder.encode(user.getPasswordInput()));
        user.setDateBirth(Date.valueOf(user.getDateBirthInput()));
        user.setDateCreated(new Date(System.currentTimeMillis()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void saveFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    public boolean favoriteExists(Favorite favorite) {
        return favoriteRepository.existsById(favorite.getId());
    }

    public User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username);
    }

    public Integer getTotalFavoritePokemonCount(Integer pokemonId) {
        return favoriteRepository.totalFavoritePokemonCount(pokemonId);
    }

    public String getEncryptedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String getNotNullField(User user) {
        if (user.getName() != null) {
            return "name";
        }

        if (user.getEmail() != null) {
            return "email";
        }

        if (user.getDateBirthInput() != null) {
            return "dateBirthInput";
        }

        if (user.getPasswordInput() != null) {
            return "passwordInput";
        }

        return "";
    }

    public Boolean isOnlyOneNotNullField(User user) {
        int n = 0;
        if (user.getName() != null) {
            n++;
        }

        if (user.getEmail() != null) {
            n++;
        }

        if (user.getDateBirthInput() != null) {
            n++;
        }

        if (user.getPasswordInput() != null) {
            n++;
        }

        return n == 1;
    }

    public void profileUpdate(User user, String field) {
        User userActual = getUser();

        switch (field) {
            case "name":
                userActual.setName(user.getName());
                userActual.setPasswordInput("123456");
                userActual.setDateBirthInput("1998-01-01");
                break;
            case "email":
                userActual.setEmail(user.getEmail());
                userActual.setPasswordInput("123456");
                userActual.setDateBirthInput("1998-01-01");
                break;
            case "dateBirthInput":
                userActual.setDateBirth(Date.valueOf(user.getDateBirthInput()));
                userActual.setPasswordInput("123456");
                userActual.setDateBirthInput("1998-01-01");
                break;
            case "passwordInput":
                userActual.setPassword(getEncryptedPassword(user.getPasswordInput()));
                userActual.setPasswordInput("123456");
                userActual.setDateBirthInput("1998-01-01");
                break;
        }

        updateUser(userActual);
    }

    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_ADMIN);
    }

    public boolean isUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_USER);
    }

    public boolean hasRole(String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario '" + username + "' no encontrado");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}