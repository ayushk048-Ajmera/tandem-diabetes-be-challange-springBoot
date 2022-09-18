package com.backendchallenge.configuraion;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.cloud.azure.cosmos")
@Data
@EnableCosmosRepositories(basePackages = "com.backendchallenge.repository")
@Component
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
