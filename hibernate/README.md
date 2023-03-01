# Persistência com JPA: Hibernate

O Hibernate é um framework de mapeamento objeto-relacional (ORM) de código aberto para a linguagem de programação Java. Ele fornece uma maneira conveniente de mapear objetos Java para tabelas em um banco de dados relacional e vice-versa, evitando a necessidade de escrever código SQL manualmente. O Hibernate é amplamente utilizado em projetos Java empresariais e é conhecido por sua eficiência, escalabilidade e facilidade de uso. Ele suporta a maioria dos bancos de dados relacionais, como MySQL, Oracle, PostgreSQL, SQL Server e muitos outros. 

## Instalação
No arquivo `pom.xml` na tag `<dependecies> </dependencies>`adicionaremos a dependência
do **Hibernate**, basta adicionar uma, que o Maven irá baixar as demais.

```xml
<dependencies>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>5.4.27.Final</version>
    </dependency>
</dependencies>
```

E agora precisamos instalar a dependência do banco de dados. Nesse caso eu estarei
usando o **MySQL**.

Seguindo a mesma ideia da instalação da dependência do Hibernate, faremos a do MySQL.

```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>5.4.17.Final</version>
    </dependency>


    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.32</version>
    </dependency>

</dependencies>
```

Com isso, basta fazer com que seu projete baixe as dependências que foram adicionadas no
arquivo `pom.xml`. Isso irá variar de IDE para IDE.

## Configuração

Uma das etapas importantes é a **configuração**! E essas configurações ficarão no 
arquivo `persistence.xml`. Como estamos tratando de um arquivo de configuração, no projeto java 
irei colocar na pasta resources. **Obs**: esse arquivo deve ficar dentro de uma pasta chamada: 
**META-INF**, basta cria-la em seu projeto.

Segue uma imagem de como ficou no meu projeto.


