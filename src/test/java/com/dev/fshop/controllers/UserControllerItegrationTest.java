package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Role;
import com.dev.fshop.services.AccountService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@SpringBootTest(classes = UserController.class)
class UserControllerItegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenUser_whenRegisterAccount_thenReturnString() throws Exception {
        Date date = new Date();
        Role roleEntity = new Role();
        Account customerEntity = new Account();
        customerEntity.setUserName("user12245896");
        customerEntity.setName("Trương Thanh Bình");
        customerEntity.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse("2020-08-08"));
        customerEntity.setPhoneNumber("00902468110");
        customerEntity.setEmail("binhtts1624@gmail.com");
        customerEntity.setGender(true);
        customerEntity.setCountry("Việt Nam");
        customerEntity.setAddress("Q9, TP HCM");
        customerEntity.setRegisteredAt(new SimpleDateFormat("yyyy-mm-dd").parse("2020-08-08"));
        customerEntity.setLastLogin(new SimpleDateFormat("yyyy-mm-dd").parse("2020-08-08"));
        customerEntity.setPassword("user123856");
        customerEntity.setAvatar("string");
        customerEntity.setStatus(true);
        String exampleCustomer = "{\n" +
                "  \"userName\": \"user12245896\",\n" +
                "  \"name\": \"Trương Thanh Bình\",\n" +
                "  \"birthDate\": \"2020-08-08\",\n" +
                "  \"phoneNumber\": \"0902468110\",\n" +
                "  \"email\": \"binhtts1624@gmail.com\",\n" +
                "  \"gender\": true,\n" +
                "  \"country\": \"Việt Nam\",\n" +
                "  \"address\": \"Q9, TP HCM\",\n" +
                "  \"registeredAt\": \"2020-08-08\",\n" +
                "  \"lastLogin\": \"2020-08-08\",\n" +
                "  \"password\": \"user123856\",\n" +
                "  \"avatar\": \"string\",\n" +
                "  \"status\": true\n" +
                "}";
        Role role = new Role();
        role.setRoleId("ROL_1");
        role.setRoleName("USER");

        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());


        when(accountService.addUser(customerEntity, role.getRoleId())).thenReturn("Create new user successfully!");
        // Send customerEntity as body to /v1/api/users
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/api/users/register")
                .param("_csrf", csrfToken.getToken())
                .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(exampleCustomer))
                .andReturn();
        System.out.println(result.getRequest().getContentAsString());
        String expected = "Create new user successfully!";
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }
}
