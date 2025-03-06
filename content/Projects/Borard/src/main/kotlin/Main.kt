package org.newTechDeveloper

import org.newTechDeveloper.persistence.config.DbConectionConfig
import org.newTechDeveloper.persistence.migration.MigrationStrategy
import java.sql.SQLException

fun main() {
   try {
       var connection = DbConectionConfig.getConnection().apply {
           MigrationStrategy(this).execultMigration()
       }

   } catch (e: SQLException) {
       e.printStackTrace()
       println("ERRO AO CONECTAR AO DB")
   }
}