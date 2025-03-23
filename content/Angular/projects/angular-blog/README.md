# Angular Blog

Este projeto foi desenvolvido como parte de um estudo para validar conhecimentos em Angular. Ele consiste em um blog simples, com funcionalidades básicas e uma estrutura modularizada para facilitar o entendimento e a manutenção do código.

## Estrutura do Projeto

A estrutura do projeto está organizada da seguinte forma:

```
src/ ├── index.html
     ├── main.ts
     ├── main.server.ts
     ├── server.ts
     ├── styles.css
     ├── app/
          │ ├── app.component.ts
          │ ├── app.component.html
          │ ├── app.component.css
          │ ├── app.config.ts
          │ ├── app.config.server.ts
          │ ├── app.routes.ts
          │ ├── app.routes.server.ts
          │ ├── components/
          │ ├── pages/
          ├── data/
```

### Principais Arquivos e Diretórios

- **`app/`**: Contém os principais arquivos do aplicativo Angular, incluindo o componente raiz (`app.component`) e as configurações de rotas e servidor.
- **`components/`**: Diretório destinado a componentes reutilizáveis.
- **`pages/`**: Diretório destinado às páginas principais do blog.
- **`data/`**: Diretório reservado para dados estáticos ou mockados.

## Funcionalidades

O projeto inclui as seguintes funcionalidades:

1. **Exibição de Postagens**: Uma página principal que lista postagens do blog.
2. **Navegação**: Rotas configuradas para navegar entre diferentes páginas.
3. **Estilização**: Estilos básicos definidos no arquivo `styles.css`.
4. **Configuração de Servidor**: Arquivos como `server.ts` e `main.server.ts` para suporte a renderização no servidor (SSR).

## Componentes

Os principais componentes do projeto incluem:

- **`AppComponent`**: Componente raiz que serve como ponto de entrada para o aplicativo.
- **Componentes Reutilizáveis**: Localizados no diretório `components/`, como botões, cabeçalhos, e rodapés.
- **Páginas**: Localizadas no diretório `pages/`, como a página inicial e páginas de detalhes de postagens.

## Desenvolvimento

Este projeto foi criado utilizando o Angular CLI e segue as melhores práticas recomendadas para desenvolvimento Angular. Abaixo estão algumas ferramentas e tecnologias utilizadas:

- **Angular**: Framework principal para desenvolvimento do front-end.
- **TypeScript**: Linguagem utilizada para desenvolvimento.
- **CSS**: Para estilização.
- **Node.js**: Para suporte ao servidor e renderização no servidor (SSR).

## Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone <url-do-repositorio>
   ```

2. Instale as dependências:

   ```js
     npm install
   ```

3. Execute o servidor de desenvolvimento:

   ```js
   ng serve
   ```

4. Acesse o aplicativo no navegador:

   ```js
     http://localhost:4200
   ```

- Demais instruções no documento **Instructions.md**, nele podera confgurar o ambiente para rodar está aplicaçõa corretamente.

### Conclusão

Este projeto foi desenvolvido com o objetivo de consolidar conhecimentos em Angular e práticas de desenvolvimento front-end. Ele pode ser expandido com novas funcionalidades e componentes para atender a diferentes necessidades.
