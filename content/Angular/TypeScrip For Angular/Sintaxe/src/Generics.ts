// MUDO  ANY PARA T E AO CHAMR O METOOD DEFNO O QEU EU QUERO RECEBER

function concatArray<T>(...itens:T[]):T[] {
    return new Array().concat(itens)
}


// QUI ESTOU DEFININDO QUE QUERO RECEBER UM ARRAY DE STRING[]
const coisa = concatArray<String[]>(["1","2","3","8"],["Carro","Academia","Games"])
coisa.push(["Naruto"]) // QUI JA BAGUNÇA TUDO ANY == PUTARIA
console.log(coisa)