package com.jumia.customer.config;

import org.hibernate.dialect.Dialect;
import org.springframework.context.annotation.Configuration;

import java.sql.Types;

@Configuration
public class SQLiteDialectConfig extends Dialect {

    public SQLiteDialectConfig() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
    }

}