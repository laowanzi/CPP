## Java内存区域详解

### 运行时数据区域

C/C++中需要程序员自己进行内存管理，容易出现内存泄露的问题，Java将内存管理的权利交给JVM，一旦出现内存泄漏和溢出的问题，如果不了解虚拟机是怎样使用内存的，那么排查错误将是一个艰巨的任务。

JVM在执行Java程序是会把它管理的内存划分成不同的数据区域

JDK1.7：

![java-runtime-data-areas-jdk1.7](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\java-runtime-data-areas-jdk1.7.png)

JDK1.8：

![java-runtime-data-areas-jdk1.8](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\java-runtime-data-areas-jdk1.8.png)

线程私有的：

* 程序计数器
* 虚拟机栈
* 本地方法栈

线程共享的：

* 堆
* 方法区
* 直接内存（非运行时数据区的一部分）

#### 程序计数器

* 字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理
* 在多线程的情况下，程序计数器用于记录当前线程执行的位置，从而当线程被切换回来的时候能够知道该线程上次运行到哪儿了
* 程序计数器是唯一不会出现`OutOfMemoryError`的内存区域

#### Java虚拟机栈

## JVM垃圾回收详解

Java的自动内存管理主要是针对对象内存的分配和对象内存的回收。同时，Java自动内存管理最核心的功能是**堆**内存中对象的分配与回收。

Java堆是垃圾收集器管理的主要区域，因此也被称作**GC堆（Garbage Collected Heap）**

在JDK7版本及以前，堆内存分为以下三个部分：

1.新生代内存（Young Generation）：Eden、Survivor区S0和S1

2.老生代（Old Generation）：中间一层

3.永久代（Permanent Generation）：最底下一层

![hotspot-heap-structure](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\hotspot-heap-structure.png)

JDk8版本之后`PermGen`被`Metaspace`（元空间）取代，元空间使用的是直接内存。

### 内存分配和回收原则

对象优先在**Eden**区分配

大对象直接进入老年代

长期存活的对象将进入老年代

针对HotSpot VM的实现，它里面的GC其实准确分类只有两大种：

部分收集(Partial GC)：

* 新生代收集
* 老年代收集
* 混合收集

整堆收集(Full GC)：收集整个Java堆和方法区

空间分配担保是为了确保在Minor GC之前老年代本身还有容纳新生代所有对象的剩余空间。

### 死亡对象判断方法

#### 引用计数法

给对象中添加一个引用计数器：

* 每当有一个地方引用它，计数器加1
* 当一个引用失效，计数器减1
* 当计数器为0，对象不会再被使用

没有被实际采用，很难解决**对象之间循环引用**的问题

#### 可达性分析算法

#### 引用类型总结

引用类型分为强引用、软引用、弱引用、虚引用四种

![java-reference-type](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\java-reference-type.png)

#### 如何判断一个常量时废弃常量？

#### 如何判断一个类时无用的类？

方法区主要回收的是无用的类

### 垃圾收集算法

#### 标记-清除算法

标记-清除算法分为标记和清除两个阶段：首先标记出所有不需要回收的对象，在标记完成后统一进行回收掉所有没有被标记的对象。

这是最基础的收集算法，有两个问题：

1.效率问题：标记和清除两个阶段效率都不高

2.空间问题：会产生很多内存碎片

![mark-and-sweep-garbage-collection-algorithm](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\mark-and-sweep-garbage-collection-algorithm.png)



## 类加载器详解

### 类加载过程

* 类加载过程：**加载->连接->初始化**
* 连接过程分为三类：**验证->准备->解析**

![class-loading-procedure](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\class-loading-procedure.png)







































































