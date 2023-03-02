# Persistência com JPA: Hibernate
JPA significa Java Persistence API e é uma especificação do Java que define uma interface de programação para gerenciamento de persistência de dados em aplicações Java.
O JPA é implementado por diversos frameworks de persistência, como o Hibernate, EclipseLink e OpenJPA.

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

Segue uma imagem de como ficou no meu projeto na IDE IntelliJ IDEA

<img src="https://i.ibb.co/sqvtYd6/Screenshot-from-2023-03-01-15-27-05.png">

E dentro desse arquivo é necessário colocar as configurações abaixo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <persistence-unit name="hibernate" transaction-type="RESOURCE_LOCAL">
        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/loja" />
            <property name="javax.persistence.jdbc.user" value="USUARIO" />
            <property name="javax.persistence.jdbc.password" value="SENHA" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
        </properties>
    </persistence-unit>
</persistence>


```

Não é necessário decorarmos todo esse xml, mas precisamos entender seu funcionamento e significado.

`transaction-type`: Há dois valores, RESOURCE_LOCAL(base de dados local) ou JTA

`<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />` serve para definirmos qual será o drive, mysql, postgresql ...

` <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/SCHEMA" />` serve para definirmos a url de conexão com o banco de dados

` <property name="javax.persistence.jdbc.user" value="USUARIO" />` serve para definirmos o usuário

`<property name="javax.persistence.jdbc.password" value="SENHA" />` serve para definirmos a senha

`<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />` serve para definirmos qual dialeto do banco de dados

**Obs**: Substitua as palabras: SCHEMA, USUARIO, SENHA pelas suas informações!

## Schema e Tabel

Para esse estudo, faremos de um schema chamado `store` e tabela intitulada `products`

```sql
CREATE SCHEMA store;

CREATE TABLE products( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL, 
    descricao varchar(255), 
    preco DECIMAL 
);
```


## Classe para representar nossa tabela: entidade JPA

```Java
package br.com.alura.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    @Column(name = "price")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}


```

Iremos fazer uso de **anotações**.

`@Entity`: estamos dizendo que nossa classe representará uma entidade(tabela)

`@Table(name = "products")`: como o nome da nossa classe é diferente do nome da tabela, devemos usar essa anotação
e passar o nome da tabela, que no meu caso a tabela se chama **products**

`@Column(name = "column_name")`: essa anotação serve para indetificarmos qual atributo da nossa classe
é na nossa tabela. Não fiz uso dessa anotação, pois, os nomes dos meus atributos eram iguais as das colunas da tabela.

`@Id`: representa qual atributo da nossa classe que é o id da nossa tabela.

`@GeneratedValue(strategy = GenerationType.IDENTITY)`: representa que o valor desse atributo `id` é gerado pelo banco de dados
para o MySQL o valor é ` GenerationType.IDENTITY`


**Em resumo**: uma entidade JPA é uma classe que faz o mapeamento de uma tabela na nossa base de dados


## Realizando operações no banco: INSERT, UPDATE and DELETE

```java
package br.com.alura.main;

import br.com.alura.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class MainInsertProduct {
    public static void main(String[] args) {
        Product cellphone = new Product();
        cellphone.setName("Xiaomi Poco x5 pro");
        cellphone.setDescription("Ram 8GB, Memory 256GB");
        cellphone.setPrice(new BigDecimal("2199"));

        //Factory passando o persistenciUnitName no arquivo persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("store");
        EntityManager entityManager = factory.createEntityManager();
        //iniciar as transações
        entityManager.getTransaction().begin();
        entityManager.persist(cellphone);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

```

O código acima representa uma operação de inserção.
Após criar o objeto `cellphone` e 'setar' os atributos, foi necessário
criar uma `EntityManagerFactory` uma interface, e para instância-lo usaremos
uma factory `Persistence.createEntityManagerFactory("store")`. O argumento `"store"` é a string que difinimos
no arquivo persistence.xml que definimos na tag `<persistence-unit name="store" transaction-type="RESOURCE_LOCAL">` como
o valor do atributo **name**.

Com isso poderemos criar uma EntityManager que é a interface que poderemos fazer uso para
poder realizar as transações no banco de dados. Logo, o resultado é: `EntityManager entityManager = factory.createEntityManager();`, e com isso poderemos realizar
as operações desejadas.
```java
    entityManager.getTransaction().begin();
    entityManager.persist(cellphone);
    entityManager.getTransaction().commit();
    entityManager.close();
```
`entityManager.getTransaction().begin();` inicia as transações para poder reaizar as transações no banco de dados

`entityManager.persist(cellphone);` objeto que será inserido no banco de dados

`entityManager.getTransaction().commit();` realizar as operações 

`entityManager.close();` finalizar o recurso entitymanager

Ao rodar esse código, no log(saída no terminal) não vai aparecer nenhuma confirmação, logo é
necessário configurar no persistence.xml.
Segue a configuração na tag `<properties>` adicione a propriedade `<property name="hibernate.show_sql" value="true"/>

Com isso ao executar o código o resultado será:

<img src="https://i.ibb.co/m5MwZgC/Screenshot-from-2023-03-02-10-04-22.png" alt="Screenshot-from-2023-03-02-10-04-22" border="0">

## Organizando um pouco mais o código

Vamos isolar a factory para uma nova classe chamada `JPAUtil` e em um pacote chamado 
`util`

```java
package br.com.alura.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("store");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}

```
O intuito é 'esconder' essa factory.

Portanto, no nosso main de inserção o resultado será:

```java
package br.com.alura.main;

import br.com.alura.dao.ProductDao;
import br.com.alura.model.Product;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MainInsertProduct {
    public static void main(String[] args) {
        Product cellphone = new Product();
        cellphone.setName("Xiaomi Poco x5 pro");
        cellphone.setDescription("Ram 8GB, Memory 256GB");
        cellphone.setPrice(new BigDecimal("2199"));


        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(entityManager);
        //iniciar as transações
        entityManager.getTransaction().begin();
        productDao.insert(cellphone);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

```
 No código acima as transações ainda estão no Main, mas já organizamos e diminuímos linhas.

