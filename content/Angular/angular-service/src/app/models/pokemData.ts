export type pokemonData = {
  name: string,
  id :number
  sprites : {
    front_default:string,
    other : {
      'official-artwork' : {
        front_default:string
      }

    }
  },
  types: {
    slot:number,
    type: {
      name:string
      url: string
    }
}[]
}
