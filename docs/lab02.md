## Seconda lezione di laboratorio: Networking in Java

Il pacchetto Java che contiene tutte le classi per le comunicazioni di rete di chiama **java.net**.  
Il pacchetot Java che contiene tutte le classi per gestire le operazioni di input/output si chiama **java.io**.  

#### Focus on: InetAddress
La classe InetAddress è contenuta nel package **java.net**.  
La classe InetAddress rappresenta un indirizzo di rete di un host; in particolare esistono due sottoclassi:
  * **Inet4Address**: Rappresenta un indirizzo IPv4
  * **Inet4Address**: Rappresenta un indirizzo IPv6  

##### Metodi statici
Non esistono costruttori pubblici, bisogna usare per tanto i metodi statici di factoring; in particolare esistono
  * ```java
    InetAddress localhost = InetAddress.getLocalHost();
    ```
    Questo metodo ritorna l'indirizzo della macchina su cui il codice è in esecuzione.
    
  * ```java
    InetAddress googleAddress = InetAddress.getByName("google.com");
    ````
    Questo metodo ritorna l'indirizzo IP associato al dominio passato come parametro.
    
  * ```java
    InetAddress[] googles = InetAddress.getAllByName("google.com");
    ```
    Questo metodo ritorna tutti gli IP associati al dominio passato come parametro, sia IPv4 che IPv6.
    
Tutti questi metodi utilizzano il servizio DNS configurato sulla macchina per risolvere i domini.

##### Metodi d'istanza
La classe InetAddress mette a disposizione molti metodi utili nello sviluppo di applicazioni di rete, in particolare  
  * I **getter**
    ```java
    InetAddress localhost = InetAddress.getLocalHost();
    // ritorna il nome dell'host sulla rete
    String hostName = localhost.getHostName();
    
    // ritorna l'indirizzo ip dell'host sotto forma di stringa
    String address = localhost.getHostAddress();
    
    // ritorna il nome canonico dell'host
    String canonicalName = localhost.getCanonicalName();
    ```
  * Metodi di rete
    ```java
    // In questo caso uso il factory method di Inet4Address perchè voglio un IPv4
    InetAddress google = Inet4Address.getByName("google.com");
    
    // Ritorna se è un IP locale, ovvero se la consegna dei pacchetti IP è diretta o no
    boolean isLocal = google.isAnyLocalAddress();
    
    // Ritorna se l'indirizzo è raggiungibile nel lasso di tempo specificato.
    boolean isReachable = google.isReachable(1000);
    
    // Ritorna se l'indirizzo è un indirizzo di multicast o no.
    boolean isMulticast = google.isMulticastAddress();
    ```
    
---

#### Focus on: Socket
La classe Socket è contenuta nel pacchetto **java.net** e rappresenta una socket che utilizza TCP come protocollo a livello trasporto.  

##### Costruttori
Per creare una socket è sufficiente utilizzare uno dei suoi costruttori; i piu semplici sono
  * ```java
    InetAddress google = Inet4Address.getByName("google.com");
    Socket toGoogleSocket = new Socket(google,8080);
    ```
  * ```java
    Socket toGoogleSocket = new Socket("google.com", 8080);
    ```

##### I/O su Socket
Per effettuare operazioni di I/O sulle socket possiamo utilizzare gli stessi meccanismi che useremmo per leggere l'input da tastiera e stampare a video in Java.
In particolare per leggere da una socket useremo la classe **BufferedReader** e per scrivere useremo la classe **BufferedWriter**.

###### Esempio
In questo esempio un client si connette a un server, manda un messaggio "PING" e il server risponde con un messaggio "PONG". Il server verrà mostrato successivamente
```java
InetAddress localhost = InetAddress.getLocalHost();
Socket socket = new socket(localhost, 8080);

// Creo l'oggetto per poter scrivere sulla socket.
BufferedWriter outToSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

// Scrivo il messaggio e aggiungo un carattere di "nuova riga"
outToSocket.write("PING");
outToSocket.newLine();

// Impongo l'invio anche se il buffer non è piento e chiudo il writer.
outToSocket.flush();
outToSocket.close();

// Creo l'oggetto per leggere dalla socket.
BufferedReader inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

// Leggo la stringa e la stampo.
String fromServer = inFromSocket.readLine();
System.out.println(fromServer);

// Chiudo il reader e la socket.
inFromSocket.close();
socket.close();
```

---

#### Focus on: ServerSocket
La classe ServerSocket è parte del pacchetto **java.net** e, come suggerisce il nome, rappresenta una socket lato server. In particolare è un oggetto che si mette in ascolto su una determinata porta per accettare le richieste di connessione dei client che utilizzano la classe Socket.

##### Costruttori
```java
// Creo una ServerSocket in ascolto sulla porta 9090
ServerSocket serverSocket = new ServerSocket(9090);
```

##### Metodi
Oltre ai metodi getter che consentono di avere informazioni sulla configurazione di rete della macchina è importante il metodo **accept()**, poichè è quel metodo che consente di accettare connessioni.  
Questo metodo è bloccante, ovvero quando viene chiamato l'applicazione si ferma in attesa di una richiesta di connessione e quando la riceve ritorna una socket su cui sarà possibile comunicare.

###### Esempio
Riportiamo qui a titolo di esempio il server con cui il client mostrato in precedenza interagisce.  

```java

// Creo la server socket e mi metto in attesa di connessione sulla porta 8080.
ServerSocket serverSocket = new ServerSocket(8080);
Socket socket = serverSocket.accept();

// Creo gli oggetti per leggere e scrivere dalla/sulla socket.
BufferedReader inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

BufferedWriter outToSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

// Scrivo il messaggio
String fromClient = inFromSocket.readLine();
if(fromClient.trim().toUpperCase().equals("PING")){
	outToSocket.write("PONG");
} else{
	outToSocket.write("ERROR: Expected PING, received "+ fromClient);
}

outToSocket.newLine();
outToSocket.flush();

// Chiudo tutti gli oggetti.
inFromSocket.close();
outFromSocket.close();

socket.close();
```

Una versione completa dell'esempio può essere trovata su questa repository nella cartella della seconda lezione con tutti i sorgenti degli esercizi assegnati.

---

#### Esercizi assegnati
  1. Creare un applicativo che stampi a video il nome della macchina su cui è in esecuzione
  2. Creare un applicativo che esegua l'NSLookup di un indirizzo, ovvero dato un nome ritorni tutti gli indirizzi IP assegnati su DNS.
  3. Creare un server HTTP che risponda alle richieste GET e ritorni il file richiesto
  4. Migliorare il server HTTP in modo che possa:
     1. Gestire anche la richiesta di cartelle mostrando tutti i file contenuti in esse.
     2. Ritornare solo i file a cui si ha veramente accesso, ovvero solo quelli relativi alle sottocartelle del percorso di esecuzione del server
     3. Ottimizzare la memoria usata dal server evitando di salvare tutti il file in memoria prima di mandarlo al client.

Gli esercizi contrassegnati con **-X** non sono ancora disponibili su questa repository.
