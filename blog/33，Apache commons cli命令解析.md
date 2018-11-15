创造命令

```java
// create Options object
Options options = new Options();
// add t option
options.addOption("t", false, "display current time");
```

解析参数

```java
CommandLineParser parser = new DefaultParser();
CommandLine cmd = parser.parse( options, args);
if(cmd.hasOption("t")) {
    // print the date and time
} else {
    // print the date
}
```

获取值

```java
String countryCode = cmd.getOptionValue("c");
```

