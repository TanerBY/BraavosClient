package com.braavos.fractal.service;


import com.braavos.fractal.client.FractalClient;
import com.braavos.fractal.dto.Category;
import com.braavos.fractal.dto.CategoryUpdate;
import com.braavos.fractal.dto.Transaction;
import com.braavos.fractal.repository.CategoryRepository;
import com.braavos.fractal.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(FractalService.class)
public class FractalServiceTest {

    @MockBean
    FractalClient fractalClient;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    FractalService fractalService;

    @Captor
    private ArgumentCaptor<List<Transaction>> argument;

    @Captor
    private ArgumentCaptor<Transaction> transactionArg;

    @Before
    public void before() {
        Mockito.reset(/*mocked objects to reset*/);
    }

    @Test
    public void shouldReadAndCategorizeRawTransactions () throws Exception {

        when(fractalClient.readTransactions()).thenReturn(Arrays.asList(new Transaction("123", "Costa","accountID", new Category("Coffee", "Costa"))));
        when(categoryRepository.findAll()).thenReturn(Arrays.asList( new Category("Coffee", "Costa")));

        fractalService.readRawTransactions();

        verify(transactionRepository).saveAll(argument.capture());

        assertEquals("Coffee",argument.getValue().get(0).getCategory().getCategoryName());

    }
    @Test
    public void shouldGetCategorizedTransactions() {

        when(transactionRepository.findByCategory("123")).thenReturn(Arrays.asList(new Transaction("123", "description","accountID", new Category("Coffee", "Costa"))));

        List<Transaction> transactions = fractalService.getCategorizedTransactions("123");

        assertEquals("Coffee", transactions.get(0).getCategory().getCategoryName());
        assertEquals("123", transactions.get(0).getTransactionId());

    }

    @Test
    public void shouldGetAllCategories() {

        when(categoryRepository.findAll()).thenReturn(Arrays.asList( new Category("Coffee", "Costa")));

        List<Category> categories = fractalService.getCategories();

        assertEquals("Coffee", categories.get(0).getCategoryName());
        assertEquals("Costa", categories.get(0).getFilterWord());

    }

    @Test
    public void shouldUpdateCategoryOfATransaction() {

        when(categoryRepository.findById("123")).thenReturn(Optional.of( new Category("Coffee", "Costa")));
        when(transactionRepository.findById("234")).thenReturn(Optional.of(new Transaction("123", "description","accountID", new Category("Coffee", "Costa"))));


        fractalService.updateTransactionCategory(new CategoryUpdate("123","234"));

        verify(transactionRepository).save(transactionArg.capture());

        assertEquals("123", transactionArg.getValue().getTransactionId());
        assertEquals("Coffee", transactionArg.getValue().getCategory().getCategoryName());

    }


}
