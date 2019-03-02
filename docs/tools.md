## Tools

### Installare git
Per installare git si rimanda alla [prima lezione](https://facco98.github.io/webprog/lab01) di laboratorio

### Installare Java
Poiche il corso volge su Java dobbiamo installare il JDK ( "Java Development Kit" ). E' richiesta la versione 8 o superiore.

In questo caso verrà mostrato come installare [OpenJDK](https://openjdk.java.net/), ovvero il JDK open source; nulla vita di installare anche il JDK di Oracle.
  * ##### Windows 
    Per installare OpenJDK su piattaforma Windows si rimanda a [questa](https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows) pagina di StackOverflow.
  * ##### macOS
    Per installare OpenJDK su un sistema Mac è possibile
      * Scaricare il binario dal sito ufficiale e spostare il contenuto dell'archivio in `/Libreria/Java/JavaVirtualMachines/`
      * In alternativa è possibile usare [homebrew](https://brew.sh/index_it) digitando
        ```shell
        brew cask install adoptopenjdk
        ```
   * ##### Linux
     E' possibile installare OpenJDK dal proprio gestore di pacchetti.  
     In particolare per i sistemi Debian-based ( ad esempio Ubuntu ) basta digitare  
     `sudo apt install default-jdk`
     
     ###### Attenz
     Nel caso in cui si stia utilizzando Ubuntu 18.04.xx o precedenti se si tenta di installare OpenJDK con il comando mostrato sopra si avrà errore, in quanto nella repository è presente il pacchetto denominato openjdk-11 ma contiene di fatot la versione 10.  
     Per installare OpenJDK 11 su queste versioni sarà necessario farlo manualmente. Per farlo basterà copiare-incollare i comandi qui riportati.
     
     ```shell
     wget https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz -O /tmp/openjdk-11.0.2.tar.gz
     sudo tar xfvz /tmp/openjdk-11.0.2.tar.gz --directory /usr/lib/jvm
     rm -f /tmp/openjdk-11.0.2.tar.gz
     
     sudo sh -c 'for bin in /usr/lib/jvm/jdk-11.0.2/bin/*; do update-alternatives --install /usr/bin/$(basename $bin) $(basename $bin) $bin 100; done'
     sudo sh -c 'for bin in /usr/lib/jvm/jdk-11.0.2/bin/*; do update-alternatives --set $(basename $bin) $bin; done'
     ```
     
     Nel momento in cui OpenJDK 11 comparirà sulla repo ufficiale di Ubuntu 18.04 o precedenti, basterà upgradare il default-jdk e cambiare la versione con `upgrade-alternatives`, scegliendo tra le opzioni in lista la versione corretta del JDK.
     
     ```shell
     sudo apt install --only-upgrade default-jdk
     update-alternatives --config java
     ```
     
     Per altre distribuzioni Linux si rimanda alle varie repository e relativi gestori di pacchetti.

### Installare un web server
Per questo corso è necessario inoltre installare un web server.
Esistono diversi web server, ad esempio [Glassfish](https://javaee.github.io/glassfish), [Tomcat](http://tomcat.apache.org/), [JBoss](https://developers.redhat.com/products/eap/overview/?referrer=jbd), [Jetty](https://www.eclipse.org/jetty/) e altri.

In questo corso viene utilizzato Tomcat, quindi verrà mostrato il processo di installazione per tutti i sistemi operativi, ad eccezione delle distribuzioni Linux diverse da Ubuntu.
 
   * ##### Installare Tomcat su macOS
     Nel caso abbiate come sistema operativo macOS allora potete installare Tomcat tramite      [homebrew](https://brew.sh/index_it) semplicemente digitando
 
     ```shell
     brew install tomcat
     ```
     Per avviare tomcat basterà digitare `catalina run`.
     In alternativa è possibile usare  
     ```shell
     catalina start
     catalina stop
     ``` 
   	 per fare in modo che sia trattato come deamon e quindi non blocchi la finestra del terminale.
     
   * ##### Installare Tomcat su Windows
     Nel caso di windows basta eseguire l'installer scaricabile sul sito ufficiale di Tomcat.  
   * ##### Installare Tomcat su Ubuntu
     Nel caso di Ubuntu basta digitare
     ```shell
     apt update
     apt install tomcat8
     ```
     
     Nel caso voleste anche installare al webapp per amministare tomcat digitate  
     `apt install tomcat8-admin`
     
     Potrete gestire tomcat con i seguenti comandi
     ```shell
     systemctl start tomcat8
     systemctl restart tomcat8
     systemctl stop tomcat8
     ```
 
 Per tutti gli altri web server si rimanda alle istruzioni sui siti ufficiali.

### Installare un IDE
I tre maggiori IDE per Java sono:
  * [NetBeans](https://netbeans.org/) - Free
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/): Sono disponibili
    * La **Community Version** gratuita
    * La **Ultimate Version** a pagamento che è possibile scaricare gratuitamente con la licenza studenti ottenibile grazie alla mail di ateneo. 
  * [Eclipse](https://www.eclipse.org/eclipseide/) - Free
 
 Personalmente io utilizzo IntelliJ IDEA, ma a parte piccole differenze grafiche per quello che dobbiamo fare sono del tutto indifferenti a differenza dei plugin che sono disponibili; è possibile scegliere quello che si preferisce tra i tre.
 
