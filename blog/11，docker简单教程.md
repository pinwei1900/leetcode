想象一下，docker作为一个容器，最重要的是什么。

第一是镜像，镜像文件定义了容器里面包含的基础环境，是资源的定义

第二是容器，用镜像可以用来生成容器，每一个容器都是一个实例，它可以使用生成它的镜像的资源

第三是数据卷，运行的程序不应该向容器中写入数据，应该独立于镜像的存在，有文件的写入操作，都应该存储在数据卷中

第四是仓库，是用来放镜像文件的地方，可以用来分享以及下载别人写好的镜像文件，比如[Docker Hub](https://hub.docker.com/)

有了这些，就可以建立一个环境，用来部署应用程序



**1，获取镜像**

` docker pull ubuntu:16.04`

列出镜像

`docker image ls`

`docker system df`

`docker image rm centos`

**2，修改和保存镜像**

`docker diff webserver`

`docker commit --author "Tao Wang <twang2218@gmail.com>" --message "修改了默认网页" webserver nginx:v2`

`docker history nginx:v2` 查看历史提交信息

**3，运行镜像**

`$ docker run -it --rm ubuntu:16.04 bash`

- `-it`：这是两个参数，一个是 `-i`：交互式操作，一个是 `-t` 终端。这里打算进入 `bash` 执行一些命令并查看返回结果，因此需要交互式终端。
- `--rm`：这个参数是说容器退出后随之将其删除。默认情况下，为了排障需求，退出的容器并不会立即删除，除非手动 `docker rm`。这里只是随便执行个命令，看看结果，不需要排障和保留结果，因此使用 `--rm` 可以避免浪费空间。
- `ubuntu:16.04`：这是指用 `ubuntu:16.04` 镜像为基础来启动容器。
- `bash`：放在镜像名后的是**命令**，这里希望有个交互式 Shell，因此用的是 `bash`

**4，进入镜像**

`docker exec -it webserver bash`

**5，制作镜像**

```
$ docker import http://download.openvz.org/template/precreated/ubuntu-14.04-x86_64-minimal.tar.gz 
```

压缩包可以是本地文件、远程 Web 文件，甚至是从标准输入中得到。压缩包将会在镜像 `/` 目录展开，并直接作为镜像第一层提交。

**6，保存镜像**

```
$ docker image ls alpine
$ docker save alpine | gzip > alpine-latest.tar.gz
```

**7，加载保存的镜像**

```
$ docker load -i alpine-latest.tar.gz
```

从一个主机复制文件到另一个主机

```
docker save <镜像名> | bzip2 | pv | ssh <用户名>@<主机名> 'cat | docker load'
```

