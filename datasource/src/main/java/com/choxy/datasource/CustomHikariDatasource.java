package com.choxy.datasource;

import com.zaxxer.hikari.HikariConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomHikariDatasource extends AbstractHikariDatasource{

    public void init() {
        HikariConfig hikariConfig = new HikariConfig();

    }


    public Connection getMysqlConnection() {
        Connection connection;
        try {
            Class.forName("");
            connection = DriverManager.getConnection("", "", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }



}
