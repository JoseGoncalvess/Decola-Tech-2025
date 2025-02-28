class CardNews extends HTMLElement{
    constructor() {
        super();

        const shadow = this.attachShadow({mode: 'open'});
        shadow.appendChild(this.build());
        shadow.appendChild( this.styles());
    }

    build(){
        const comonnetRoot = document.createElement("div");
        comonnetRoot.setAttribute("class","card");

        const cardLeft= document.createElement("div");
        cardLeft.setAttribute("class","card__left");

        
        const cardRigth = document.createElement("div");
        cardRigth.setAttribute("class","card__rigth");

        comonnetRoot.appendChild(cardLeft);
        comonnetRoot.appendChild(cardRigth);



        const titleNotice = document.createElement("h1");
        const title = this.getAttribute("soursTitle")
        titleNotice.textContent = `${title}`;



        const autor = document.createElement("span");
        autor.setAttribute("class","title_autor")
        const autorSourc = "By "  + (this.getAttribute("autor") || "Anonymous")
        autor.textContent = `${autorSourc}`;



        const descripNotice = document.createElement("p");
        const descript = this.getAttribute("descript")
        descripNotice.textContent = `${descript}`;


        const imgNotice = document.createElement("img");
        const image = this.getAttribute("soursImage") || "./assets/default.jpg"
        const altertext = this.getAttribute("soursImage")
        imgNotice.setAttribute("src",`${image}`);
        imgNotice.setAttribute("alt",`${altertext}`);
        imgNotice.setAttribute("width","300px");


        cardLeft.appendChild(autor);
        cardLeft.appendChild(titleNotice);
        cardLeft.appendChild(descripNotice);

        cardRigth.appendChild(imgNotice);


        return comonnetRoot;
    }

    styles(){
        const style = document.createElement("style");
        style.textContent = `.card {
    width: 720px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 10px;
    -webkit-box-shadow: 4px 0px 20px -6px rgba(148, 148, 148, 0.56);
    -moz-box-shadow: 4px 0px 20px -6px rgba(148, 148, 148, 0.56);
    box-shadow: 4px 0px 20px -6px rgba(148, 148, 148, 0.56);
}

.card__left {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.card__left > h1 {
    margin-top: 10px;
    font-size: 20px;
}

.card__left > p {
    font-size: 12px;
    color: gray;
}

.card__left > span {
    font-weight: 600;
}`;

        return style;
    }
}

customElements.define("card-news",CardNews);