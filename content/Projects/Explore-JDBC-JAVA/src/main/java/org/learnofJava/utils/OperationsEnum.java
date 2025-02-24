package org.learnofJava.utils;

import java.util.stream.Stream;

public enum OperationsEnum {
    INSERT,
    CREATE,
    UPDATE,
    DELETE;

    public static  OperationsEnum getByDbOperation(final String dbOperation){
        return  Stream.of(org.learnofJava.utils.OperationsEnum.values())
                .filter(operationsEnum -> operationsEnum.name().startsWith(dbOperation.toUpperCase()))
                .findFirst()
                .orElseThrow();
    }

}
