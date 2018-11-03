1，下载dep[文件](https://dev.mysql.com/downloads/repo/apt/)

2，命令行添加mysql的仓库链接

`sudo dpkg -i mysql-apt-config_*w.x.y-z*_all.deb`

如果不知道具体的操作，那么就直接按照默认推荐的进行选择，注意8.0和5.7版本，5.7是比较稳定且大小比较合适的版本。

3，安装mysql

`sudo apt-get update`

`sudo apt-get install mysql-server`

安装过程中会要求提供mysql的用户名和密码，保持默认密码（空），或者123456

4，开启和关闭mysql服务

安装成功之后会默认开始mysql服务

```shell
sudo service mysql status #查询状态
sudo service mysql stop   #停止服务
sudo service mysql start  #开启服务
```

5，登陆设置

新安装的mysql服务，如果不进行任何设置，会发现mysql只能使用sudo权限登陆，这是因为mysql的root用户登陆插件是auth_socket导致的。

```mysql
$ sudo mysql -u root # I had to use "sudo" since is new installation

mysql> USE mysql;
mysql> SELECT User, Host, plugin FROM mysql.user;

+------------------+-----------------------+
| User             | plugin                |
+------------------+-----------------------+
| root             | auth_socket           |
| mysql.sys        | mysql_native_password |
| debian-sys-maint | mysql_native_password |
+------------------+-----------------------+
```

对此进行修改

```mysql
mysql> UPDATE user SET plugin='mysql_native_password' WHERE User='root';
mysql> FLUSH PRIVILEGES;
mysql> exit;

$ service mysql restart
```

6，修改mysql用户密码

**shell命令**

```shell
mysqladmin -u用户名 -p旧密码 password 新密码 
mysqladmin -uroot -p123456 password 123
```

**修改table **

```mysql
mysql> use mysql; 
mysql> update user set password=password('123') where user='root' and host='localhost'; 
mysql> flush privileges;
```

**mysql命令**

```mysql
mysql> set password for 用户名@localhost = password('新密码'); 
mysql> set password for root@localhost = password('123');
```

7，新建mysql用户

```mysql
$ sudo mysql -u root # I had to use "sudo" since is new installation

mysql> USE mysql;
mysql> CREATE USER 'songlin'@'localhost' IDENTIFIED BY '';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'songlin'@'localhost';
mysql> UPDATE user SET plugin='auth_socket' WHERE User='YOUR_SYSTEM_USER';
mysql> FLUSH PRIVILEGES;
mysql> exit;

$ service mysql restart
```

8，开放远程端口

查看MySQL默认配置文件

`$ mysql --help` 

找出其中的 `bind-address = 127.0.0.1`并使用`#`注释掉此句话，重启MySQL

赋予MySQL的用户权限

```
mysql> USE mysql;
mysql> update user set host = '%' where user = 'songlin';
mysql> grant all privileges  on *.* to 'songlin'@'%' identified by "123456";
mysql> FLUSH PRIVILEGES;
mysql> exit;

$ service mysql restart
```

9，MySQL远程链接

```
$ mysql -h 47.107.122.21 -usonglin -p
```

