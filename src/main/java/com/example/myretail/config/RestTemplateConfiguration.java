package com.example.myretail.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Created by akrish10 on 5/26/20.
 */
@Configuration
public class RestTemplateConfiguration {



    @Value("${product.rest.totalconnections:10}")
    private int maxTotalConnections;

    @Value("${product.rest.readtimeout:3000}")
    private int readTimeout;

    @Value("${product.rest.connectiontimeout:5000}")
    private int connectionTimeout;

    /**
     * productRestTemplate.
     *
     * @return productRestTemplate
     */
    @Bean
    public RestTemplate productRestTemplate() {
        RestTemplate productRestTemplate = new RestTemplate(
                clientHttpRequestFactory(
                        new HttpComponentsClientHttpRequestFactory(httpClient())));
        productRestTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return productRestTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory(
            HttpComponentsClientHttpRequestFactory httpClientFactory) {
        httpClientFactory.setReadTimeout(readTimeout);
        httpClientFactory.setConnectTimeout(connectionTimeout);
        return httpClientFactory;
    }

    /**
     * httpClient.
     *
     * @return httpClient
     */
    @Bean
    public CloseableHttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }
}
