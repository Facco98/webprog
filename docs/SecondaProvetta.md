# Appunti per il secondo parziale

## JSP
JSP è un acronimo che sta per JavaServer Pages; si tratta di una tecnologia che permette la creazione di documenti ipertestuali dinamici tramite l'inserimento di particolari tag ( tag JSP ) che verranno poi interpretati dal JSP container per generare una pagina HTML standard dinamica.

Alla richiesta della risorsa il JSP container genera automaticamente una servlet Java il cui output sarà esattamente la pagina JSP con la sostituzione dei tag JSP la loro interpretazione.

La servlet così generata rimane "in vita" fino allo spegnimento del web server ( o altre politiche specifiche ), come una normalissima servlet.

 * ### JSP initialization
  Momento in cui vengono dichiarate tutte le risorse che verranno usate dalla pagina JSP; fatto implementando il metodo `jspInit()` dell'interfaccia `JspPage`.

  ```jsp
  <% -- pagina -- %>
  <%!
    public void jspInit(){
      // init code
    }
  %>
  <% -- pagina -- %>
  ```

 * ### JSP finalization
  Momento in cui vengono rilasciate tutte le risorse utilizzate dalla pagina JSP; fatto implementando il metodo `jspDestroy()` dell'interfaccia `JspPage`.

  ```jsp
  <% -- pagina -- %>
  <%!
    public void jspDestroy(){
      // finalization code
    }
  %>
  <% -- pagina -- %>
  ```

 * ### Tag JSP
  La dinamicità delle pagina JSP è data da particolari tag che vengono mescolati a quelli HTML standard e che saranno eseguiti dal servlet container; vengono chiamati tag JSP. I tag si differenziano a seconda della funzione che hanno; in particolare ci sono:
  1. ##### Commenti
      A differenza del commento HTML questo non è presente nella pagina inviata al browser.
      ```jsp
      <% -- commento -- %>
      ```
  2. ##### Dichiarazioni
      Dichiara variabili o metodi a cui si può accedere dal resto della pagina.
      ```jsp
      <%! String nome = "Claudio"; %>
      <%! public String getNome(){ return nome; } %>
      ```
  3. ##### Direttive
      Tag usato per passare informazioni al JSP container; non generano output.
      ```jsp
      <%@ page import="java.util.*" %>
      <%@ inlclude file="anotherPage.html" %>
      <%@ taglib uri="<libURI>" prefix="p" %>
      ```
      Il secondo esempio mostrato è un include che viene risolto a compile time, quindi permette di includere solo risorse statiche.

  4. ##### Espressioni
      Tag usato per inserire all'interno della pagina la valutazione ( in forma di stringa ) di un codice Java.
      ```jsp
      Ciao, <%= getNome() %>
      ```
      Facendo riferimento a quanto mostrato nell'esempio delle dichiarazioni JSP, questo esempio produrrà come output `Ciao, Claudio`.

  5. ##### Scriptlet
      Tag usato per incorporare nella pagina del codice Java puro; molto veloce per i programmatori ma da evitare assolutamente in quanto è poco comprensibili a coloro che non hanno famigliarità con la tecnologia JSP o con Java in generale. Viene mostrato solo perchè esiste.

      ```jsp
      <%
        // Recupero il nome passato nella richiesta
        String nome = request.getParameter("nome");
        // Stampo il nome a video.
        out.println("<b>Ciao, " + nome + "</b>");
      %>
      ```

  6. ##### Azioni
      Tag usato per dare direttive a runtime al JSP container.
      ```jsp
      <jsp:include page="anotherPage.jsp" />
      <jsp:forward page="continue.jsp" />
      ```
      Il primo esempio è un include fatto a runtime, mentre il secondo esempio è un redirect alla pagina `continue.jsp` mantenendo gli stessi oggetti rappresentanti la richiesta e la risposta.

 * ### Oggetti impliciti
  Ogni pagina JSP dichiara automaticamente degli oggetti, essendo di fatto poi eseguita come una normale servlet. Gli oggetti dichiarati da ogni pagina JSP sono:
  * ##### request
      Oggetto istanza di `HttpServletRequest` che rappresenta la richiesta ricevuta.

  * ##### response
      Oggetto istanza di `HttpServletResponse` che rappresenta la risposta che sarà inviata al client.

  * ##### out
      Oggetto istanza di `PrintWriter` che permette di scrivere direttamente sulla risposta che il client riceverà.

  * ##### session
      Oggetto istanza di `HttpSession` che rappresenta la sessione associata alla richiesta.
  * ##### exception
      Oggetto che contiene un'eccezione lanciata e non catturata. Disponibili solo all'interno di quelle pagine che utilizzano la direttiva

      ```jsp
      <%@ page isErrorPage=true %>
      ```

  * ##### page
      Oggetto istanza della servlet Java che sta processando la pagina ( è un sinonimo di `this`).

  * ##### pageContext
      Oggetto che contiene i metodi per accedere ad alcuni dati specifici della pagina.

  * ##### application
      Oggetto che permette di accedere al contesto della sevrlet.

  * ##### config
      Oggetto che permette di accedere alla configurazione della servlet.


 * ### Scope
    All'interno della pagina le variabili dichiarate possono avere uno scope diverso; ad esempio quelle dichiarate con i tag di dichiarazione hanno come scope **page**, il che significa che sono visibili all'interno dell'intera pagina dal momento in cui sono dichiarate.  
    I diversi scope sono:
  1. #### **Page**
        Le variabili sono visibili all'interno della pagina.
  2. #### **Request**
        Le variabili sono visibili all'interno di tutte le pagine che gestiscono lo stesso oggetto **request**, ad esempio quelle chiamate con `<jsp:forward />`.
  3. #### **Session**
        Le variabili sono visibili all'interno di tutte le pagine che condividono la stessa sessione Http.
  4. #### **Application**
        Le variabili sono visibili a tutte le pagine dell'applicazione web.

