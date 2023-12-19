# CyberGari

Um software que analisa os seus arquivos e te ajuda a economizar espaço e dinheiro com armazenamento!

## Instalação

Para rodar o CyberGari, é necessário ter instalado na sua máquina
a [Java SDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) , a instalação
do [Gradle](https://gradle.org/install/) também é recomendada.

Antes de compilar, atualize a Classe LocalFileSettins.java com o separador correto do seu sistema operacional e uma
diretório existente.

Exemplo Linux:

```bash
    private static final SO soInUse = SO.LINUX;
    public static final String SEPARATOR = "/";
    public static final String FOLDER = ".github/testFiles";
```

Exemplo Windows:

```bash
    private static final SO soInUse = SO.WINDOWS;
    public static final String SEPARATOR = "\\";
    public static final String FOLDER = "C:\\.github\\testFiles";
```

Depois disso, apenas mude para a pasta do projeto e execute os seguintes comandos:

```bash
  cd CyberGari-backend
  gradle build
  gradle bootRun
```
    
