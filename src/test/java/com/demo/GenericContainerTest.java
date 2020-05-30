package com.demo;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

import java.io.IOException;

public class GenericContainerTest {

    @ClassRule
    public static GenericContainer webServer = new GenericContainer("alpine:3.2")
            .withExposedPorts(80)
            .withCommand("/bin/sh",
                    "-c",
                    "while true; do echo \"HTTP/1.1 200 OK\n\nTestContainers Rules!\" | nc -l -p 80; done"
            );

    static{
        webServer.start();
    }

    @Test
    public void shouldReturnCorrectMessage() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + webServer.getContainerIpAddress() + ":" + webServer.getMappedPort(80));
        HttpResponse resp = httpclient.execute(httpGet);

        String greeting = EntityUtils.toString(resp.getEntity()).trim();
        Assertions.assertEquals("TestContainers Rules!", greeting);
    }
}
