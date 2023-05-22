#### Java语言的特点

1. 简单易学
2. 面向对象（封装，继承，多态）
3. 编译与解释并存
4. 平台无关性（通过JVM实现）
5. 支持多线程

#### Java与C++的区别

1. Java是纯面向对象的语言，C++除了面向对象，还有面向过程，泛型编程
2. Java中没有指针，减轻程序员的负担
3. Java中内存回收由JVM负责，有GC机制
4. C++中可以对方法和操作符进行重载，Java只能对方法进行重载

#### 基础语法

基本类型（primitive type）

整型：`byte`、`short`、`int`、`long`

浮点型：`float`、`double`

| 基本类型  | 字节 | 类别     | 默认值    | 包装类型    |
| --------- | ---- | -------- | --------- | ----------- |
| `byte`    | 1    | 整型     | 0         | `Byte`      |
| `short`   | 2    | 整型     | 0         | `Short`     |
| `int`     | 4    | 整型     | 0         | `Integer`   |
| `long`    | 8    | 整型     | 0L        | `Long`      |
| `float`   | 4    | 浮点型   | 0f        | `Float`     |
| `double`  | 8    | 浮点型   | 0d        | `Double`    |
| `char`    | 2    | 字符类型 | 'u0000'   | `Character` |
| `boolean` |      | 布尔型   | **false** | `Boolean`   |

> 整型和浮点型都是有符号的。



#### 面向对象

对象变量并没有实际包含一个对象，它只是引用一个对象，Java中的null表示一个空引用。

Java中任何对象变量的值都是对存储在另外一个地方的某个对象的引用。

Java中可以通过new运算符创建对象实例（对象实例在堆内存中），返回一个对象引用（对象引用存放在栈内存中）。

Java中只有值传递，只不过对于对象参数，值的内容就是对象的引用。

### Object

#### 常见方法

Object类是一个特殊的类，是所有类的父类。它主要提供了以下11个方法。

```java
/**
 * native 方法，用于返回当前运行时对象的 Class 对象，使用了 final 关键字修饰，故不允许子类重写。
 */
public final native Class<?> getClass()
/**
 * native 方法，用于返回对象的哈希码，主要使用在哈希表中，比如 JDK 中的HashMap。
 */
public native int hashCode()
/**
 * 用于比较 2 个对象的内存地址是否相等，String 类对该方法进行了重写以用于比较字符串的值是否相等。
 */
public boolean equals(Object obj)
/**
 * naitive 方法，用于创建并返回当前对象的一份拷贝。
 */
protected native Object clone() throws CloneNotSupportedException
/**
 * 返回类的名字实例的哈希码的 16 进制的字符串。建议 Object 所有的子类都重写这个方法。
 */
public String toString()
/**
 * native 方法，并且不能重写。唤醒一个在此对象监视器上等待的线程(监视器相当于就是锁的概念)。如果有多个线程在等待只会任意唤醒一个。
 */
public final native void notify()
/**
 * native 方法，并且不能重写。跟 notify 一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程。
 */
public final native void notifyAll()
/**
 * native方法，并且不能重写。暂停线程的执行。注意：sleep 方法没有释放锁，而 wait 方法释放了锁 ，timeout 是等待时间。
 */
public final native void wait(long timeout) throws InterruptedException
/**
 * 多了 nanos 参数，这个参数表示额外时间（以毫微秒为单位，范围是 0-999999）。 所以超时的时间还需要加上 nanos 毫秒。。
 */
public final void wait(long timeout, int nanos) throws InterruptedException
/**
 * 跟之前的2个wait方法一样，只不过该方法一直等待，没有超时时间这个概念
 */
public final void wait() throws InterruptedException
/**
 * 实例被垃圾回收器回收的时候触发的操作
 */
protected void finalize() throws Throwable { }

```

#### `==`和`equals()`的区别

`==`对于基本类型和普通类型的作用效果是不同的：

* 对于基本类型，比较的是值
* 对于引用类型，比较的是对象的内存地址

`equals`不能用于基本类型的变量，只能用来判断两个对象是否相等。

`equals`方法存在两种使用情况：

* **类没有重写`equals`方法**：等价于通过`==`来比较两个对象
* **类重写`equals`方法**：若属性相等，则返回`true`，一般可以重写`equals`方法

> 字符串使用`equals`判断是否相等是因为虚拟机并不始终将相同的字符串共享，如通过`+`或`substring`等操作得到的字符串并不共享。

#### 接口与抽象类

接口用来描述类应该做什么，接口中的所有方法都自动是`public`方法。

抽象方法不需要实现，使用`abstract`关键字

不包含抽象方法的类可以定义为抽象类，包含抽象方法的类必须定义为抽象类。

抽象类不可实例化，但是可以定义一个抽象类的对象变量。

接口近似于没有实例字段的抽象类，但还是有区别的。

接口间接实现了多继承
