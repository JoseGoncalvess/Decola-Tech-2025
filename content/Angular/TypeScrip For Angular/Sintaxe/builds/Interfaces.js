"use strict";
;
const user = {
    name: 'Guilherme',
    age: 23
};
const user2 = {
    name: 'Guilherme',
    age: 23
};
console.log(user.name = "Maykon");
console.log(user2);
// USA PRATICO DAS INTEFACES 
class Pessoa {
    constructor(age, name) {
        this.name = name;
        this.age = age;
    }
}
const celine = new Pessoa(23, "Celine");