## JSTL
JSTL è un acronimo che sta per Java Standard Tag Library e, come suggerisce il nome, è una libreria contenente molti tag JSP per svolgere le azioni più comuni.

JSTL viene usato come sistema di **templating**, ovvero al testo della pagina vengono mescolati dei segnaposto, elementi speciali che verranno poi sostituiti con i dati effettivi.

Oltre a questo contiene anche un framework per la creazione di tag JSP personalizzati.

 * ### Custom tag framework
  Questo framework permette di creare dei tag personalizzati per eseguire nuove azioni quando richiamati all'interno di pagine JSP.

  1. ##### Tag library
      E' il "pacchetto" che rappresenta la libreria; una libreria, di solito, contiene molti tag che possono essere usati individualmente o avere dipendenze particolari tra loro. Di solito viene distribuita come archivio JAR.
  2. ##### Tag-library descriptor
      E' un file con estensione __.tld__ e con sintassi XML; contiene la sintassi del tag e le informazioni necessario al web container per gestire tali tag.

  3. ##### Tag handler
      Una classe Java contenente la logica necessaria all'esecuzione di un particolare tag. Tale classe deve implementare una specifica interfaccia Java per essere riconosciuta valida e gestita correttamente dal web container.

JSTL utilizza uno speciale linguaggio per accedere al valore delle variabili, chiamato **Expression Language**, molto simile alla sintassi utilizzata da JavaScript per accedere alle proprietà/metodi degli oggetti. Questo linguaggio ha valore solo nel caso in cui sia in uno dei tag JSTL.

Per accedere al valore di una variabile si usa la notazione `${variabile}`; può essere usato anche per valutare un'espressione matematica `${4+1}`.

Per accedere posizionalmente agli elementi di un vettore si può usare la notazione `${vettore[i]}`, dove i indica l'indice numerico.  

Per accedere agli elementi contenuti in una HashMap tramite la chiave sono possibili due diverse notazioni:
 1. `${map.key}`
 2. `${map["key"]}`


