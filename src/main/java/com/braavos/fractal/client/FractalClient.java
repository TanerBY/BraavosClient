package com.braavos.fractal.client;


import com.braavos.fractal.dto.TokenResponse;
import com.braavos.fractal.dto.Transaction;
import com.braavos.fractal.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class FractalClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<Transaction> readTransactions() {

        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Bearer " + getToken());
        HttpEntity<String> request =
                new HttpEntity<String>(null,headers);

        ResponseEntity<TransactionResponse> response =  restTemplate.exchange("https://sandbox.askfractal.com/banking/6/accounts/fakeacc/transactions?companyId=6",HttpMethod.GET,request, TransactionResponse.class);

        System.out.println("response "+response.getBody().getResults().get(0).getDescription());
        return response.getBody().getResults();
    }

    private String getToken () {
        String transactionUrl = "https://sandbox.askfractal.com/token";

        HttpEntity<String> request =
                new HttpEntity<String>(null, getHeaders());
        TokenResponse response = restTemplate.postForObject(transactionUrl,request,TokenResponse.class);
        return response.getAccess_token();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Api-Key", "oysN8BtPXM3Ozc6fTX0WI7qpRspQpCZP2jCxpNGg");
        headers.add("X-Partner-Id", "5111acc7-c9b3-4d2a-9418-16e97b74b1e6");
        return headers;
    }
}
