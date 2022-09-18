package com.backendchallenge.configuraion;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import org.springframework.context.annotation.Bean;

public class AzureCosmosDbConfiguration extends AbstractCosmosConfiguration {

    private String database;

    private String endpoint;

    private String key;

    private String containerName;

    private AzureKeyCredential azureKeyCredential;

    @Bean
    public CosmosClientBuilder init() {
        this.azureKeyCredential = new AzureKeyCredential(key);
        DirectConnectionConfig directConnectionConfig = new DirectConnectionConfig();
        GatewayConnectionConfig gatewayConnectionConfig = new GatewayConnectionConfig();

        return new CosmosClientBuilder()
                .endpoint(endpoint)
                .credential(azureKeyCredential)
                    .directMode(directConnectionConfig, gatewayConnectionConfig);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
