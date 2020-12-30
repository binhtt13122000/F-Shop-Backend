package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Role;
import com.dev.fshop.services.AccountService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserController.class )
class UserControllerItegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Mock
    private Account account;

    @Test
    public void givenUser_whenRegisterAccount_thenReturnString() throws Exception {
        Date date = new Date();
        Role roleEntity = new Role();
        Account customerEntity = new Account("user12645896", "Trương Thanh Bình", new SimpleDateFormat("yyyy-mm-dd").parse("2020-08-08"),"0899386214","tramyphan24@gmail.com",true,
                "ca mau", "thanh pho ca mau", new SimpleDateFormat("yyyy-mm-dd").parse("2020-08-08"), new SimpleDateFormat("yyyy-mm-dd").parse("2020-08-08"),"er123456","2312313", true);
        String exampleCustomer = "{\n" +
                "  \"userName\": \"user12645896\",\n" +
                "  \"name\": \"Trương Thanh Bình\",\n" +
                "  \"birthDate\": \"2020-08-08\",\n" +
                "  \"phoneNumber\": \"0902472110\",\n" +
                "  \"email\": \"binhtts168@gmail.com\",\n" +
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

        when(accountService.addUser(customerEntity, role.getRoleId())).thenReturn("Create new user successfully!");
        // Send customerEntity as body to /v1/api/users

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/api/users/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(exampleCustomer))
                .andReturn();

        String expected = "Create new user successfully!";
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }
}
