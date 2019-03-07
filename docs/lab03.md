## Terza lezione di laboratorio: HTML, CSS e JS

Durante questa lezione tratteremo degli strumenti base necessari per creare delle pagine web.

### HTML
HTML è l'acronimo di **H**yper**T**ext **M**arkup **L**anguage; come suggerisce il nome è un linguaggio di markup che serve sostanzialmente a descrivere la struttura di un ipertesto ( una pagina web ).

#### Tag
Parte fondamentale di HTML sono i **tag** per mezzo dei quali si identificano le varie parti di un ipertesto; è possibile pensare a questi tag come delle etichette, che dicono, ad esempio, "questa cosa è un link".  
Queste etichette sono rappresentate da parole chiave racchiuse tra parentesi angolari e molto spesso ad ogni tag di apertura corrisponde un tag di chiusura, che si differenzia dal tag di apertura perchè inizia con il carattere `/`


```html
<keyworkd></keyword>
```

Esistono due tipologie di elementi identificati dai tag:
  * **block**: sono quegli elementi HTML che non permettono ad altri oggetti di "stargli a fianco", ovvero occupano tutto lo spazio orizzontale

  * **inline**: sono quegli oggetti HTML che non occupano tutto lo spazio orizzontale, permettendo ad altri elementi di affiancarcisi.


Analizziamo ora i vari tag, con un approccio top-down, che compaiono in un documento HTML.

##### Il tag `html`
L'intero documento ipertestuale è compreso tra una coppia di tag html, che segna l'inizio e la fine di un documento HTML.

```html
<!DOCTYPE HTML>
<html>
<!-- Resto del documento -->
</html>
```

La prima riga serve a definire il tipo di documento, HTML nel nostro caso.
La terza riga dell'esempio mostra come creare un commento: basta mettere il proprio commento tra la coppia di tag `<!--` e `-->`.

##### Il tag `head`
Il tag head è quel particolare tag che contiene tutta una serie di informazioni relative al nostro documento ipertestuale; in particolare è buona norma che contenga:
  1. L'encoding con cui il file HTML è stato scritto
  2. Il titolo della nostra pagina

Oltre a queste informazioni base all'interno del tag head possono comparire anche riferimenti a fogli di stile esterni o a script, trattati nelle relative sezioni.

##### Il tag `title`
Il tag title serve a dire al browser il titolo del documento, in particolare è il nome che viene visualizzato nel tab del browser.  

```HTML
<!DOCTYPE HTML>
<html>
  <head>
    <title>Pagina di esempio</title>
  </head>
  <body>
  </body>
</html>
```

##### Il tag `meta`
Il tag meta è un tag con cui è possibile fornire al browser informazioni relative all'URL, all'encoding del file e molto altro.

```html
<html>
  <head>
    <meta charset="UTF-8">
    <title>Pagina di esempio</title>
  </head>
  <body>
  </body>
</html>
```

E' il primo esempio di tag che non ha un corrispettivo tag di chiusura.  

##### Il tag `body`
Il tag body contiene il corpo della pagina, ovvero quello che l'utente visualizzerà.  

##### I tag per gestire il testo - __inline__
Parlando di contenuto parliamo prima di tutto di testo che deve essere visualizzato sullo schermo ed esistono dei tag apposta per gestire il testo grassetto, corsivo eccetera.

  * `<b>Testo grassetto</b>` ha l'effetto di rendere il testo grassetto.
  * `<i>Testo corsivo</i>` ha l'effetto di rendere il testo corsivo.
  * `<tt>Testo non proporzionato</i>` ha l'effetto di rendere il testo scritto con carattere non proporzionale, ovvero un carattere in cui tutti i caratteri occupano lo stesso spazio.
  * `<pre>Testo formattato    come voglio</pre>` ha l'effetto di rendere il testo scritto con carattere non proporzionato mantenendo la formattazione data, ovvero non comprime spazio/a capo.

##### I tag `a` - __inline__
Il tag ha il compito di rendere il testo un link ipertestuale verso l'URL specificata. L'URL va specificata nell'attirbuto `href` del tag.

```html
<a href="https://www.unitn.it">Unitn</a>
```

##### Il tag `img` - __inline__
Il tag img ha il compito di caricare e mostrare a schermo un'immagine corrispondente all'URL specificata.

```html
<img src="./imgs/img.jpg" alt="Un'immagine">
```

