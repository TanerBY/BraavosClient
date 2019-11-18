package com.braavos.fractal.client;


import com.braavos.fractal.dto.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


@RunWith(SpringRunner.class)
@WebMvcTest(FractalClient.class)
public class FractalClientTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FractalClient client;

    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testReadTransactions() throws  Exception {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("https://sandbox.askfractal.com/token")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\n" +
                                "    \"access_token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzYW5kYm94VXNlciIsIm5hbWUiOiJGcmFjQm94IiwiaWF0IjoxNTE2MjM5MDIyLCJleHBpcmVzIjoxODAwfQ.A-Xk_RwJu3BZQ7gsUgq7nK4UPJpqIKJtxbBxkz2eJU4\",\n" +
                                "    \"partner_name\": \"FracBox\",\n" +
                                "    \"partner_id\": \"5111acc7-c9b3-4d2a-9418-16e97b74b1e6\",\n" +
                                "    \"expires\": 1800,\n" +
                                "    \"token_type\": \"Bearer\"\n" +
                                "}")
                );

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("https://sandbox.askfractal.com/banking/6/accounts/fakeacc/transactions?companyId=6")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Content-Type", "application/json"))
                .andExpect(header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzYW5kYm94VXNlciIsIm5hbWUiOiJGcmFjQm94IiwiaWF0IjoxNTE2MjM5MDIyLCJleHBpcmVzIjoxODAwfQ.A-Xk_RwJu3BZQ7gsUgq7nK4UPJpqIKJtxbBxkz2eJU4"))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\n" +
                                "    \"results\": [\n" +
                                "        {\n" +
                                "            \"bankId\": 6,\n" +
                                "            \"accountId\": \"fakeacc\",\n" +
                                "            \"companyId\": 6,\n" +
                                "            \"transactionId\": \"2eeb4b27-eac8-4d6c-a1cb-18ad8c57514d\",\n" +
                                "            \"bookingDate\": \"2017-09-02\",\n" +
                                "            \"description\": \"TFL.GOV.UK/CP TFL TRAVEL CH\",\n" +
                                "            \"amount\": \"4.69\",\n" +
                                "            \"currencyCode\": \"GBP\",\n" +
                                "            \"type\": \"DEBIT\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"bankId\": 6,\n" +
                                "            \"accountId\": \"fakeacc\",\n" +
                                "            \"companyId\": 6,\n" +
                                "            \"transactionId\": \"73e752d3-e4a6-4bc5-b7ff-3d7b1f674404\",\n" +
                                "            \"bookingDate\": \"2017-08-23\",\n" +
                                "            \"description\": \"TOTAL CHARGES TO 05MAR2017\",\n" +
                                "            \"amount\": \"4.12\",\n" +
                                "            \"currencyCode\": \"GBP\",\n" +
                                "            \"type\": \"DEBIT\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"links\": {}\n" +
                                "}")
                );



        List<Transaction> list = client.readTransactions();

        assertEquals("6",list.get(0).getBankId());
        assertEquals("73e752d3-e4a6-4bc5-b7ff-3d7b1f674404",list.get(1).getTransactionId());

    }


}
