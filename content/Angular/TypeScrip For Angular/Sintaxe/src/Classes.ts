// MODIFICADORES DE ACESSO
// - PUBLIC - COMPORTAMENTO DEFAULT
// - PRIVATE > SO SERA ACESADA DENTRO DA CLASS
// - PROTECT -> SOMENTE CLASSE E SUAS HERDEIRA CONSEGUE ENXERGAR

// TRÁS A SEGURANÇA DA TIPAGEM 

class Char {
protected name : String
readonly power : Number

constructor(name: String, power: Number) {
this.name = name;
this.power = power;
    
}

public attack() : void{
    console.log(`${this.name} Atacou!!\nDano causado de ${this.power}`)
}

}

class Magincian extends Char {
    magic: String;
    magicPoint: Number;
    constructor(name: String, power: Number, magic: String, magicPoint:Number){
        super(name, power);
        this.magic = magic
        this.magicPoint = magicPoint
    }

    override attack(): void {
        console.log(`${this.name} Atacou!!\nUsando a magia ${this.magic}\nDano causado de ${this.magicPoint}`)
    }
    
  
}





const kindred = new Char("Kindred",346);
const Syndra = new Magincian("Syndra",89,"Esferas do Vazio",260);
kindred.attack()
Syndra.attack()