package com.choxy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest(classes = {WebApplication.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WebTest {

    @Test
    public void arrayChild() {
        ArrayList<String> parentArr = new ArrayList<>();
        parentArr.add("schema_name");
        parentArr.add("table_name");

        ArrayList<String> childArr = new ArrayList<>();
        childArr.add("schema_name");
        childArr.add("table_name");

        Assert.assertTrue("是子集", parentArr.containsAll(childArr));
    }
}
