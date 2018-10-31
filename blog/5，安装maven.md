1，下载[maven](https://maven.apache.org/download.cgi)，加压

`tar zxvf apache-maven-3.5.4-bin.tar.gz -C /opt/maven/`

2，配置环境

`vim /etc/profile`

```
export M2_HOME=/opt/maven/apache-maven-3.5.4
export PATH=$M2_HOME/bin:$PATH
```

