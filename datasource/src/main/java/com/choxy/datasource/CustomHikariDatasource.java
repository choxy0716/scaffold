package com.choxy.datasource;

import com.choxy.enums.DatabaseTypeEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class CustomHikariDatasource extends AbstractDatasource{

    private DataSource dataSource;
    public void init() {

    }

    public DataSource getDatasourceConnection(DatabaseTypeEnum databaseTypeEnum) {
        switch (databaseTypeEnum) {
            case HIVE:
                dataSource = getHiveDatasource();
                break;
            case MYSQL:
                dataSource = getMysqlDatasource();
                break;
            case ORACLE:
                dataSource = getOracleDatasource();
                break;
            case POSTGRESQL:
                dataSource = getPgDatasource();
                break;
            case STARROCKS:
                dataSource = getStarRocksDatasource();
                break;
            case OCEANBASE:
                dataSource = getOCBDatasource();
                break;
            case CLICKHOUSE:
                dataSource = getClickhouseDatasource();
                break;
            case TIDB:
                dataSource = getTiDBDatasource();
                break;
            default:
                throw new RuntimeException("unSupport database");
        }
        return dataSource;
    }

    private DataSource getMysqlDatasource() {
        Properties properties = new Properties();
        properties.setProperty("", "");
        return getHikariDatasource(properties);
    }

    private DataSource getOracleDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }

    private DataSource getHiveDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }

    private DataSource getPgDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }

    private DataSource getStarRocksDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }

    private DataSource getOCBDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }

    private DataSource getClickhouseDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }

    private DataSource getTiDBDatasource() {
        Properties properties = new Properties();
        return getHikariDatasource(properties);
    }


    private DataSource getHikariDatasource(Properties properties) {
        DataSource dataSource = null;
        if (properties.isEmpty()) {
            return null;
        }
        try {
            HikariConfig hikariConfig = new HikariConfig(properties);
            dataSource = new HikariDataSource(hikariConfig);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return dataSource;

    }



}
