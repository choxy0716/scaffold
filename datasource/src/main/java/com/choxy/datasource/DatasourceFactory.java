package com.choxy.datasource;

import java.io.Serializable;

public class DatasourceFactory implements Serializable {

    private CustomHikariDatasource customHikariDatasource;

    public CustomHikariDatasource getCustomHikariDatasource() {
        return customHikariDatasource;
    }

    public void setCustomHikariDatasource(CustomHikariDatasource customHikariDatasource) {
        this.customHikariDatasource = customHikariDatasource;
    }
}
