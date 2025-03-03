
# Instalçaõ do TypeScript

Podemos intalr o typeScript manualmente de duas maneira diferente:

De mono Global: `npm install -g typescript `.
    
De mono Local: `npm install typescript -d`.
    
    - a flag "-d" Define que o ts sera instalado de modo Desenvolvedor enão para produção.
    

### Importante:
---
 **tsconfig.json** - O arquivo de configuração do TS.
Nele conseguimos flexibilzar ainda masi a TRASNPILAÇÂO de **TS -> JS**.

Podemos efetuar a craão de uma manualmnete por meio do comando 

` npx tsc --init
`

Ou Criarmos a configuraçãola no proprio site do [TypeScript Paygroud ](https://www.typescriptlang.org/play/).

Assim importamos me nosso projeto.


## Facilitações

Para facilitar o nosso build e runer dos scripts criados precisamso criar uma novo script la no nosso `package.json` dentro de `scripts:{}` basta adiconar o seguinte comando, para execultar as duas instruções.
` "start": "npx tsc && node build/index.js",`

ficando algo assim:

- `` "scripts": {
    "start": "npx tsc && node build/index.js",
    "test": "echo \"Error: no test specified\" && exit 1"
  },
``

Desse modo quando rodar `npm run start` o proprio note vai cuidar de execultar essas instruções.