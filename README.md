# repair-shop

Система Ремонтное Агенство. Пользователь может создать заявку на
ремонт изделия. Менеджер может принять заявку указав цену, либо
отклонить заявку, указав причину. Мастер может выполнить принятую
Менеджером заявку. Пользователь может оставить Отзыв о выполненных
работах.



#### To launch:
***
##### in Tomcat:

go to: *TOMCAT_ROOT/conf/server.xml*  
and set *URIEncoding=”UTF-8″* for *Connector* tagS. 

like:

```xml
<Connector   
URIEncoding="UTF-8"   
port="8080" 
redirectPort="8443"   
connectionTimeout="20000"   
protocol="HTTP/1.1"/>
```
***
##### in MySQL:

Create user with name: 'root' and pass: 'root'  
```sql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root';
```  
**Or**

 Change username and pass in `dbcp.properties` to yours  
 *(Your user should have all privileges. In this case program will work fine)*.  

Run *script.sql* which is located in *resources* of this project. 
***
After launch of tomcat you can access home page by path 

*http://localhost:8080/site/home_page*

*(if tomcat is launched on port 8080 of course (if it's not, change port in url to what you need))*
