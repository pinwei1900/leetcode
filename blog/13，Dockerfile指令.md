**COPY复制文件**

```Dockerfile
COPY package.json /usr/src/app/
```



**CMD容器启动**

- `shell` 格式：`CMD <命令>`
- `exec` 格式：`CMD ["可执行文件", "参数1", "参数2"...]`

shell格式直接使用shell命令，如果需要执行程序的话，应该直接使用exec的方式进行执行



**ENTRYPOINT传递参数**

```Dockerfile
FROM ubuntu:16.04
RUN apt-get update \
    && apt-get install -y curl \
    && rm -rf /var/lib/apt/lists/*
ENTRYPOINT [ "curl", "-s", "http://ip.cn" ]
```

```
docker run myip -i #相当于执行了 curl -s http://ip.cn -i
```



**ENV设置环境变量**

- `ENV <key> <value>`
- `ENV <key1>=<value1> <key2>=<value2>..`

在Dockerfile中使用



**VOLUME定义匿名卷**

```
docker run -d -v mydata:/data xxxx
```

EXPOSE暴露接口

```
EXPOSE 3306 33060
```



**WORKDIR指定工作目录**



**USER指定当前用户**

```
USER redis
```



**HEALTHCHECK健康检查**



**ONBUILD构建时运行**

在引入多层Dockerfile文件的时候，如果有一个基础的镜像文件，但是其中有一些指令的信息虽然是公共的，但是直接构建的话，就需要给每一个项目复制一份Dockerfile文件。不要这么做，使用ONBUILD可以使后面跟随的命令在下一层进行构建的时候运行。

```dockerfile
FROM node:slim
RUN mkdir /app
WORKDIR /app
ONBUILD COPY ./package.json /app
ONBUILD RUN [ "npm", "install" ]
ONBUILD COPY . /app/
CMD [ "npm", "start" ]
```