Le Java Standard Tag Libraries si dividono, principalmente in cinque pacchetti:
 1. ##### Core library
    Contiene i tag più usati, come quelli per eseguire azioni logiche ( if, choose ) e per iterare gli oggetti.
    Prefisso consigliato: **c**

    ```jsp
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    ```
    
    ###### Tag principali
    
    * __&lt;c:out value="val" /&gt;__  
    Viene sostituito con il valore dell'espressione contenuta nel parametro __val__.

      ```jsp
      <%! String nome = "Claudio" %>
      <p>Ciao, <c:out value="${nome}" /></p>
      ```
    * __&lt;c:set value="val" var="v" scope="page"/&gt;__  
    Non produce output; ha l'effetto di creare una variabile chiamata **v** con valore __val__ con scoping __page__.

      ```jsp
      <c:set value="Claudio" var="nome" scope="page" />
      <p>Ciao, <c:out value="${nome}" /></p>
      ```

    * __&lt;c:if test="${a == b}"&gt;__  
    Il contenuto tra il tag **&lt;c:if&gt;** il corrispettivo tag di chiusura **&lt;/c:if&gt;** viene valutato ( e eventualmente mostrato ) solo se il test ha avuto esito positivo.
    E' possibile passare un altro argomento chiamato **var**; nel caso presente l'esito del confronto viene salvato in una variabile chiamata come il valore del parametro var e con scoping corrispondente a quello specificato con il parametro **scope**.

      ```jsp
      <c:set value="5" var="a" scope="page" />
      <c:set value="6" var="b" scope="page" />
      <c:if test="${a==b}" var="esito" scope="page">
        <c:set value="Claudio" var="nome" scope="page" />
        <p>Ciao, <c:out value="${nome}" /></p>
      </c:if>
      <p>Esito: <c:out value="${esito}" /></p>
      ```

    Nel caso mostrato il paragrafo che saluta non viene mostrato, in quanto ${a==b} viene valutato a false.  
    Viene inoltre mostrato `Esito: false` alla fine della pagina.

    * __&lt;c:choose&gt;__  
    E' un tag che identifica un blocco in cui ci sono condizioni mutualmente esclusive identificate dai tag
      1. **&lt;c:when test="${a==b}">**, mostrato solo se il test ha esito positivo.
      2. **&lt;c:otherwhise&gt;**, mostrato solo se nessuna delle altre clausole ha avuto successo.


      ```jsp
      <c:set value="5" var="a" scope="page" />
      <c:set value="6" var="b" scope="page" />
      <c:choose>
        <c:when test="${a==b}"> <p>a==b</p> </c:when>
        <c:otherwhise> <p> a!=b </p></c:otherwhise>
      </c:choose>
      ```
    In questo caso l'output sarà `a!=b`, in quanto il primo test fallirà.

    * __&lt;c:forEach var="i" items="collection"&gt;__  
    Consente di eseguire il contenuto dei tag per ogni elemento contenuto nella variabile `collecion`, accedendo ogni volta all'elemento corrente tramite la vriabile `i`.

      ```jsp
      <%! String[] nomi = new String[]{"Claudio", "Francesco"}; %>
      <:forEach var="i" items="nomi">
        <p>Hello, <c:out value="${i}" /> </p>
      </c:forEach>
      ```
    Il risultato di quanto mostrato soprà sarà  
      ```
      Hello, Claudio
      Hello, Francesco
      ```

    Esiste inoltre la variante con un intervallo __&lt;c:forEach var="i" begin="1" end="10" step="1"&gt;__.

    * __&lt;c:forTokens items="${string}" delims="${del}" var="i"&gt;__  
    Consente di dividere la stringa `string` ogni volta che si incontra la stringa `del`, salvare questa sottostringa nella variabile `i` e eseguire ciò che è contenuto tra i tag di apertura e chiusura.

    E' possibile inoltre specificare un indice da cui partire e uno a cui fermarsi tramite i parametri `begin` e `end`.

      ```jsp
      <c:forTokens items="a,b,c,d,e,f" delims="," var="letter">
          <p><c:cout value="${letter}" /></p>
      </c:forTokens>
      ```

    Il risultato dell'esempio sarà
      ```
      a
      b
      c
      d
      e
      f
      ```


 2. ##### XML library
    Contiene i tag utili per processare e fare il validamento dei documenti basati su XML. Prefisso consigliato: **x**

    ```jsp
    <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    ```

 3. ##### Formatting library
    Contiene i tag utili per l'internazionalizzazione delle pagine e per la formattazione dei dati, come ad esempio data/ora e valute.
    Prefisso consigliato: **fmt**

    ```jsp
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    ```

    ###### Tag principale
    *  __&lt;fmt:message key="your.key" /&gt;__  
      Questo tag è utilizzato per l'internazionalizzazione delle applicazioni usando un **ResourceBundle**, restituendo il valore corrispondente alla chiave passata.  
      Un ResourceBundle è una classe Java che gestisce un file di testo contenente oggetti specifici per la zona, come ad esempio i messaggi tradotti in una specifica lingua. Questa classe carica i file di testo con estensione __.properties__, contenenti delle coppie chiave-valore come segue; nel caso si voglia inserire dei separatori ( come spazi o tab ) bisogna farne l'escape.
        ```
        saluto=Ciao
        benvenuto=Benvenuto
        frutta\ e\ verdura=Frutta e verdura.
        ```
        Il nome del file è così composto: `<nome>_xx.properties`, dove `<nome>` può assumere qualsiasi valore noi vogliamo, mentre `xx`deve essere l'identificativo di una lingua secondo lo standard ISO 639.  
        Esempi:
          * messages_it.properties
          * messages_en.properties

        Per usare questo in un'applicazione Web creata tramite servelt e derivate bisogna specificare il ResourceBundle che si vuole utilizzare nel file `web.xml`.

        ```xml
        <context-param>
          <param-name>javax.servlet.jsp.jstl.fmt.localizationContext</param-name>
          <param-value> <!-- Inserire il percorso ( package ) COMPLETO del resource bundle --> </param-value>
        </context-param>
        ```

        In alternativa si può impostare il ResourceBundle da usare in ogni pagina JSP

        ```jsp
        <fmt:setBundle baseName="path" />
        ```
        sostituendo al posto di `<path>` il percorso ( come package ) completo del resource bundle che si vuole usare.

 4. ##### SQL library
    Contiene i tag utili per l'interazione con database relazionali basati su SQL.
    Prefisso consigliato: **sql**

    ```jsp
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    ```
## Pattern MVC
Un pattern è un metodo di organizzazione del lavoro sviluppato per risolvere specifici problemi e favorire la migliore organizzazione, manutenzione e riutilizzabilità del codice.

