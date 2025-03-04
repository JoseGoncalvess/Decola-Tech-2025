// interfaces (types x interface)
// TIPAGEM MAIS ESPECIFICA
type User = {
     name : String | Number;
    age : Number;
};

// TRABALHANDO COM CLASSE  - CONTRATO 
interface User2Interface {
   readonly name : String | Number;
    age : Number;
};

const user:  User = {
    name :'Guilherme',
    age : 23
}

const user2:  User2Interface = {
    name :'Guilherme',
    age : 23
}

console.log(user.name="Maykon");
console.log(user2);


// USA PRATICO DAS INTEFACES 

class Pessoa implements User2Interface {
   
    name: String | Number;
    age: Number;

    constructor(age:Number, name: String) {
        this.name = name;
        this.age = age;
    }
}

const celine : Pessoa =  new Pessoa(23, "Celine");

