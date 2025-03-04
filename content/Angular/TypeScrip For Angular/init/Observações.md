# Instalçaõ do TypeScript

Antes de tuodo vamos criar um projeto node na nossa raiz pra facilitar nossos ajustes e rodar os script mais de boaa:

Roda o seguinte comando pra criar um projeto node na raiz do projeto:
`npm init -y`

Podemos intalr o typeScript manualmente de duas maneira diferente:

De mono Global: `npm install -g typescript `.

De mono Local: `npm install typescript -d`.

    - a flag "-d" Define que o ts sera instalado de modo Desenvolvedor enão para produção.

### Importante:

---

**tsconfig.json** - O arquivo de configuração do TS.
Nele conseguimos flexibilzar ainda masi a TRASNPILAÇÂO de **TS -> JS**.

Podemos efetuar a craão de uma manualmnete por meio do comando

`npx tsc --init`

Ou Criarmos a configuraçãola no proprio site do [TypeScript Paygroud ](https://www.typescriptlang.org/play/).

Assim importamos me nosso projeto.

## Facilitações

Para facilitar o nosso build e runer dos scripts criados precisamso criar uma novo script la no nosso `package.json` dentro de `scripts:{}` basta adiconar o seguinte comando, para execultar as duas instruções.
` "start": "npx tsc && node build/index.js",`

ficando algo assim:

- `"scripts": {
  "start": "npx tsc && node build/index.js",
  "test": "echo \"Error: no test specified\" && exit 1"
},`

Desse modo quando rodar `npm run start` o proprio note vai cuidar de execultar essas instruções.

## Melhorando

**TS-NODE-DEV** : Um servidor local que entende TypeScript, não precisando mais buildar os **script.ts** para depois rodar:

- Bastar rodar os eguinte comando para instalar essa ferramente(servidor):
  `npm install ts-node-dev -D`

Após isso absta adiocnar uma novo script la no **package.json** para poder funcionar corretamente:

`"start:dev": "ts-node-dev --respawn --transpile-only src/index.ts"`

- Ficando assim:

`  "scripts": {
    "start": "npx tsc && node build/index.js",
    "start:dev": "ts-node-dev --respawn --transpile-only src/**",
    "test": "echo \"Error: no test specified\" && exit 1"
  },`

---

Pronto agora basta rodar os eguinte comando e o seu script será traspilado sem precisa gerar alguam build.

`npm run start:dev`

Se preferir usar parâmetros de linha de comando, o processo seria parecido:

Crie um novo script no seu package.json que aceita parâmetros:

`"scripts": {
    "start:dev": "ts-node-dev --respawn --transpile-only"
}`
Passe o nome do arquivo como parâmetro na linha de comando:

`npm run start:dev -- src/file.ts`
O ts-node-dev automaticamente reconhecerá o arquivo passado após o --.

- **Observação:** Senão desligar os Servidor a medida que manipula o arquivo ele execulta de imediato, e isso ajuda muito
