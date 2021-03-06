package com.ps.services;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class TestUserService {

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
        assertNotNull(userService);
        userService.create("john.cusack@pet.com", "test", UserType.OWNER);
    }

    @BeforeTransaction
    public void checkDbInit() {
        long count = userService.countUsers();
        assertEquals(1, count);
    }

    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void testCreate() {
        userService.create("sds", "DDD", UserType.ADMIN);
        System.out.println("test testCreate");
    }
}
