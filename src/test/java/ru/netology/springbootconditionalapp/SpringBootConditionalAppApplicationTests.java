package ru.netology.springbootconditionalapp;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalAppApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    public static GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(9001);
    public static GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(9000);

    @BeforeAll
    public static void setUp() {
        prodapp.start();
        devapp.start();
    }

    @Test
    void devProfileTest() {
        ResponseEntity<String> forEntityOne = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(9000) + "/profile", String.class);
        System.out.println(forEntityOne.getBody());
        Assert.assertEquals("Current profile is dev", forEntityOne.getBody());
    }

    @Test
    void productionProfileTest() {
        ResponseEntity<String> forEntityTwo = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(9001) + "/profile", String.class);
        System.out.println(forEntityTwo.getBody());
        Assert.assertEquals("Current profile is production", forEntityTwo.getBody());
    }


}
