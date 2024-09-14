package com.gbizo.API_JogodoBicho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbizo.API_JogodoBicho.config.security.TokenService;
import com.gbizo.API_JogodoBicho.dto.authDTO;
import com.gbizo.API_JogodoBicho.model.contraventor;
import com.gbizo.API_JogodoBicho.service.contraventorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
@SpringBootTest
@WebMvcTest(contraventorController.class)
public class contraventorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private contraventorService contraventorService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // Setup any required configuration here
    }

    @Test
    public void testLogin() throws Exception {
        // Create a mock contraventor for authentication
        contraventor mockContraventor = new contraventor();
        mockContraventor.setLogin("user");
        mockContraventor.setPassword("password");

        // Mock authentication and token generation
        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(mockContraventor, null, List.of());
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("user", "password")))
                .thenReturn(mockAuthentication);
        when(tokenService.generateToken(mockContraventor)).thenReturn("mockToken");

        authDTO authRequest = new authDTO("user", "password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contraventor/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("mockToken"));
    }

    @Test
    public void testCreateContraventor() throws Exception {
        contraventor contraventor = new contraventor();
        contraventor.setNome_cont("Gustavo Bizo");
        contraventor.setLogin("gbiz0");
        contraventor.setPassword("password");
        contraventor.setCpf_cont("12345678900");
        contraventor.setTipo_cont("admin");

        when(contraventorService.createContraventor(contraventor)).thenReturn(contraventor);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contraventor/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contraventor)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome_cont").value("Gustavo Bizo"));
    }

    @Test
    public void testGetAllContraventor() throws Exception {
        contraventor contraventor1 = new contraventor();
        contraventor1.setNome_cont("Gustavo Bizo");

        contraventor contraventor2 = new contraventor();
        contraventor2.setNome_cont("Neymar Jr");

        when(contraventorService.getAllContraventor()).thenReturn(List.of(contraventor1, contraventor2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contraventor/selectAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome_cont").value("Gustavo Bizo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome_cont").value("Neymar Jr"));
    }

    @Test
    public void testGetContraventorById() throws Exception {
        contraventor contraventor = new contraventor();
        contraventor.setNome_cont("Gustavo Bizo");
        when(contraventorService.getContraventorById(1L)).thenReturn(Optional.of(contraventor));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contraventor/select/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome_cont").value("Gustavo Bizo"));
    }

    @Test
    public void testDeleteContraventor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/contraventor/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateContraventor() throws Exception {
        contraventor contraventor = new contraventor();
        contraventor.setNome_cont("Gustavo Bizo Updated");
        contraventor.setLogin("gb");
        contraventor.setPassword("newpassword");
        contraventor.setCpf_cont("12345678900");
        contraventor.setTipo_cont("admin");

        when(contraventorService.updateContraventor(1L, contraventor)).thenReturn(contraventor);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/contraventor/edit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contraventor)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome_cont").value("Gustavo Bizo Updated"));
    }

}
