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
  * __<c:out value="val" />__  
    Viene sostituito con il valore dell'espressione contenuta nel parametro __val__.

    ```jsp
    <%! String nome = "Claudio" %>
    <p>Ciao, <c:out value="${nome}" /></p>
    ```
  * __<c:set value="val" var="v" scope="page"/>__  
    Non produce output; ha l'effetto di creare una variabile chiamata **v** con valore __val__ con scoping __page__.

    ```jsp
    <c:set value="Claudio" var="nome" scope="page" />
    <p>Ciao, <c:out value="${nome}" /></p>
    ```

  * __<c:if test="${a == b}">__  
    Il contenuto tra il tag **<c:if>** il corrispettivo tag di chiusura **</c:if>** viene valutato ( e eventualmente mostrato ) solo se il test ha avuto esito positivo.
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

  * __<c:choose>__  
    E' un tag che identifica un blocco in cui ci sono condizioni mutualmente esclusive identificate dai tag
      1. **<c:when test="${a==b}">**, mostrato solo se il test ha esito positivo.
      2. **<c:otherwhise>**, mostrato solo se nessuna delle altre clausole ha avuto successo.


    ```jsp
    <c:set value="5" var="a" scope="page" />
    <c:set value="6" var="b" scope="page" />
    <c:choose>
      <c:when test="${a==b}"> <p>a==b</p> </c:when>
      <c:otherwhise> <p> a!=b </p></c:otherwhise>
    </c:choose>
    ```
    In questo caso l'output sarà `a!=b`, in quanto il primo test fallirà.

  * __<c:forEach var="i" items="collection">__  
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

    Esiste inoltre la variante con un intervallo __<c:forEach var="i" begin="1" end="10" step="1">__.

  * __<c:forTokens items="${string}" delims="${del}" var="i">__  
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

 4. ##### SQL library
    Contiene i tag utili per l'interazione con database relazionali basati su SQL.
    Prefisso consigliato: **sql**

    ```jsp
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    ```
