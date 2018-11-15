关于数据库配置文件的修改

```
spring.datasource.test1.driver-class-name
spring.datasource.test1.jdbc-url
spring.datasource.test1.username
spring.datasource.test1.password
```



maven打包

```
根目录下没有source源文件的，应该使用pom类型进行打包
有源文件的，可以使用jar进行打包
```



启动

```
启动的时候不能注入bean，只可以定义和生成bean
```

