制作镜像，是一项创新的活动，如果官方或者在仓库中的镜像不满足自己的需求，那么最好的方式就是创建自己的镜像文件来满足自己的需求。有两种方式进行创建镜像文件。

- 从零开始，自己创建
- 限定一个基础镜像文件，再添加自己需要的环境

**1，FROM——指定基础镜像** 

```dockerfile
FROM imageName
...
...
```

**2，RUN——执行命令**

仅仅只有一个基础的镜像并不能体现出多样性，应该在此之上添加新的东西，比如下列文件

```dockerfile
FROM debian:jessie

RUN buildDeps='gcc libc6-dev make' \
    && apt-get update \
    && apt-get install -y $buildDeps \
    && wget -O redis.tar.gz "http://download.redis.io/releases/redis-3.2.5.tar.gz" \
    && mkdir -p /usr/src/redis \
    && tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1 \
    && make -C /usr/src/redis \
    && make -C /usr/src/redis install \
    && rm -rf /var/lib/apt/lists/* \
    && rm redis.tar.gz \
    && rm -r /usr/src/redis \
    && apt-get purge -y --auto-remove $buildDeps
```

FROM指定了基础镜像文件，RUN指令一次性的执行了所需要在这个镜像文件中应该执行的命令。

应该尽可能的减少无用文件的生成，并且尽量只用一次RUN指令。

**3，构建镜像**

```shell
$ docker build -f dockerfile -t nginx:v3 .
```

- -t 表示此镜像的标签
- -f 表示指定的dockerfile文件路径

使用此命令就可以实现镜像的构建，也会展现出对应的过程

<!--还可以使用Git repo的方式进行构建镜像文件-->

**4，复制本地文件**

docker进行build镜像的时候，并不是在本地执行构建命令，而是在服务器中进行构建，如果需要复制本地文件到镜像中，就需要给服务器一个上下文目录的概念。构建的时候，会把这个目录打包上传到Docker服务器，构建时展开就是构建的上下文。

```
COPY ./package.json /app/
```

需要在目录中忽略的文件可以写在目录下的`.dockerignore`文件中



