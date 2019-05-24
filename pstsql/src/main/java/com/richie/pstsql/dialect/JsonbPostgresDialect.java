package com.richie.pstsql.dialect;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.type.StringType;

import java.sql.Types;

/**
 * pstsql
 * <p>
 * Created by lylaut on 2019-05-17
 */
public class JsonbPostgresDialect extends PostgreSQL95Dialect {
    public JsonbPostgresDialect() {
        super();
        registerColumnType(Types.JAVA_OBJECT, "jsonb");
        registerHibernateType(Types.ARRAY, StringType.class.getName());
    }
}
