Java JDK可以在Linux下有两个版本OpenJdk和Oracle JDK

1，下载[JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)，加压到`opt/java`路径下

`tar -zxvf jdk-8u191-linux-x64.tar.gz -C /opt/java`

2，配置环境变量使得所有用户都可以使用JDK

`vim /etc/profile`

```
export JAVA_HOME=/opt/java/jdk1.8.0_191
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:JAVA_HOME/lib:JRE_HOME/lib:${CLASSPATH}
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
```

