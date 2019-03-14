## Quarta lezione di laboratorio: Java Servlet e Form
Per gli argomenti trattati è necessario installare TomCat o un qualsiasi Web Server; per questo si rimanda alla pagina dei [tools](https://facco98.github.com/webprog/tools).

A seconda del vostro IDE e sistema operativo sarà necessario configurare diversamente il percorso di TomCat in modo da poter effettivamente lanciare i vostri esercizi/progetti.  

### Java Servlet
Una Servlet è un oggetto Java che consente di gestire delle richieste di rete; in particolare noi estenderemo la classe **HttpServlet**, ovvero quella servlet specializzata nella gestione delle richieste HTTP.  

```java
public class SimpleHttpServlet extends HttpServlet {

    // Si occupa di tutte le richieste passate col metodo GET.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

    // Si occupa di tutte le richieste passate col metodo POST.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

}
```

In particolare sono presenti gli oggetti **HttpServletRequest** e **HttpServletResponse** che rappresentano rispettivamente la richiesta HTTP ( e quindi anche dovo sono contenuti tutti i parametri della richiesta ) e la risposta ( dove potremo anche scrivere un eventuale risposta al client ).

### Elaborare un form
Per recuperare i dati passati alla nostra Servlet ci serviamo del metodo _getParameter(String)_ della classe _HttpServletRequest_.  

```java
var firstName = req.getParameter("first_name");
```

### Scrivere la risposta
Per scrivere la risposta dobbiamo prima di tutti restituire lo status della richiesta ( 200 nel caso sia andato tutto bene ), indicare che inviamo dell'HTML e infine inviare l'HTML.

```java
resp.setContentType("text/html");
resp.setStatus(200);
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));

// Invio l'HTML
output.flush();
output.close();
```

### web.xml
Un file molto importante è _web.xml_,  poiche in questo file sono presenti le informazioni per far capire al web server quale servlet deve chiamare per una specifica richiesta.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- Serve a dire al web server quali server deve caricare e come si chiamano -->
    <servlet>
        <servlet-name>Excercise1</servlet-name>
        <servlet-class>ex1.Exercise1</servlet-class>
    </servlet>

    <!-- Serve a dire al web server quale servlet risponde a un particolare URL -->
    <servlet-mapping>
        <servlet-name>Excercise1</servlet-name>
        <url-pattern>/ex1/compute</url-pattern>
    </servlet-mapping>

</web-app>
```

### Esercizi
  1. Realizzare una servlet che legge nome e cognome da un form e mostra una pagina HTML in cui li mostra.
  2. Realizarre una servlet che gestisca un form in cui sono presenti delle checkbox.
  3. Realizzare una servlet che legga e mostri in una pagina web tutti i parametri passati dal form.
