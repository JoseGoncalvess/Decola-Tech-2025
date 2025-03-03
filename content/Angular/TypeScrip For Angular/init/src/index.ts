type Hero = {
    name: string,
    power: string
}

function logarHero(params:Hero) {
    console.log("Opa, me chamo "+ params.name + " eu tenho o poder de "+ params.power );
}

logarHero({name : "Flash", power: "Velocidade"})