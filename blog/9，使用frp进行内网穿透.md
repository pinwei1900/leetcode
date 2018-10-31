[frp](https://github.com/fatedier/frp)真的非常棒！

根据自己的系统下载对应的release版本

1，启用server（公网服务器）

```
#frps.ini
[common]
bind_port = 7000
vhost_http_port = 80
vhost_https_port = 443
```

服务器端口，以及使用http进行访问的端口

启动

`sudo ./frps -c frps.ini`

2，启用client

```
#frpc.ini
[common]
server_addr = 47.107.122.21
server_port = 7000

[ssh]
type = http
local_ip = 127.0.0.1
local_port = 3000
custom_domains = 47.107.122.21
vhost_http_port = 80
```

common  公网服务器信息

ssh 配置连接信息，本地服务ip和端口，以及远程对接ip和端口