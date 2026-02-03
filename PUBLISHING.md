# Guia de Publicacao no Maven Central

## Pre-requisitos

- [x] Conta no Sonatype Central (https://central.sonatype.com)
- [x] Namespace verificado (com.echosistema)
- [x] Chave GPG criada e publicada

## Passo 1: Gerar Token no Sonatype Central

1. Acesse **https://central.sonatype.com**
2. Faca login com sua conta
3. Clique no seu **avatar/nome** no canto superior direito
4. Clique em **"View Account"**
5. Na secao **"Generate User Token"**, clique em **"Generate"**
6. Sera exibido:
   ```
   <server>
     <id>${server}</id>
     <username>TOKEN_USERNAME_AQUI</username>
     <password>TOKEN_PASSWORD_AQUI</password>
   </server>
   ```
7. **COPIE E SALVE** esses valores - eles so aparecem uma vez!

## Passo 2: Configurar Maven Settings

Crie ou edite o arquivo `~/.m2/settings.xml`:

```bash
mkdir -p ~/.m2
nano ~/.m2/settings.xml
```

Cole o seguinte conteudo (substitua pelos seus tokens):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              https://maven.apache.org/xsd/settings-1.0.0.xsd">

  <servers>
    <server>
      <id>ossrh</id>
      <username>COLE_SEU_TOKEN_USERNAME_AQUI</username>
      <password>COLE_SEU_TOKEN_PASSWORD_AQUI</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <id>ossrh</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <gpg.keyname>49CF2678F35D5E32</gpg.keyname>
      </properties>
    </profile>
  </profiles>

</settings>
```

Salve com `Ctrl+O`, `Enter`, `Ctrl+X`.

## Passo 3: Verificar Chave GPG

Confirme que sua chave esta configurada:

```bash
# Listar chaves
gpg --list-keys --keyid-format LONG

# Deve mostrar:
# pub   rsa3072/49CF2678F35D5E32 2025-12-25 [SC] [expira: 2028-12-24]
#       83FAAB631C8CA4ED477DC5C949CF2678F35D5E32
# uid                 [ultimate] EchoSistema <contact@echosistema.email>
```

## Passo 4: Fazer Deploy

```bash
cd /home/ewerton/Jetbrains/PhpStormProjects/echointel-sdk/java

# Limpar, compilar, testar e publicar
mvn clean deploy -P release
```

Se pedir a senha da chave GPG, digite a senha que voce criou ao gerar a chave.

## Passo 5: Verificar Publicacao

Apos o deploy bem-sucedido:

1. Acesse **https://central.sonatype.com**
2. Va em **"Deployments"** ou **"Browse"**
3. Procure por `com.echosistema:echointel-sdk`
4. O pacote pode levar **10-30 minutos** para aparecer no Maven Central

## Como Usuarios Instalam

Apos publicado, usuarios podem adicionar ao `pom.xml`:

```xml
<dependency>
    <groupId>com.echosistema</groupId>
    <artifactId>echointel-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

Ou com Gradle:

```groovy
implementation 'com.echosistema:echointel-sdk:1.0.0'
```

## Troubleshooting

### Erro: "401 Unauthorized"
- Verifique se o token esta correto em `~/.m2/settings.xml`
- Gere um novo token no Sonatype Central

### Erro: "GPG signing failed"
- Verifique se a chave GPG esta configurada: `gpg --list-keys`
- Verifique o keyname no settings.xml

### Erro: "Namespace not verified"
- Va em Sonatype Central > Namespaces
- Verifique se `com.echosistema` esta verificado

### Erro: "Missing javadoc/sources"
- O pom.xml ja esta configurado para gerar ambos
- Execute: `mvn clean package` e verifique se gera os JARs

## Publicar Nova Versao

1. Atualize a versao no `pom.xml`:
   ```xml
   <version>1.1.0</version>
   ```

2. Execute o deploy:
   ```bash
   mvn clean deploy -P release
   ```

3. Crie uma tag no Git:
   ```bash
   git tag v1.1.0
   git push --tags
   ```

## Links Uteis

- Sonatype Central: https://central.sonatype.com
- Maven Central Search: https://search.maven.org
- Documentacao: https://central.sonatype.org/publish/publish-guide/
