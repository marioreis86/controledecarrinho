package com.varejista.controledecarrinho.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarrinhoControllerTest {
    public static final String path = "/carrinho";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shoudGetAllCarrinhos() throws Exception {
        mockMvc.perform(get(path).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk());
    }
    @Test
    void shoudGetCarrinho() throws Exception {
        mockMvc.perform(get(path + "/{id}", 1).with(user("mario").password("123").roles("admin")))
                .andExpect(status().isOk());
    }

}
