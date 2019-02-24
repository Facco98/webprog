## Prima lezione di laboratorio: Git

### Cosa contiene
  * Appunti.pdf: Gli appunti presi a lezione
  * comandi: Lista dei comandi mostrati a lezione con spiegazione
  * install.sh: File bash per installare git ( vedi sotto )

### Cose da fare
  1. Installare git
  2. Registrarsi su [GitLab](https://gitlab.com/users/sign_in#register-pane)
  3. Provare a usare git:  
     * Clonare la repo
     * Provare a committare
     * Provare a creare branch
  
### Installa git

  * #### Dal sito ufficiale
    * Puoi installare git seguendo le istruzioni riportate
     [qui](https://git-scm.com/book/it/v1/Per-Iniziare-Installare-Git)
     
  * #### Usando il file su questa repository ( solo per sistemi Unix-like )
  
    * Via wget
    ```shell
    wget -o ./install.sh https://raw.githubusercontent.com/Facco98/webprog/master/lab1-git/install.sh
    chmod +x ./install.sh
    ./install.sh
    rm -rf ./install.sh
    ```
    
    * Via curl
    ```shell
    curl -o ./install.sh https://raw.githubusercontent.com/Facco98/webprog/master/lab1-git/install.sh
    chmod +x ./install.sh
    ./install.sh
    rm -rf ./install.sh
    ```
    
### Git Locali

Dopo aver installato git, prova ad utilizzarlo.

  * ##### Inizializza una nuova repository locale
  	```shell
    git init
    ```
    Il comando mostrato sopra serve a inizializzare una nuova repository git nella cartella in cui siamo attualmente.  
    All'interno di questa cartella verrà creata la cartella ".git", che contiene tutti i database che servono per gestire la repository git.
   * ##### Popola la tua repository
     Crea un file, ad esempio il classico file main.swift che contiene solamente la stampa della stringa "Hello World" o un qualsiasi altro file
     
     ```shell
     echo "Swift.print(\"Hello World\")" > "main.swift"
     ```
     
     In alternativa puoi creare qualsiasi altro file; a titolo di completezza viene riportato il file anche in C++.
     
     ```cpp
     #include <iostream>
     
     using namespace std;
     
     int main(){
     
     	cout << "Hello World" << endl;
     	return 0;
     }
     ````
   * ##### "Committa" le tue modifiche
     Significa semplicemente dire a git che vuoi che ricordi la particolare versione dei file, o del singolo file nel nostro caso
     
     * Di a git di quali file vuoi che memorizzi la nuova versione
       E' possibile fare questo con il comando ```git add```, specificando tutti i file che si vuole aggiungere alla "staging area", ovvero a quella lista di file che devono essere modificati/aggiunti/tolti.  
       Il comando ```git add main.swift```aggiungera il file main.swift alla staging area.  
       In alternativa è possibile usare ```git add *```per aggiungere tutti i file.  
       
     * Di a git di registrare la modifica ( Committa le tue modifiche )
       Per fare questo si utilizza il comando ```git commit```, in particolare useremo l'opzione ```-m```per specificare la nota. E' importante specificare sempre una nota sulle modifiche, in modo da poter capire perchè e in cosa consistono tali modifiche.  
       Il comando commit consiste nella registrazione delle modifiche dei file presenti nella staging area.  
       
       ```shell
       git commit -m "Aggiunto il file main.swift"
       ````
	   
       Una scorciatoia molto utile è usare l'opzione ```-a```, in modo che tutti i file che sono stati modificati vengano aggiunti alla staging area direttamente, senza bisogno di usare il comando ```git add````.

	   ```shell
       git commit -a -m "Aggiunto il file main"
       ````
	   è equivalente a
       
       ```shell
       git add *
       git commit -m "Aggiunto il file main"
       ```
* Crea un nuovo branch
  Creare un nuovo branch significa semplicemente creare un percorso di sviluppo indipendente del progetto a partire da un altro branch. Di default ogni git presenta il branch "master", ovvero il ramo principale della nostra repository. Immaginiamo di dover sviluppare n nuove funzionalità; per farlo in parallelo possiamo creare n branch, uno per ogni funzionalità e poi far confluire tutti i cambiamente ( fare il merge ) nel ramo master.  
  Per creare un nuovo branch si utilizza il comando ```git branch```. Creiamo il branch per la prima funzionalità a partire dall'ultima versione di codice caricato sul ramo master.  
  Visto che non abbiamo altri branch saremo di default sul branch master, quindi possiamo procedere a creare il nuovo ramo che chiameremo "fun1" per semplicità.  
  
  ```shell
  git branch fun1
  ````

  Ora però se modifichiamo qualcosa questi cambiamenti avverranno sul branch master, per spostarci dobbiamo utilizzare il comando ```git checkout```, seguito dal nome del branch su cui vogliamo spostarci
  
  ```shell
  git checkout fun1
  ````

  Una scorciatoia molto utile consiste nel comando ```git checkout -b```, che crea automaticamente il branch e ci sposta su di esso.
  
  ```shell
  git checkout -b fun1
  ````

  Quando dico "ci sposta", quello che accade e che viene spostato il puntatore HEAD della nostra repository in un particolare punto. Il puntatore HEAD è quel particolare puntatore che ci dice su quale commit specifico su quale ramo stiamo operando.  
  Ogni commit è identificato da un codice hash univoco, mentre il ramo è identificato dal proprio nome all'interno della repository.
  
  Il procedimento per effettuare delle modifiche al nostro nuovo ramo è identico a prima, quindi modifichiamo il file main e committiamo i cambiamenti.
  
    * Versione Swift
    
      ```swift
      let name = "Claudio"
      Swift.print("Hello, \(name)")
      ````
	* Versione C++

	  ```cpp
      #include <iostream>

	  using namespace std;

      int main(){
		
        char* name = "Claudio"
   		cout << "Hello, " << name << endl;
   		return 0;
	  }
      ```
   Committiamo quindi le modifiche
  ```shell
  git commit -a -m "Aggiunta la variabile"
  ````
  
  Ora che abbiamo finito di sviluppare la nostra ipotetica nuova funzionalità possiamo far confluire i due rami, ovvero fare i merge dei due; per fare questo usiamo il comando ```git merge```, che prende le modifiche di un ramo e le porta in quello attuale.  
  Dobbiamo quindi prima spostarci sul branch master e poi fare il merge.
  
  ```shell
  git checkout master
  git merge fun1
  ```
  
  Ora che il nuovo branch non ci serve più possiamo anche eliminarlo.
  
  ```shell
  git branch -d fun1
  ````
  
* ### Git Remote
  Vai su GitLab e crea una nuova repository; questa è una repository remota.
  In questa pagina non viene trattata la generazione delle chiavi ssh per poter accedere alla git remota tramite protocollo sicuro, poichè la procedura per aggiungere una chiave ssh dipende da gestore a gestore; lascio il [link](https://docs.gitlab.com/ee/ssh/) alla guida specifica di GitLab poiche è il servizio che useremo in questo corso.
  
  
  * ##### Inizializzare una repository remota
    GitLab permette di inizializzare la repository remota con un file "README.md", ovvero un file di markdown in cui ci saranno tutte le informazioni sulla repository/sul progetto.
  
  * ##### Clona la repository remota
    Se la repository remota non è vuota allora puoi clonarla attraverso il comando ```git clone```; questo creerà una copia esatta della repository remota sulla propria macchina su cui potremo lavorare
    
    ```shell
    git clone <url>
    ```
  * ##### Parti da una repository locale
    Se hai gia un progetto organizzato in una repository locale allora puoi caricarlo direttamente in una repository remota.  
    Per fare ciò crea una nuova repository remota senza inizializzarla e utilizza il comando ```git remote``` per dire a git il corrispettivo remoto della tua repository locale.
    
    ```shell
    git remote add origin <url>
    ```
  * ##### Branch remoti
    Quando si ha una repository remota si hanno dei branch locali e dei branch remoti; solitamente il branch locale a è impostato per tenere traccia del branch origin/a, ovvero il corrispettivo branch remoto.
    
  * ##### Portarti al pari con una repository remota
    Per scaricare i dati di una repository remota e "aggiornare" la propria repository locale di lavoro si utilizza il comando ```git pull```, che si occupa di aggiornare tutti i file e i branch.
   
  * ##### Configura il tuo profilo git
    E' molto importante quando si parla di git remote sapere chi ha fatto determinate modifiche, visto che di solito più persone lavorano allo stesso progetto. Per identificare le varie persone bisogna settare il proprio profilo git, usando il comando ```git config```
    
    ```shell
    # Configura il vostro nome
    git config --global user.name "Claudio Facchinetti"
    
    # Configura il vostro indirizzo email
    git config --global user.email "devack@hotmail.it"
    ````
	
    E' molto importante che la mail sia la stessa che avete usato per la registrazione al servizio di hosting di repository remote ( GitLab ) nel nostro caso, perchè sarà quella con cui cercerà di autenticarsi quando scaricherete/invierete le modifiche. Se la lasciate vuota allora vi chiederà ogni volta username ( mail ) e password.
    
  * ##### Inviare i cambiamenti
  	Per aggiornare la repository locale con le nostre modifiche usiamo il comando ```git push```, ovvero inviamo ( "pushiamo" ) le nostre modifiche al server remoto.
    
    ```shell
    git push -u origin <branch>
    ```
    
    dove branch è il nome del ramo su cui stiamo lavorando.  
Ulteriori informazioni e comandi git possono essere trovati [sul sito ufficiale] (https://git-scm.com/doc).  
Per approfondire la conoscienza del funzionamento dei branch git potete dare un'occhiata a [questo](https://learngitbranching.js.org/) sito, dove sono presenti anche esercizi per famigliarizzare con git.
    
### Attenz.
Questo è solo un riassunto per entrare velocemente nel mondo git in base a quanto spiegato a lezione e non intende sostituirsi in alcun modo al materiale o al manuale ufficiale git.
