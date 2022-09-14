package com.varejista.controledecarrinho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varejista.controledecarrinho.models.Cupom;
import com.varejista.controledecarrinho.models.Produto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CupomControllerTest {

    public static final String path = "/cupom";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shoudGetAllCupons() throws Exception {
        mockMvc.perform(get(path).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk());
    }

    @Test
    void shoudGetCupom() throws Exception {
        mockMvc.perform(get(path + "/{id}", 3)
                        .with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(3)))
                .andExpect(jsonPath("$.nome", Is.is("CUPOM90")))
                .andExpect(jsonPath("$.desconto", Is.is(0.90)));

        mockMvc.perform(get(path + "/{id}", 2)
                        .with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(2)))
                .andExpect(jsonPath("$.nome", Is.is("20DESCONTO")))
                .andExpect(jsonPath("$.desconto", Is.is(0.20)));
    }

    @Test
    void shoudGetErrorWhenNaoEncontrado() throws Exception {
        mockMvc.perform(get(path + "/{id}", 500)
                        .with(user("mario").password("123").roles("admin")))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewProduto() throws Exception {
        Cupom c = new Cupom(null, "SUPERDESCONTO", 0.95);
        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(c)).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", Is.is(c.getNome())))
                .andExpect(jsonPath("$.desconto", Is.is(c.getDesconto())));
    }

    @Test
    void shouldUpdateCupom() throws Exception {
        Cupom c = new Cupom(null, "SUPERDESCONTO", 0.99);
        mockMvc.perform(put(path + "/{nome}", "SUPERDESCONTO")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(c)).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteCupom() throws Exception {
        mockMvc.perform(delete(path + "/{id}", 3).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isNoContent());
    }
}
