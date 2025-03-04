"use strict";
// MODIFICADORES DE ACESSO
// - PUBLIC - COMPORTAMENTO DEFAULT
// - PRIVATE > SO SERA ACESADA DENTRO DA CLASS
// - PROTECT -> SOMENTE CLASSE E SUAS HERDEIRA CONSEGUE ENXERGAR
// TRÁS A SEGURANÇA DA TIPAGEM 
class Char {
    constructor(name, power) {
        this.name = name;
        this.power = power;
    }
    attack() {
        console.log(`${this.name} Atacou!!\nDano causado de ${this.power}`);
    }
}
class Magincian extends Char {
    constructor(name, power, magic, magicPoint) {
        super(name, power);
        this.magic = magic;
        this.magicPoint = magicPoint;
    }
    attack() {
        console.log(`${this.name} Atacou!!\nUsando a magia ${this.magic}\nDano causado de ${this.magicPoint}`);
    }
}
const kindred = new Char("Kindred", 346);
const Syndra = new Magincian("Syndra", 89, "Esferas do Vazio", 260);
kindred.attack();
Syndra.attack();
