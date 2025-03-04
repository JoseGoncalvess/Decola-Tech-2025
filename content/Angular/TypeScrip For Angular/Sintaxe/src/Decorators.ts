// EXECULTAR ALGO QUE DECORAMOS
function ExbirNome(target: any) {
    console.log(target);

}

@ExbirNome
class Funcionario { }

// // AQUI ELE SO EXIBE QUEM CHAMOU ELA 

function ApiVersion(version: any) {
    return (target: any) => {
        Object.assign(target.prototype, { __version: version })
    }

}

// ATRIBUTE DECORATION
function minLength(length: number) {
    return (target: any, key: string) => {
        let _value = target[key];

        const  getValue  = () => _value;
        const  setValue  = (newValue: string) => {
            if (newValue.length <= length) {
                throw new Error(`Tamnho menor do que ${length}`);
            }
            _value = newValue
        };
         Object.defineProperty(target, key, {
            get: getValue,
            set: setValue
         });
    };

}



@ApiVersion("1.0.1")
class Api {
    // CRIAMOS UM LOGICA AO VALOR SER LIDO,
    //  COMPLEMENTANDO AINDA MAIS O TYPESCRIPT INJETANDO O COMPORTAMENTO
    @minLength(4) 
    name: String;

    constructor(name: String) {
        this.name = name;

    }

}

const api = new Api("Animes")
console.log(api.__version);



