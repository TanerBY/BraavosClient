package com.braavos.fractal.controller;


import com.braavos.fractal.dto.Category;
import com.braavos.fractal.dto.Transaction;
import com.braavos.fractal.service.FractalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FractalService fractalService;

    @Test
    public void shouldReturnCategorizedTransactions() throws Exception {
        when(fractalService.getCategorizedTransactions("123")).thenReturn(Arrays.asList(new Transaction("123", "description","accountID", new Category("Coffee", "Costa"))));
        this.mockMvc.perform(get("/categories/123/transactions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]transactionId").value("123"))
                .andExpect(jsonPath("$[0]accountId").value("accountID"))
                .andExpect(jsonPath("$[0]category.categoryName").value("Coffee"))
                .andExpect(jsonPath("$[0]category.filterWord").value("Costa"));
    }


    @Test
    public void shouldAddNewCategory() throws Exception {
        when(fractalService.addCategory(any())).thenReturn( new Category("Coffee", "Costa"));

        this.mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content("{\"categoryName\":\"Coffee\",\"filterWord\":\"Costa\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryName").value("Coffee"))
                .andExpect(jsonPath("filterWord").value("Costa"));
    }

    @Test
    public void shouldReturnAllCategories() throws Exception {
        when(fractalService.getCategories()).thenReturn(Arrays.asList( new Category("Coffee", "Costa")));

        this.mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]categoryName").value("Coffee"))
                .andExpect(jsonPath("$[0]filterWord").value("Costa"));
    }

    @Test
    public void shouldUpdateCategory() throws Exception {
        this.mockMvc.perform(put("/categories").contentType(MediaType.APPLICATION_JSON).content("{\"categoryID\":\"Coffee\",\"transactionID\":\"Costa\"}"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}