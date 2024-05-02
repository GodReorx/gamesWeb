package com.games.model.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private static final Player TESTINGPLAYER = Player.builder()
            .email("testing@testing.com")
            .password("12345")
            .nickname("Dummy")
            .registrationDate(new Date())
            .id(666)
            .role(Role.USER)
            .build();


    @DisplayName("Testing to extract and compare information")
    @Test
    public void test1() {
        Player outcomePlayer = new Player(666,"testing@testing.com","12345","Dummy",new Date(),Role.USER);
    }

    @Test
    public void getUsername() {

    }

    @Test
    public void getPassword() {
    }

    @Test
    void isAccountNonExpired() {
    }

    @Test
    void isAccountNonLocked() {
    }

    @Test
    void isCredentialsNonExpired() {
    }

    @Test
    void isEnabled() {
    }

    @Test
    void onCreate() {
    }

    @Test
    void getId() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void getNickname() {
    }

    @Test
    void getRegistrationDate() {
    }

    @Test
    void getRole() {
    }

    @Test
    void setId() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void setNickname() {
    }

    @Test
    void setRegistrationDate() {
    }

    @Test
    void setRole() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void builder() {
    }
}