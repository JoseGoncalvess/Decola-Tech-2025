package org.newTechDeveloper.persistence.migration

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.exception.LiquibaseException
import liquibase.resource.ClassLoaderResourceAccessor
import lombok.AllArgsConstructor
import org.newTechDeveloper.persistence.config.DbConectionConfig
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintStream
import java.sql.Connection
import java.sql.SQLException


@AllArgsConstructor
class MigrationStrategy(val connection : Connection) {

    public  fun  execultMigration(){
        var originalOut = System.out
        var originalErr = System.err
        try {
            FileOutputStream("liquibase.log").use { fos ->
                System.setOut(PrintStream(fos))
                System.setErr(PrintStream(fos)  )
                try {
                   DbConectionConfig.getConnection().use {
                            connection ->
                        JdbcConnection(connection).use { jdbcConnection ->
                            val liquibase: Liquibase = Liquibase(
                                "/db/changelog/changelog-master.yml",
                                ClassLoaderResourceAccessor(),
                                jdbcConnection
                            )
                            liquibase.update()
                        }
                    };
                } catch (e: SQLException) {
                    e.printStackTrace()
                    System.setErr(originalErr)
                } catch (e: LiquibaseException) {
                    e.printStackTrace()
                    System.setErr(originalErr)
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            System.setOut(originalOut)
            System.setErr(originalErr)
        }
    }

}