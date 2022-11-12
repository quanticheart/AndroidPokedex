# Pokedex TCG

Aplicativo com intuito apenas educativo

### Versões

Contem dois Product Flavors:

- Free

Versão gratuita do aplicativo, que comtem um banner de propaganda na tela de perfil

- Pro

Versão sem propaganda no perfil

### Telas

O aplicativo comte:

- Tela de SplashScreen
- Tela de login
- Tela de cadastro
- Tela de recuperação de senha
- Listagem de card (1GEN)
- Tela de detalhes
- Lista de coleção do usuario
- Sobre
- Perfil

### Modulos

Para a contrução do aplicativo foi utilizado modularização com arquitetura clean:

- APP

Modulo principal do aplicativo, responsavel pelas views e viewmodels

- Domain

Modulo responsavel pela regras de negocio

- Repository

Modulo responsavel pelos dados externos e internos

- Core

Modulo responsavel por conter todo o codigo comum

### Recusos Extras

- Compartilhas dados do card
- notificação

### Firebase

O aplicativo utiliza os seguintes recursos do firebase:

- Analytics
- Crashlytics
- Firebase App Distribution
- Remote Config
- Notification
- Performs
- Authentication
- Firestore database

  