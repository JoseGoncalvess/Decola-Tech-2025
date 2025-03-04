"use strict";
// PRIMITIVOS 
let ligado = true;
let epocaDoAno = "Carnavel";
let idade = 24; // number prrite salvar numros com pontos também
// TIOS ESPECIAIS
// null
let nulo = null;
// undefined
let indefinido = undefined;
//TIPOS ABRANGENTES
// anny // ACEITA QULQUER COISA
let esatdoBool = true;
let esatdoString = "true";
let esatdoNUmber = 1;
//VOID - NÂO RETORNA NADA
let retorno;
//========================================================
//OBJETO 
// SEM PREVISIBILIDADE
let produto = {
    name: "Picanha",
    valor: 200
};
// COM PREVISIBILIDADE
let newProduto = {
    name: "Picanha",
    valor: 200,
    unidade: 1
};
//=========================================================
// ARRAYS - MANEIRA DE CRIAR
let dados = ["God Of War", "PES 2013", "Dragon Ball Z"];
let dados2 = ["Alta da comaparecida", "John Wek", "V de Vigança"];
// ARRAY MULTI TYPE
let info = ["Carrocha", 21];
//===================================================
// TUPLAS - O SER CRIADA SEGUE A SEQUENCIA DA SUA TIPAGEM
let boleto = ["FACULDADE", 241, true];
//=======================================================
// ARRAYS - METOOS
// TUDO QUE TEM NO JAVA AQUI É TOP TAMBÉM
let jogos = ["God Of War", "PES 2013", "Dragon Ball Z"];
jogos.filter(j => j.includes("God"));
jogos.map(j => console.log(j));
jogos.pop();
jogos.push("Dishonored");
// =======================================================
// DATES
let studyNow = new Date("2025-03-03 11:39");