Il pattern MVC ( Model-View-Controller ) è un pattern sviluppato per la creazione di applicazioni che si devono interfacciare con gli utenti. Tale pattern definisce tre livelli architetturali:
  1. Model  
  E' il livello che si occupa della gestione dei dati, ovvero della persistenza; è suo compito infatti recuperare dati ( da un eventuale databse da esempio ), salvare le modifiche e garantirne la consistenza e correttezza.

  2. View  
  E' il livello che si occupa della presentazione dei dati all'utente e con cui quest'ultimo interagisce.

  3. Controller  
  E' il livello che si occupa di implementare la logica dell'applicazione; in parole povere è il livello che si occupa di elaborare i dati dal livello Model e di fornirli al livello View perchè siano mostrati agli utenti.

## JavaBeans
Una classe JavaBean è una classe Java il cui unico scopo è quello di modellare le informazioni che vengono scambiate tra il livello View e il livello Control: è semplicemente un contenitore per i dati in transito.

Deve avere:
  1. Un costruttore di default, senza argomenti
  2. Solo campi `private` o `protected`
  3. Metodi getter e setter per ogni campo

Per utilizzare un Bean in una pagina è necessario includerlo.  
```jsp
<jsp:useBean id="var" class="pathToClass" />
```

Questa azione creerà una variabile chiamata `var` che sarà un'istanza della classe specificata. E' possibile modificare le proprietà di tale variabile con la seguente azione:

```jsp
<jsp:setProperty name="var" property="proprieta" value="valore" />
```

Questa azione assegna alla variabile di istanza `proprieta` del bean referenziato da `var` il valore `valore`.

E' possibile anche inizializzare un bean a partire da dei parametri ricevuti in **request**, come ad esempio da un form. Per fare questo è possibile usare una delle seguenti sintassi:
  1. `<jsp:setProperty name='var' property='proprieta' param='p' />`  
    Questa azione setterà il valore di `var.proprieta` al valore contenuto nel parametri `p` presente in **request**

  2. `<jsp:setProperty name='var' property='proprieta' />`  
    Questa azione setterà il valore di `var.proprieta` al valore contenuto nel parametro `proprieta`presente in **request**; se tale parametro non esiste questa azione non avrà alcun effetto.

  3. `<jsp:setProperty name='var' property='*' />`  
    Questa azione tenterà di eseguire il comportamento di quella precedentemente mostrata per ogni proprietà del bean referenziato da `var`.


E' possibile recuperare il valore di una proprietà con la seguente azione:
```jsp
<jsp:getProperty name="var" property="proprieta" />
```
Questa azione ritorna il valore della variabile di istanza `proprieta` del bean referenziato dalla variabile `var`.


## JavaScript
JavaScript è un linguaggio di scripting interpretato nato per rendere dinamiche le pagine HTML che, sino alla sua introduzione, erano statiche una volta create.

