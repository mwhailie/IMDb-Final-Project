# IMDb-Final-Project

## Prerequisites

* JDK 1.8
* Tomcat 8
* MySQL
* [Spring MVC](https://spring.io/) and [Hibernate](http://hibernate.org/) - Framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Front End

* [jquery](http://jquery.com/) Framework
* [bootstrap](http://v3.bootcss.com/) 

## Set up

To run this project, please change the following code in hibernate.cfg.xml

```
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/final</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```
as your database setting

Then run the following command in your mysql
```
create table final;
```

## Built With

* [sts](https://spring.io/tools/sts) - IDE
