package com.choxy.enums;

public enum DatabaseTypeEnum {

    MYSQL("mysql"),
    ORACLE("oracle"),
    POSTGRESQL("postgresql"),
    HIVE("hive"),
    STARROCKS("starrocks"),
    CLICKHOUSE("clickhouse"),
    TIDB("tidb"),
    OCEANBASE("oceanbase");

    private String dbType;
    DatabaseTypeEnum(String databaseType) {
        this.dbType = databaseType;
    }

    public String getDbType() {
        return this.dbType;
    }
}