### Tipi
In JavaScript esistono pochissimi tipi fondamentali:
  * Number
    Rappresenta un numero, indipendentemente che esso sia intero o no. Un numero in JavaScript è rappresentato con lo standard IEEE 754 a 64 bit.

  * String
  * RegExp
  * Boolean
  * Object
  * Array
    E' possibile aggiungere un elemento a un array tramite il metodo `push`.
  * Function
  * null
    Rappresenta un valore "vuoto" per un oggetto volutamente definito così.
  * undefined
    Rappresenta un valore non definito; si ottiene quando si tenta di accedere a una variabile non definita nello scope.

### Dichiarare una variabile
Per dichiarare una variabile si utilizza la parola chiave `var`; è importante notare che quando si dichiara una variabile non è necessario specificarne il tipo, in quanto JavaScript è dinamicamente tipizzato.

```javascript
var nome = "Claudio";
var cognome = "Facchinetti"
```

Come molti dei linguaggi moderni, il punto e virgola alla fine di ogni riga è opzionale se è presente una sola istruzione per riga.  

E' possibile usare anche le parole chiave `let` e `const` per dichiarare delle variabili.

#### const
Le variabili dichiarate con questa parola chiave sono immodificabili, ovvero non sono variabili ma delle costanti

```javascript
const pi = 3.14;
pi = 3; // Errore a runtime.
```

#### let
Le variabili dichiarate con questa parola chiave sono visibili solo nel blocco di codice che le dichiara.

```javascript
// code
for( let i = 0; i < 10; i++ ){

  // qui posso usare la variabile i

}

// qui la variabile i non esiste più.
```

La differenza tra let e var sta nel fatto che originariamente JavaScript utilizzava il concetto di scope solo per le funzioni, quindi non esisteva uno scope diverso per i cicli for, ad esempio.

```javascript
//code

for( var i = 0; i < 10; i++ ){

  // qui la variabile i esiste

}

// qui la variabile i continua ad esistere
```

### Comparazione
In JavaScript gli operatori di comparazione sono gli stessi che in C e Java, con l'unica aggiunta dell'operatore `===` e ovviamente della sua negazione `!==`

#### `==` vs `===`
Il doppio uguale fa un confronto tentando una conversione di tipo se i due oggetti sono di tipi diversi; il tripo uguale, invece, non tenta nessuna conversione di tipo e se sono di tipi diversi semplicemente ritorna false.

```javascript
let a = 10;
let str = "10";

let doppioUguale = ( a == str ) // true
let triploUguale = ( a === str ) // false
```

### Controllo di flusso e cicli
Le strutture di controllo e di iterazione sono uguali a quelle presenti in C e Java, con l'unica eccezione dell'istruzione `for obj in array` e `for elem of numerable`.

```javascript
let collection = [10,20,30];
for( let i of collection ){
  console.log(i); // verrà stampato "10 20 30"
}

collection.forEach((element, index, array) => console.log(element)); // è equivalente al ciclo mostrato sopra

let obj = {"uno": "a", "due": "b"}
for( let p in obj ){
  console.log(obj[p]) // Verrà stampato "a b"
}
```

### Dichiarare una funzione
Per dichiarare una funzione esistono due metodi; il primo con l'utilizzo della parola chiave `function`, il secondo con le `arrow functions`.

#### function
```javascript
function media(a,b){

  return (a+b)/2;

}
```

Da notare come non bisogna specificare il tipo di ritorno e neppure il tipo degli argomenti passati.

#### arrow functions
Poichè in JavaScript le funzioni sono degli oggetti è possibile fare assegnamenti di tali funzioni a delle variabili, come si farebbe nei linguaggi di programmazione funzionali.

```javascript
let media = (a,b) => (a+b)/2;
```

Questo stile dichiarativo è molto simile a quello adottato dalle lambda-expression di Java o nei linguaggi puramente funzionali come SML.

### Chiamare una funzione
La chiamata di una funzione è uguale a linguaggi come C e Java.
```javascript
let a = 5;
let b = 3;
let avg = media(a,b); // avg = 4
```

Lo stesso si applica alle funzioni dichiarate con la seconda notazione.
```javascript
let a = 5;
let b = 3;
var media = (a,b) => (a+b)/2;
let avg = media(a,b); // avg = 4
```

Poichè JavaScript non fa nessun tipo di controllo dei tipi, è possibile passare anche più o meno valori alle funzioni e questo non causa errori ma solo dei comportamenti inaspettati.

