
// FUNÇÕES 
function hello(name:String) : String {
    return "Hello, " + name;
}

console.log(hello("Anny"))

// FUNÇÕES DE MULTI TYPES
function configTypes(telefone:String | Number)   {
    return  telefone;
}
console.log(configTypes("87999999999"))
console.log(configTypes(87888888888))



// FUNÇÕES  ASSYNC
// SEMPRE TENHO QEU ETONA UM PROMISE<TIPADA>
async function getUSer(id :number) : Promise<String> {
    return "Maria"
}
