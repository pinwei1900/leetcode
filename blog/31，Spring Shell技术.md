有时候需要使用一些命令行工具来完成一些任务，一个方便的技术是使用Spring Shell做开发

知识点

- 自定义提示符，PromptProvider类提供了命令行的提示信息
- 参数传递使用类似"echo1 --a 1 --b 2 --c 3"，或"echo1 --a 1 2 3"或"echo1 1 3 --b 2"。其效果都是分别把 1，2 和 3 赋值给 a、b 和 c、
- Spring Shell运行时，内部存在一个处理循环应用读取用户的输入，进行对应的处理，最后再把处理的结果输出，结果处理是由 shell.ResultHandler 接口来实现的，所以如果需要处理结果，应该实现ResultHandler接口
- 动态命令可用性，命令创建完成之后就是可用的，但是有时候命令的可以用基于是否满足内部状态，如果不满足则不可以使用，这种动态命令的实现通过@ShellComponent下面的xxxAvailability() 方法实现。按照命名惯例，检查命令可用性的方法的名称是在命令方法名称之后加上 Availability 后缀
- 通过实现Converter 接口可以实现String到实体对象的转换
- 如果不需要内置命令，可以把 spring-shell-standard-commands 从 Maven 依赖中排除

```
<dependency>
  <groupId>org.springframework.shell</groupId>
  <artifactId>spring-shell-starter</artifactId>
  <version>2.0.0.M2</version>
  <exclusions>
    <exclusion>
      <groupId>org.springframework.shell</groupId>
      <artifactId>spring-shell-standard-commands</artifactId>
    </exclusion>
  </exclusion>
</dependency>
```

相关注解

- @ShellComponent 声明一个Spring Shell组件
- @ShellMethod("xxx") 表示一个可以具体执行的命令
- @ShellOption("--boy") 用来指定具体的参数名称绑定到具体的参数中；还可以定义默认值@ShellOption(defaultValue = "Hello")；还可以是多个值@ShellOption(arity = 3) 可以映射3个参数
- @Size 来限制字符串的长度，用@Min 和@Max 来限制数值的大小
- @ShellMethodAvailability 控制命令的可用性
- @ShellCommandGroup 命令分组形式，或者使用@ShellMethod 注解的属性 group 来指定分组名称



