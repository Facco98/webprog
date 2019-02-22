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
