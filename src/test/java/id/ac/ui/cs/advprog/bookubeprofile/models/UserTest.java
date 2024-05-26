package id.ac.ui.cs.advprog.bookubeprofile.models;

import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.bookubeprofile.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGetAuthorities() {
        User user = new User();
        user.setAuthorities(null);
        assertNull(user.getAuthorities());

        user.setAuthorities(null);
        assertNull(user.getAuthorities());
    }

    @Test
    public void testIsAccountNonLocked() {
        User user = new User();
        user.setAccountNonLocked(true);
        assertTrue(user.isAccountNonLocked());

        user.setAccountNonLocked(false);
        assertFalse(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        User user = new User();
        user.setCredentialsNonExpired(true);
        assertTrue(user.isCredentialsNonExpired());

        user.setCredentialsNonExpired(false);
        assertFalse(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        User user = new User();
        user.setEnabled(true);
        assertTrue(user.isEnabled());

        user.setEnabled(false);
        assertFalse(user.isEnabled());
    }

    @Test
    public void testGetId() {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testGetUsername() {
        User user = new User();
        String username = "john_doe";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User();
        String password = "password123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testGetEmail() {
        User user = new User();
        String email = "john.doe@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetRole() {
        User user = new User();
        String role = "ROLE_USER";
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    public void testIsAccountNonExpired() {
        User user = new User();
        user.setAccountNonExpired(true);
        assertTrue(user.isAccountNonExpired());

        user.setAccountNonExpired(false);
        assertFalse(user.isAccountNonExpired());
    }

    @Test
    public void testGetAuthoritiesAdmin() {
        User user = new User();
        user.setAuthorities(null);
        assertNull(user.getAuthorities());

        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
        user.setAuthorities(authorities);
        assertEquals(authorities, user.getAuthorities());
    }

    @Test
    public void testUserBuilder() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .role("ROLE_USER")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("ROLE_USER", user.getRole());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    public void testSetAndGetAuthorities() {
        User user = new User();
        Collection<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ADMIN"),
                new SimpleGrantedAuthority("USER")
        );

        user.setAuthorities(authorities);
        assertEquals(authorities, user.getAuthorities());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .role("ROLE_USER")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .role("ROLE_USER")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        assertEquals(user1.getUsername(), user2.getUsername());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getRole(), user2.getRole());
        assertEquals(user1.isAccountNonExpired(), user2.isAccountNonExpired());
        assertEquals(user1.isAccountNonLocked(), user2.isAccountNonLocked());
        assertEquals(user1.isCredentialsNonExpired(), user2.isCredentialsNonExpired());
        assertEquals(user1.isEnabled(), user2.isEnabled());
    }
}