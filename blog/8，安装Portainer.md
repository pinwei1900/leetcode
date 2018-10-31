Portainer是Docker的图形化管理工具

1，下载镜像

`docker search portainer`

`docker pull docker.io/portainer/portainer`

2，创建Portainer实例

```
docker volume create portainer_data
docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
```

创建一个数据卷，用来保存实例的数据，和容器的生命周期无关。

使用宿主主机的端口关联容器的9000端口，并且将此应用的数据绑定到先前创建的数据卷上