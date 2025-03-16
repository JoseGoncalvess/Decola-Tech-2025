package org.newTechDeveloper.persistence.config.utils.enums

enum class BoardColumnKindEnum {

    INITIAL, FINAL, CANCEL, PENDING;

   companion object {
       fun findByName(name: String): BoardColumnKindEnum {
           return    BoardColumnKindEnum.values().filter { b -> b.name.equals(name) }.first();

       }
   }
}