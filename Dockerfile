#指定基础镜像，在其上进行定制
FROM java:8

#维护者信息
MAINTAINER wangshilei <sirwsl@163.com>

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp

#复制上下文目录下的target/demo-1.0.0.jar 到容器里
COPY target/shopping-kill-1.0-SNAPSHOT.jar shoppingkill.jar

#bash方式执行，使demo-1.0.0.jar可访问
#RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
RUN bash -c "touch /shoppingkill.jar"

#update system timezone
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#update application timezone
RUN echo "Asia/Shanghai" >> /etc/timezone


#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 8080

#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","shoppingkill.jar"]