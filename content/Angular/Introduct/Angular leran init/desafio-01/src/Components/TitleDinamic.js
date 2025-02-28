
class TitleDinamic extends HTMLElement{
    constructor() {
        super();

        const shadow = this.attachShadow({mode: 'open'});

        // BASE DO COMPONENT
        const title = document.createElement("h1");
        // DEIXNAOD OS COMONENTES DINAMICOS
        title.textContent = this.getAttribute("title");  

        // STYLE DO COMPONENTE
        const style = document.createElement("style");
        style.textContent = `
            h1{
                color: green;
            }
        `;

// ADICIONANDO O ELEMNTOA O SHADOW
       shadow.appendChild(title);
       shadow.appendChild(style)
    }
}
customElements.define("titulo-dinamico", TitleDinamic);