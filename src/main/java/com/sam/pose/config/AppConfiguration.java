package com.sam.pose.config;

import com.microsoft.azure.spring.data.cosmosdb.config.AbstractDocumentDbConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableDocumentDbRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDocumentDbRepositories(basePackages = "com.sam.pose.dao")
public class AppConfiguration extends AbstractDocumentDbConfiguration {

    @Value("${azure.cosmos.db.uri}")
    private String url;

    @Value("${azure.cosmos.db.key}")
    private String key;

    @Value("${azure.cosmos.db.database}")
    private String database;


    @Override
    public DocumentDBConfig getConfig() {
        return DocumentDBConfig.builder(url, key, database).build();
    }
}
