package com.nttdata.dockerized.postgresql.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructor_ShouldCreateEmptyObject() {
        // When
        User user = new User();

        // Then
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getActive());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        Boolean active = true;

        // When
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setActive(active);

        // Then
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(active, user.getActive());
    }

    @Test
    void settersAndGetters_WithNullValues_ShouldWorkCorrectly() {
        // Given
        User user = new User();

        // When
        user.setId(null);
        user.setName(null);
        user.setEmail(null);
        user.setActive(null);

        // Then
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getActive());
    }

    @Test
    void settersAndGetters_WithEmptyStrings_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        String emptyName = "";
        String emptyEmail = "";

        // When
        user.setName(emptyName);
        user.setEmail(emptyEmail);

        // Then
        assertEquals(emptyName, user.getName());
        assertEquals(emptyEmail, user.getEmail());
    }

    @Test
    void settersAndGetters_WithSpecialCharacters_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        String nameWithSpecialChars = "José María O'Connor-Smith";
        String emailWithSpecialChars = "josé.maría@example.com";

        // When
        user.setName(nameWithSpecialChars);
        user.setEmail(emailWithSpecialChars);

        // Then
        assertEquals(nameWithSpecialChars, user.getName());
        assertEquals(emailWithSpecialChars, user.getEmail());
    }

    @Test
    void settersAndGetters_WithLongStrings_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        String longName = "This is a very long name that contains many characters and should be handled properly by the user entity";
        String longEmail = "this.is.a.very.long.email.address.that.contains.many.characters.and.should.be.handled.properly@example.com";

        // When
        user.setName(longName);
        user.setEmail(longEmail);

        // Then
        assertEquals(longName, user.getName());
        assertEquals(longEmail, user.getEmail());
    }

    @Test
    void settersAndGetters_WithBooleanValues_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        Boolean activeTrue = true;
        Boolean activeFalse = false;

        // When
        user.setActive(activeTrue);

        // Then
        assertEquals(activeTrue, user.getActive());

        // When
        user.setActive(activeFalse);

        // Then
        assertEquals(activeFalse, user.getActive());
    }

    @Test
    void settersAndGetters_WithZeroId_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        Long zeroId = 0L;

        // When
        user.setId(zeroId);

        // Then
        assertEquals(zeroId, user.getId());
    }

    @Test
    void settersAndGetters_WithNegativeId_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        Long negativeId = -1L;

        // When
        user.setId(negativeId);

        // Then
        assertEquals(negativeId, user.getId());
    }

    @Test
    void settersAndGetters_WithLargeId_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        Long largeId = Long.MAX_VALUE;

        // When
        user.setId(largeId);

        // Then
        assertEquals(largeId, user.getId());
    }
}
