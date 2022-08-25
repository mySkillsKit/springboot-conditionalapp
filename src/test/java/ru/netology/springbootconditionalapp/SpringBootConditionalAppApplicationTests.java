package ru.netology.springbootconditionalapp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalAppApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);
    @Container
    public static GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);


    @Test
    void devProfileTest() {
        ResponseEntity<String> forEntityOne = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        System.out.println(forEntityOne.getBody());
        Assert.assertEquals("Current profile is dev", forEntityOne.getBody());
    }

    @Test
    void productionProfileTest() {
        ResponseEntity<String> forEntityTwo = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        System.out.println(forEntityTwo.getBody());
        Assert.assertEquals("Current profile is production", forEntityTwo.getBody());
    }


}
