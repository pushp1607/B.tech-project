package com.application.controllers;

import com.application.dto.DatabaseInfo;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.*;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;

public class ElasticsearchConnection {

    public static RestHighLevelClient connectToElasticsearch() {
        // Build the Elasticsearch client
        String username = "elastic";
        String password = "";
        String hostname = "localhost";
        int port = 9200;
        String scheme = "https";
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port, scheme))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        RestClient restClient = builder.build();
        return new RestHighLevelClientBuilder(restClient).setApiCompatibilityMode(true)
                .build();
    }

    public static boolean isElasticsearchConnected(RestHighLevelClient client) {
        try {
            // Check the cluster health
            ClusterHealthRequest request = new ClusterHealthRequest();
            ClusterHealthResponse response = client.cluster().health(request, RequestOptions.DEFAULT);
            ClusterHealthStatus status = response.getStatus();
            return status == ClusterHealthStatus.GREEN || status == ClusterHealthStatus.YELLOW;
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

        RestHighLevelClient client = connectToElasticsearch();

        // Check if connected
        boolean isConnected = isElasticsearchConnected(client);
        if (isConnected) {
            System.out.println("Connected to Elasticsearch!");
        } else {
            System.out.println("Failed to connect to Elasticsearch.");
        }

        // Close the client when done
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
