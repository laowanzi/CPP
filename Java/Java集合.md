## Java集合概述

### 概览

Java集合，也叫做容器，主要由两大接口派生而来：一个是`Collection`接口，用于存放单一元素；一个是`Map`接口，用于存放键值对。`Collection`接口下面又有三个主要的子接口：`List`、`Set`和`Queue`。

![Java集合框架概览](C:\Users\Zhang Wan\Desktop\学习笔记\Java\Pictures\java-collection-hierarchy.png)

### List，Set，Queue，Map四者的区别

* List：存储的元素是有序的，可重复的；
* Set（注重独一无二的性质）：存储的元素是无序的，不可重复的；
* Queue：按照特定的排队规则来确定先后顺序，存储的元素是有序的，可重复的；
* Map：使用键值对（Key-Value）存储，key是无序的、不可重复的，value是无序的、可重复的，每个键最多映射到一个值。

### 集合框架底层数据结构总结

#### List

* `ArrayList`：`Object[]` 数组
* `Vector`：`Object[]` 数组
* `LinkedList`：双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环)

#### Set

* `HashSet`(无序，唯一): 基于 `HashMap` 实现的，底层采用 `HashMap` 来保存元素
* `LinkedHashSet`: `LinkedHashSet` 是 `HashSet` 的子类，并且其内部是通过 `LinkedHashMap` 来实现的。有点类似于我们之前说的 `LinkedHashMap` 其内部是基于 `HashMap` 实现一样，不过还是有一点点区别的
* `TreeSet`(有序，唯一): 红黑树(自平衡的排序二叉树

#### Queue

- `PriorityQueue`: `Object[]` 数组来实现二叉堆
- `ArrayQueue`: `Object[]` 数组 + 双指针

再来看看 `Map` 接口下面的集合。

#### Map

- `HashMap`：JDK1.8 之前 `HashMap` 由数组+链表组成的，数组是 `HashMap` 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间
- `LinkedHashMap`：`LinkedHashMap` 继承自 `HashMap`，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，`LinkedHashMap` 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
- `Hashtable`：数组+链表组成的，数组是 `Hashtable` 的主体，链表则是主要为了解决哈希冲突而存在的
- `TreeMap`：红黑树（自平衡的排序二叉树）

### 如何选用集合?

我们主要根据集合的特点来选择合适的集合。比如：

- 我们需要根据键值获取到元素值时就选用 `Map` 接口下的集合，需要排序时选择 `TreeMap`,不需要排序时就选择 `HashMap`,需要保证线程安全就选用 `ConcurrentHashMap`。
- 我们只需要存放元素值时，就选择实现`Collection` 接口的集合，需要保证元素唯一时选择实现 `Set` 接口的集合比如 `TreeSet` 或 `HashSet`，不需要就选择实现 `List` 接口的比如 `ArrayList` 或 `LinkedList`，然后再根据实现这些接口的集合的特点来选用。

### List

#### ArrayList 和 Array（数组）的区别？

ArrayList内部基于动态数组实现，比Array（静态数组）使用起来更加灵活：

* ArrayList可以根据实际存储的元素动态地扩容和缩容，Array一经创建，大小无法改变；
* 允许使用泛型
* ArrayList只能存储对象，对于基本数据类型，需要转换成其包装类，Array全都可以。
* ArrayList支持插入、删除、遍历等常见操作，并提供了丰富地API操作方法；
* Array初始化时需要指定大小，ArrayList不用

#### ArrayList 和 Vector 的区别？

* ArrayList是List的主要实现类，Vector是List的古老实现类；
* ArrayList线程不安全，Vector使用 `synchronized` 关键字，线程安全；

随着 Java 并发编程的发展，`Vector` 和 `Stack` 已经被淘汰，推荐使用并发集合类（例如 `ConcurrentHashMap`、`CopyOnWriteArrayList` 等）或者手动实现线程安全的方法来提供安全的多线程操作支持。

#### ArrayList 可以添加 null 值吗？

可以添加，但最好不要，会导致空指针异常。

#### ArrayList 插入和删除元素的时间复杂度？

* 头部插入/删除：O(n)，之后的元素都需要移动；
* 尾部插入/删除：O(1)，无需改变其他元素的位置，尾部插入涉及到扩容操作时，需要进行O(n)的复制操作；
* 指定位置插入/删除：平均时间复杂度O(n)

#### LinkedList 插入和删除元素的时间复杂度？

* 头部插入/删除：O(1)，修改头节点指针即可；
* 尾部插入/删除：O(1)，修改尾节点指针即可；
* 指定位置插入/删除：需要先移动到指定位置，平均时间复杂度O(n)

#### LinkedList 为什么不能实现 `RandomAccess` 接口？

意味着LinkedList不支持快速访问，因为其底层数据结构是链表。

#### ArrayList 与 LinkedList 区别?

* 是否保证线程安全：都是线程不安全的；
* 底层数据结构不同：ArrayList是`Object`数组，LinkedList是双向链表；
* 插入和删除的不同
* 是否支持快速随机访问：ArrayList支持，LinkedList不支持；
* 内存空间占用：ArrayList列表尾部会预留一定的容量空间

#### ArrayList 的扩容机制



### Map

#### HashMap 和 Hashtable 的区别

- **线程是否安全：** `HashMap` 是非线程安全的，`Hashtable` 是线程安全的,因为 `Hashtable` 内部的方法基本都经过`synchronized` 修饰。（如果你要保证线程安全的话就使用 `ConcurrentHashMap` 吧！）；
- **效率：** 因为线程安全的问题，`HashMap` 要比 `Hashtable` 效率高一点。另外，`Hashtable` 基本被淘汰，不要在代码中使用它；
- **对 Null key 和 Null value 的支持：** `HashMap` 可以存储 null 的 key 和 value，但 null 作为键只能有一个，null 作为值可以有多个；Hashtable 不允许有 null 键和 null 值，否则会抛出 `NullPointerException`。
- **初始容量大小和每次扩充容量大小的不同：** ① 创建时如果不指定容量初始值，`Hashtable` 默认的初始大小为 11，之后每次扩充，容量变为原来的 2n+1。`HashMap` 默认的初始化大小为 16。之后每次扩充，容量变为原来的 2 倍。② 创建时如果给定了容量初始值，那么 `Hashtable` 会直接使用你给定的大小，而 `HashMap` 会将其扩充为 2 的幂次方大小（`HashMap` 中的`tableSizeFor()`方法保证，下面给出了源代码）。也就是说 `HashMap` 总是使用 2 的幂作为哈希表的大小,后面会介绍到为什么是 2 的幂次方。
- **底层数据结构：** JDK1.8 以后的 `HashMap` 在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）时，将链表转化为红黑树（**将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树**），以减少搜索时间（后文中我会结合源码对这一过程进行分析）。`Hashtable` 没有这样的机制。

#### HashMap 的底层实现

#### JDK1.8 之前

JDK1.8 之前`HashMap`底层是**数组和链表**结合在一起使用也就是**链表散列**。HashMap 通过 key 的 `hashcode` 经过扰动函数处理过后得到 hash 值，然后通过 `(n - 1) & hash` 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。

使用 `(n - 1) & hash` 实际上就是为了`hash%n`，当n是2的幂次时， `(n - 1) & hash == hash % n` ，`&`运算能够提高效率。

#### ConcurrentHashMap 和 Hashtable 的区别

`ConcurrentHashMap` 和 `Hashtable` 的区别主要体现在实现线程安全的方式上不同。

- **底层数据结构：** JDK1.7 的 `ConcurrentHashMap` 底层采用 **分段的数组+链表** 实现，JDK1.8 采用的数据结构跟 `HashMap1.8` 的结构一样，数组+链表/红黑二叉树。`Hashtable` 和 JDK1.8 之前的 `HashMap` 的底层数据结构类似都是采用 **数组+链表** 的形式，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的；
- 实现线程安全的方式（重要）：
  - 在 JDK1.7 的时候，`ConcurrentHashMap` 对整个桶数组进行了分割分段(`Segment`，分段锁)，每一把锁只锁容器其中一部分数据（下面有示意图），多线程访问容器里不同数据段的数据，就不会存在锁竞争，提高并发访问率。
  - 到了 JDK1.8 的时候，`ConcurrentHashMap` 已经摒弃了 `Segment` 的概念，而是直接用 `Node` 数组+链表+红黑树的数据结构来实现，并发控制使用 `synchronized` 和 CAS 来操作。（JDK1.6 以后 `synchronized` 锁做了很多优化） 整个看起来就像是优化过且线程安全的 `HashMap`，虽然在 JDK1.8 中还能看到 `Segment` 的数据结构，但是已经简化了属性，只是为了兼容旧版本；
  - **`Hashtable`(同一把锁)** :使用 `synchronized` 来保证线程安全，效率非常低下。当一个线程访问同步方法时，其他线程也访问同步方法，可能会进入阻塞或轮询状态，如使用 put 添加元素，另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低









