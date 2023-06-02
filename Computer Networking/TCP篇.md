### TCP基本认识

![1310bf5ed78e4c8186481c47719e0793](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\1310bf5ed78e4c8186481c47719e0793.webp)

![format,png-20230309230534096](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\format,png-20230309230534096.webp)

**序列号**：建立连接时由计算机生成的随机数作为其初始值，通过SYN包传给接收端主机，每发送一次，就**累加**一次该**数据字节数**的大小，用来解决**网络包乱序**问题。

**确认问答号**：指下一次期望收到的数据的序列号，可以认为在这个序号之前的数据都已经被正常接收，用来解决**丢包**的问题。

**控制位**：

* ACK：该位为`1`时，**确认应答**的字段变为有效，TCP规定除了最初建立连接时的`SYN`包之外该位必须设置为`1`
* RST：该位为`1`时，表示TCP连接中出现异常必须强制断开连接
* SYN：该位为`1`时，表示希望建立连接，并在其**序列号**的字段进行序列号初始值的设定
* FIN：该位为`1`时，表示希望断开连接，通信双方互相交换`FIN`为1 的TCP段



#### 什么是TCP

TCP是**面向连接的**、**可靠的**、**基于字节流**的传输层通信协议。

* **面向连接**：一对一
* **可靠的**：TCP保证一个报文能够到达接收端
* **字节流**：需要知道消息的边界

建立一个TCP连接需要双方达成三个信息的共识

* **Socket**：由IP地址（IP头部）和端口号（TCP头部）组成
* **序列号**：用来解决乱序问题等
* **窗口大小**：用来做流量控制



#### UDP和TCP的区别

UDP不提供复杂的控制机制，利用IP提供面向无连接的通信服务，UDP头部格式如下：

![format,png-20230309230439961](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\format,png-20230309230439961.webp)

区别：

1.连接

TCP是面向连接的，传输之前要先建立连接；UDP不需要连接

2.服务对象

TCP是一对一两点式服务的；UDP支持一对一，一对多和多对多的交互通信

3.可靠性

TCP是可靠的，数据可以无差错、不丢失、不重复、按序到达；

UDP是尽最大努力交付

4.拥塞控制，流量控制

TCP有以上机制，UDP则没有

5.首部开销

TCP首部一般是20个字节，使用选项字段会变长；UDP首部只有8个字节，且是固定的

6.传输方式

TCP是流式传输，没有边界，但保证顺序和可靠

UDP是一个包一个包的发送，是有边界的

7.分片不同

TCP的数据大小如果大于MSS大小，则在传输层进行分片，目标主机收到后同样在传输层进行组装

UDP的数据大小如果大于MTU大小，则会在IP层进行分片，目标主机收到后同样在IP层进行组装

8.应用场景不同：

TCP：FTP文件传输，HTTP/HTTPS

UDP：DNS、SNMP、视频音频等多媒体通信

### TCP连接建立

#### TCP三次握手



#### ![TCP三次握手.drawio](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\TCP三次握手.drawio.webp)

* 一开始，客户端和服务端都处于`CLOSE`状态。先是服务端主动监听某个接口，处于`LISTEN`状态

![format,png-20230309230500953](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\format,png-20230309230500953.webp)

* 客户端随机初始化序列号（`client_isn`），将`SYN`标志位设为1，发送`SYN`包，表示向服务端发起连接，该报文不包含应用层数据，之后客户端处于`SYN-SENT`状态。



![format,png-20230309230504118](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\format,png-20230309230504118.webp)

![format,png-20230309230508297](C:\Users\Zhang Wan\Desktop\学习笔记\Computer Networking\Pictures\format,png-20230309230508297.webp)