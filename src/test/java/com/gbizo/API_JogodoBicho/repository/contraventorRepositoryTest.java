package com.gbizo.API_JogodoBicho.repository;

import com.gbizo.API_JogodoBicho.model.contraventor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class contraventorRepositoryTest {

    @Autowired
    private contraventorRepository repository;

    @Test
    @DisplayName("Should get Contraventor successfully from DB")
    void findByLoginSuccess() {
        // Arrange: Create a contraventor instance and persist it
        contraventor contraventor = new contraventor();
        contraventor.setNome_cont("Test User");
        contraventor.setLogin("testuser");
        contraventor.setPassword("password"); // Assume password is not encrypted in this test
        contraventor.setTipo_cont("user");
        contraventor.setCpf_cont("12345678900");

        repository.save(contraventor); // Use repository.save instead of entityManager.persist

        // Act: Find contraventor by login
        contraventor foundContraventor = repository.findByLogin("testuser");

        // Assert: Verify the found contraventor is not null and has expected properties
        assertNotNull(foundContraventor, "Contraventor should not be null");
        assertEquals("testuser", foundContraventor.getUsername(), "Username should match");
        assertEquals("Test User", foundContraventor.getNome_cont(), "Nome should match");
    }
}