```javascript
let a = 5;
let b = 3;
let avg = media(a,b, 9); // avg = 4, il terzo parametro viene ignorato
```

```javascript
let a = 5;
let b = 3;
let avg = media(a); // avg = NaN, la conversione di undefined a Number genera il valore NaN ( Not a Number ).
```

Ogni funzione dichiara implicitamente il vettore degli argomenti `arguments`. In alternativa è possibile usare la notazione `...variabile`, per fare in modo che la lista degli argomenti rimanenti venga convertita in vettore con nome `variabile`.

```javascript

function media(){

  let somma = 0;
  for( let i = 0; i < arguments.length; i++ )
    somma += arguments[i];
  return (somma / arguments.length);
}

let a = 5;
let b = 3;
let avg = media(a,b); // avg = 4
```

```javascript

function media(...args){

  let somma = 0;
  for( let i = 0; i < args.length; i++ )
    somma += args[i];
  return (somma / args.length);
}

let a = 5;
let b = 3;
let avg = media(a,b); // avg = 4
```

Poichè ogni funzione è un oggetto, è possibile anche chiamarla con la seguente sintassi, specificando il vettore degli argomenti.

```javascript

function media(...args){

  let somma = 0;
  for( let i = 0; i < args.length; i++ )
    somma += args[i];
  return (somma / args.length);
}

let avg = media.apply(null, [4,6,8]) // avg = 6

```

### Oggetti
JavaScript mette a disposizione il tipo Object, con la possibilità di aggiungervi proprietà; si può pensare a un Object come a una HashMap di Java.

```javascript
let claudio = {
  "nome": "Claudio",
  "cognome": "Persona",
  "liveIn": "Trento",
};


claudio.nome  // "Claudio"
claudio["cognome"] // "Facchinetti"
```
Come mostrato è possibile accedere alle proprietà di un oggetto con la notazione `obj.propriety`; è possibile anche accedervi con la notazione `obj["propriety"]`.


### Classi
Storicamente le classi JavaScript sono implementate tramite funzioni, perchè all'inizio non vi era il supporto diretto per questa funzionalità.
L'implementazione tramite funzione sfrutta lo scoping delle funzioni e il fatto che si possono assegnare funzioni alle variabili per poi chiamarle con la notazione `.`.

```javascript

function Persona(nome, cognome){

  let p = {
    "nome": nome,
    "cognome": cognome,
    "saluta": () => {return "Hi, I'm " + this.nome + " " + this.cognome}
  }

  return p;

}

p = Persona("Claudio", "Facchinetti");
p.saluta // Hi, I'm Claudio Facchinetti

```

Con le nuove versioni di JavaScript sono state introdotte le classi come quelle di Java, con anche la possibilità di creare classi che ereditano da altre; cosa molto più complicata con l'implementazione tramite funzioni.

```javascript
class Persona{

    constructor(nome, cognome){

      this.nome = nome;
      this.cognome = cognome;

    }

    saluta(){

      return "Hi, I'm " + this.nome + " " + this.cognome;

    }

}

p = new Persona("Claudio", "Facchinetti");
p.saluta() // Hi, I'm Claudio Facchinetti
```

### Operatore +
In JavaScript l'operatore `+` è usato sia per la somma tra numeri che per la concatenazione di stringhe e questo può portare a problemi se non usato con cognizione di causa.  
Prima di tutto bisogna dire che tale operatore è binario, quindi ha bisogno di due operatori.
  1. Se uno dei due operatori è una stringa, allora si tenterà di convertire anche l'altro a stringa.
  2. Se uno dei due operatori è un numero e l'altro non è una stringa si tenta di convertire anche l'altro in un numero.
  3. In tutti gli altri casi, si tenta di convertire tutti gli operatori in numeri.

```javascript

let result = 10 + 1 // result = 11
result = 1 + "str" // result = 1str
result = 1 + 2 + "str" // result = 3str
result = "str" + 1 + 2 // result = str12

result = {} + [] // result = 0
result = {} + 1 // result = 1
result = {} + "Ciao" // result = NaN
result = "Ciao" + {} // result = Ciao[object Object]
```

#### [WAT a language](https://www.destroyallsoftware.com/talks/wat)
Si parla di JS circa al minuto 1:20, mostra alcuni assurdi dell'operatore `+` con ironia.

