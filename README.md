# IMDb-Final-Project

## Prerequisites

* jdk 1.8
* Tomcat 8
* MySQL 
* Spring 4.1.4.

## Deployment

To run this project, please change the following code in applicationContext-database.xml

```
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/final</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```
as your database setting

Then run the following command in your mysql
```
create table final
```

## Built With

* [Spring MVC](https://spring.io/) and [Hibernate](http://hibernate.org/) - Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [sts](https://spring.io/tools/sts) - IDE
