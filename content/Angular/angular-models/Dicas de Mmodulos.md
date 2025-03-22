# Módulos no Angular

Os módulos no Angular são uma forma de organizar e estruturar aplicações, permitindo que funcionalidades sejam agrupadas de maneira lógica. Cada módulo é representado por uma classe decorada com `@NgModule`, que define metadados como componentes, diretivas, pipes e outros módulos que ele utiliza.

## Benefícios dos Módulos

- **Reutilização de Código**: Permite encapsular funcionalidades específicas que podem ser reutilizadas em diferentes partes do projeto.
- **Organização**: Facilita a manutenção e escalabilidade do código, separando responsabilidades.
- **Carregamento Sob Demanda**: Com lazy loading, módulos podem ser carregados apenas quando necessários, otimizando o desempenho da aplicação.

Para mais informações, consulte a [documentação oficial do Angular](https://angular.io/guide/ngmodules).

## Shared Module:

modulo ultilizado apra poder agrupar modulos em comum da aicação podendo exportar os varios componentes em apenas um, centralizando a organzação dos modulos **EM COMUM**

## Page Module:

Comumente ultilizado para organização das pastas, recomenda-se que a componentização dos projeto sseja por contexto assim sua estrutura fica mais limpa a e facil de um possivel manutenção futuramente. Então aqui entra todas as outars paginas e seus sub-modulos de componentes.
