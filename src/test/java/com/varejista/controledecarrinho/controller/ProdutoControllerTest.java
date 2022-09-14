package com.varejista.controledecarrinho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ProdutoControllerTest {

    public static final String path = "/produto";

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
    void shoudGetAllProdutos() throws Exception {
        mockMvc.perform(get(path).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk());
    }

    @Test
    void shoudGetProduto() throws Exception {
        mockMvc.perform(get(path + "/{id}", 1)
                        .with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.nome", Is.is("teclado")))
                .andExpect(jsonPath("$.preco", Is.is(50.70)));

        mockMvc.perform(get(path + "/{id}", 2)
                        .with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(2)))
                .andExpect(jsonPath("$.nome", Is.is("processador")))
                .andExpect(jsonPath("$.preco", Is.is(700.50)));
    }

    @Test
    void shoudGetErrorWhenNaoEncontrado() throws Exception {
        mockMvc.perform(get(path + "/{id}", 500)
                        .with(user("mario").password("123").roles("admin")))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewProduto() throws Exception {
        Produto p = new Produto(null, "Modem", 127.80);
        mockMvc.perform(post(path)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(p)).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", Is.is(p.getNome())))
                .andExpect(jsonPath("$.preco", Is.is(p.getPreco())));
    }

    @Test
    void shouldUpdateProduto() throws Exception {
        Produto p = new Produto(null, "TecladoGamer", 345.92);
        mockMvc.perform(put(path + "/{nome}", "teclado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteProduto() throws Exception {
        mockMvc.perform(delete(path + "/{id}", 3).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isNoContent());
    }
}