Anche il tag img non ha un corrispondente tag di chiusura.

##### Il tag `p` - __block__
Il tag p rappresenta un paragrafo, esattamente come quello di Word, Pages o qualsiasi altro software di videoscrittura che lo implementi.  

```html
<p>Paragrafo</p>
```

##### Il tag `div` - __block__
Il tag div consiste letteralmente in un contenitore che contiene altri tag; viene usato di solito per individuare zone logicamente diverse della pagina, come ad esempio gli strumenti di ricerca e lo spazio in cui viene presentato il risultato.

```html
<div>
  <!--Lo spazio dei risultati -->
</div>
<div>
  <!-- La barra di ricerca -->
</div>
```

##### Il tag `span` - __inline__
E' l'equivalente del tag div, con l'unica differenza che è un tag inline e quindi non occupa tutta la larghezza disponibile.  

```html
<span>
  <!--Lo spazio dei risultati -->
</span>
<span>
  <!-- La barra di ricerca -->
</span>
```

##### Le liste
Esistono tre tipi di liste:
  1. Lista non ordinata: generata dal tag `<ul>`
  2. Lista ordinata: generata dal tag `<ol>`
  3. Lista delle definizioni: generata dal tag `<dl>`
Tutti questi tag devono avere un corrispettivo tag di chiudura.  

Gli elementi delle liste sono identificati dal tag:
  * `<li>` per la lista ordinata e non ordinata
  * `<dt>` per identificare il termine da definire
  * `<dd>` per identificare la descrizione

Questi tag possono non avere un corrispettivo tag di chiusura, ma è buona norma metterlo.  

##### Il tag `table` - __block__
Il tag table serve per identificare una tabella; in particolare esistono molti sotto-tag per gestire le varie componenti di una tabella. I principali sono
  1. `tbody`: racchiude il corpo della tabella
  2. `tr`: Racchiude una riga della tabella
  3. `td`: Racchiude una cella della tabella.

```html
<table>
  <tbody>
    <tr>
      <td>Riga 1, cella 1</td>
      <td>Riga 1, cella 2</td>
    </tr>
    <tr>
      <td>Riga 2, cella 1</td>
      <td>Riga 2, cella 2</td>
    </tr>
  </tbody>
</table>
```

#### Attributi
Ai tag è possibile specificare degli attributi, al fine di specificarne il comportamento o alcuni parametri; come ad esempio l'URL di un'immagine da caricare e mostrare ( attribut `src` del tag `img`), l'URL a cui un link rimanda ( attributo `href` del tag `a` ) e così via.

##### Attributo `id`
L'attributo id può essere dato a un qualsiasi tag HTML e identifica il contenuto di quel tag con un nome che, di norma, dovrebbe essere unico in tutti il documento.

##### Attributo `class`
E' quasi uguale all'attributo id con l'unica differenza che può essere ripetuto all'interno del documento. Su questo attributo si basano i vari framework CSS.

```html
<div id="login-form"></div>
<div class="colonna"></div>
```

##### I form
I form sono sostanzialmente i moduli e servono per richiedere all'utente un input che poi viene inviato a una URL e processato.  

```html
<form method="GET" action="./elabora.php">
  <span>Username:</span><br>
  <!-- Inserisce un campo di testo semplice -->
  <input type="text" name="username"><br>

  <!-- Inserisce un campo di testo dove i caratteri sono tutti nascosti -->
  <span>Password:</span><br>
  <input type="password" name="password"><br>

  <!-- Inserisce dei radio-button, permettendo di selezionarne solo uno -->
  <span>Seleziona il tuo sesso:</span><br>
  <!-- Il primo viene selezionato di default -->
  <input type="radio" name="gender" value="maschio" checked> Maschio <br>
  <input type="radio" name="gender" value="femmina"> Femmina <br>
  <input type="radio" name="gender" value="altro"> Altro <br><br>

  <!-- Il pulsante per andare a capo -->
  <input type="submit" value="Invia">

  <!-- Il pulsante per ripulire il form -->
  <input type="reset" value="Annulla"><br>

</form>
```

Il tag `br` ha il compito di interrompere la riga corrente, forzando quindi il browser ad andare a capo.  

Per inserire un campo da compilare di solito si usa il tag `input`, anche se esistono altri tag come `select`, `textarea` e altri.  

