// PRIMITIVOS 
let ligado: boolean = true;
let epocaDoAno: String = "Carnavel";
let idade: Number = 24; // number prrite salvar numros com pontos também


// TIOS ESPECIAIS
// null
let nulo: null = null;
// undefined
let indefinido: undefined = undefined;


//TIPOS ABRANGENTES
// anny // ACEITA QULQUER COISA
let esatdoBool: any = true;
let esatdoString: any = "true";
let esatdoNUmber: any = 1;

//VOID - NÂO RETORNA NADA
let retorno: void;


//========================================================
//OBJETO 
// SEM PREVISIBILIDADE
let produto: Object = {
    name: "Picanha",
    valor: 200
}

// NA CRIAÇÂO D EUM OBJETO DEVEMOS CRIAR UM NOVO TIPO, ASIM DEFININDO CADA PROPRIEDADE
type produtoLoja = { name: String, valor: Number, unidade: Number }

// COM PREVISIBILIDADE
let newProduto: produtoLoja = {
    name: "Picanha",
    valor: 200,
    unidade: 1
}

//=========================================================

// ARRAYS - MANEIRA DE CRIAR
let dados: string[] = ["God Of War", "PES 2013", "Dragon Ball Z"]
let dados2: Array<String> = ["Alta da comaparecida", "John Wek", "V de Vigança"]

// ARRAY MULTI TYPE
let info: (String | Number)[] = ["Carrocha", 21]


//===================================================

// TUPLAS - O SER CRIADA SEGUE A SEQUENCIA DA SUA TIPAGEM
let boleto: [String, Number, Boolean] = ["FACULDADE", 241, true]

//=======================================================

// ARRAYS - METOOS
// TUDO QUE TEM NO JAVA AQUI É TOP TAMBÉM
let jogos: string[] = ["God Of War", "PES 2013", "Dragon Ball Z"]

jogos.filter( j => j.includes("God"))
jogos.map( j => console.log(j))
jogos.pop()
jogos.push("Dishonored")
// =======================================================


// DATES

let studyNow : Date = new Date("2025-03-03 11:39")