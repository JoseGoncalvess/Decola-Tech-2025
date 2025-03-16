package org.newTechDeveloper.dto

data class BoardDetailsDTO(
    val id : Long,
    val name : String,
    val columns : List<BoardColumnDTO>
)