Fondamentale nel form è il "submit button": quando viene premuto il contenuto del form viene inviato ( attraverso il metodo specificato nell'attributo method del tag form ) all'URL specificata nell'attributo action del form, sottoforma di coppie chiave-valore in questo formato `chiave1=valore1&chiave2=valore2` e via dicendo.  
Le chiavi consistono dei vari `name`, specificati per ogni input.

#### HTML5
Con l'avvento di HTML5 sono arrivate anche molte funzionalità nuove che prima richiedevano di installare plugin esterni, come ad esempio FlashPlayer.

#### Reference
Per una lista completa e documentazione dei tag HTML si rimanda al [sito di W3C](https://www.w3schools.com/tags/).


***

### CSS
CSS è l'acronimo di **C**ascade **S**tyle **S**heet, che tradotto letteralmente significa foglio di stile a cascata.  
CSS è un modo per definire come un documento ipertestuale deve essere presentato graficamente, specificando i colori, i font, gli effetti grafici e altro.  
La scrittura di un foglio di stile è molto semplice, si tratta infatti di una serie di coppie chiave-valore associate a un particolare **_selettore_**, ovvero un qualcosa che è in grado di identificare uno o più elementi all'interno del documento ipertestuale.  
La sintassi dei CSS è
```css
selettore {
  proprieta1: valore1;
  proprieta2: valore2;
  etc.
}
```

#### I selettori CSS  

##### Tag
Il primo selettore da citare è il nome del tag stesso; usando come selettore il nome del tag le proprietà specificate verranno applicate a tutti gli elementi contenuti all'interno di quel particolare tag.

```css
a {
  color: red;
}
```

Nell'esempio mostrato prima tutti i link nella pagina saranno colorati di rosso.

##### ID
E' possibile anche selezionare un elemento particolare specificando come selettore l'id assegnatogli tramite l'attirbuto HTML `id` facendolo precedere da `#`.  

```css
#loginform{

  color: red;

}
```

In questo esempio tutti gli elementi contenuti nel tag identificato dall'id `loginform`avranno come colore del testo rosso.

##### Class
Analogamente all'id è possibile specificare il nome di una classe preceduto da `.`; in questo caso le regole di stile verranno applicate a tutti quegli oggetti contenuti in un tag con attributo `class` uguale a quello specificato.

```css
.contenuto{

  background-color: blue;

}
```

In questo caso tutti gli elementi contenuti in un tag con valore di `class` "contenuto" avranno come colore di sfondo il blu.

##### Pseudo-selettori
Esistono anche una serie di pseudo-selettori, che consentono di modificare le regole di stile degli elementi di un documento ipertestuale solo in alcuni casi; ad esempio

```css
a:hover{

  color: blue;

}
```

ha l'effetto di rendere il colore del link blu quando il mouse ci passa sopra.  

E' possibile inoltre specificare di applicare la regola di stile solo agli elementi contenuti in un tag all'interno di un altro selettore specificando questi selettori separati da spazio; ad esempio

```css
#loginform a{

  color: blue;

}
```

In questo caso solo i link contenuti nel tag con id "loginform" avranno il colore blue.

Allo stesso modo è possibile applicare la stessa regola di stile a più selettori contemporaneamente; basta specificare tali selettori seprandoli con una virgola.

```css
selettore1, selettore2 {

  #Regole di stile qui.

}
```

#### Importare i CSS
Normalmente si tende a specificare le regole di stile in un file esterno con estensione `.css`. Per importare tale file all'interno di un ipertesto bisogna utilizzare il tag `link`.

```html
<link rel="stylesheet" type="text/css" href="path/to/file.css">
```

Questo tag va inserito all'interno del tag `head`.

E' possibile inoltre specificare le regole di stile all'interno del tag `style`di HTML posizionabile all'interno del tag `head`, ma così facendo non è possibile riutilizzare lo stesso foglio di regole di stile.

#### Reference
Per una lista completa delle proprietà e degli pseudo selettori si rimanda alla [documentazione ufficiale di W3C](https://www.w3schools.com/cssref/).

### CSS framework
Per gestire il fatto che lo stesso documento ipertestuale deve poter essere visualizzato su più dispositivi con schermi e risoluzioni diverse sono nati dei framework che si prendono cura di questo.  
Nel corso viene mostrato [Bootstrap](https://getbootstrap.com/), ma ne esistono comunque molti altri.  
Poichè Bootstrap è molto complesso, si rimanda al [Tutoria di Bootstrap 4](https://www.w3schools.com/bootstrap4/default.asp) realizatto da W3School che è a mio avviso molto completo.


***

### JavaScript
JavaScript è un linguaggio di programmazione non tipizzato che viene interpretato dal client ( web browser ) al fine di rendere le pagine HTML dinamiche.

#### I tipi
JavaScript prevede solo cinque tipi di dato:  
  * **String**
  * **Number**
  * **Object**
  * **Boolean**
  * **Function**

Nella versione attuale dello standard è possibile definire delle classi tramite il costrutto `class`, però di fatto viene costruito un object.

#### Dichiarare una variabile
In JavaScript è possibile dichiarare una variabile tramite le keyword:
  * `var`, in questo caso sarà possibile modificare il valore della variabile
  * `const`, in questo caso non sarà possibile modificare il valore della costante in nessun punto dello script.
  * `let`, in questo caso il valore della variabile può essere modificato solo nello scope corrente.

```js
const name = "Claudio"
var age = 21
let married = false
```

#### Dichiarazione di una funzione
JavaScript adotta anche il paradigma di programmazione funzionale, infatti "Function" è un tipo di dato.
Esistono infatti due modi per definire una funzione:

  1. La keyword `function`
  ```js
  function saluta(nome){
    console.log("Ciao, " + nome)
  }
  ```
  2. La dichiarazione come variabile
  ```js
  let saulta = (nome) => console.log("Ciao, " + nome)
  ```
Queste due funzioni sono totalmente uguali, anche per quanto riguarda il metodo di chiamata.

#### Dichiarare una classe
Per dichiarare una classe si usa la keyword `class`

```js
class Persona{

  constructor(nome, cognome, dataDiNascita){

    this.nome = nome;
    this.cognome = cognome;
    this.dataNasctia = dataDiNascita;

  }

  saluta(){

    return "Ciao, sono " +this.nome+ " " + this.cognome;

  }

}

const claudio = new Persona("Claudio", "Facchinetti", "11/01/1998")
console.log(claudio.saluta())
```

Il metodo con cui JS gestisce le classi è singolare, infatti è possibile accedere a ciascuna sua proprietà o con la notazione `.` oppure con la notazione `[]`, in quanto la classe è gestita esattamente come una mappa.

```js
const claudio = new Persona("Claudio", "Facchinetti", "11/01/98")
console.log(claudio["saluta"]())
```

avrà lo stesso effetto del codice scritto sopra.  
Allo stesso modo è possibile anche aggiungere delle proprietà alle classi a runtime, anche se non è consigliabile farlo.

#### Interagire con la pagina HTML
Per interagire con il contenuto del documento ipertestuale si utilizza il DOM, ovvero una rappresentazione gerarchica ad albero che il browser usa per rappresentare il documento.

In particolare è possibile selezionare un singolo elemento identificandolo con il suo ID oppure tutta una serie di elementi che appartengono ad una classe specifica.

```js
document.getElementById("rettangolo").innerHTML="Io sono un rettangolo!"
```

Il codice mostrato sopra ha il compito di rimpiazzare il contenuto dell'elemento che risponde all'id `rettangolo`con la frase "Io sono un rettangolo!".

```js
const elements = document.getElementsByClassName("prova");
for( var i = 0; i < elements.length; i++ )
  elements[i].innerHTML="Prova"
```

Il codice mostrato rimpiazza il contenuto di tutti gli elementi di classe `prova` con la parola "Prova".

#### Includere JS
Per includere del codice JavaScript nella pagina è possibile:
  * Scriverlo direttamente tra i tag `script`
  * Importarlo da un file esterno const
  ```html
  <script type="text/javascript" language="javascipt" src="path/to/file.js">
  ```

#### Reference
Per un tutorial più approfondito e accurato di JS rimando a quello realizzato da [W3School](https://www.w3schools.com/js/).

Per la documentazione si rimanda a quella ufficiale di [MDN](https://developer.mozilla.org/it/docs/Web/JavaScript/Reference).

Una libreria molto utilizzata è Jquery, per la quale si rimanda al tutorial di [W3CSchools](https://www.w3schools.com/jquERY/default.asp)

#### Attenz
In questa pagina non si vuole fornire tutti gli strumenti per scrivere CSS, HTML e JavaScript, ma solo riassumere i concetti generali e fondamentali senza spingersi oltre.
