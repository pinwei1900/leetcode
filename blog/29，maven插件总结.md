maven常用命令

```shell
mvn package -Dmaven.test.skip=true
mvn dependency:purge-local-repository 
```





- spring-boot-maven-plugin

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <fork>true</fork>
    </configuration>
</plugin>
```

