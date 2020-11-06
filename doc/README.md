# Java锁的种类
   1.**自旋锁**
     -自旋锁是采用让当前线程不停地的在循环体内执行实现的，当循环的条件被其他线程改变时 才能进入临界区。如下

   public class SpinLock {
      private AtomicReference<Thread> sign =new AtomicReference<>();
      public void lock(){
         Thread current = Thread.currentThread();
         while(!sign .compareAndSet(null, current)){
         }
       }
      public void unlock (){
         Thread current = Thread.currentThread();
         sign.compareAndSet(current, null);
       }
     }
   **使用了CAS原子操作，lock函数将owner设置为当前线程，并且预测原来的值为空。unlock函数将owner设置为null，并且预测值为当前线程。**
     
   **当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，直至第一个线程调用unlock函数将owner设置为null，第二个线程才能进入临界区。**
     
   **由于自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。但当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU时间。如果线程竞争不激烈，并且保持锁的时间段。适合使用自旋锁。**
     
   **注：该例子为非公平锁，获得锁的先后顺序，不会按照进入lock的先后顺序进行。**
     2、自旋锁的其他种类
     上篇我们讲到了自旋锁，在自旋锁中 另有三种常见的锁形式:TicketLock ，CLHlock 和MCSlock
     Ticket锁主要解决的是访问顺序的问题，主要的问题是在多核cpu上
     package com.alipay.titan.dcc.dal.entity;
     import java.util.concurrent.atomic.AtomicInteger;
     public class TicketLock {
         private AtomicInteger serviceNum = new AtomicInteger();
         private AtomicInteger ticketNum  = new AtomicInteger();
         private static final ThreadLocal<Integer> LOCAL      = new ThreadLocal<Integer>();
         public void lock() {
             int myticket = ticketNum.getAndIncrement();
             LOCAL.set(myticket);
             while (myticket != serviceNum.get()) {
             }
         }
         public void unlock() {
             int myticket = LOCAL.get();
             serviceNum.compareAndSet(myticket, myticket + 1);
         }
     }
     每次都要查询一个serviceNum 服务号，影响性能（必须要到主内存读取，并阻止其他cpu修改）。
     CLHLock 和MCSLock 则是两种类型相似的公平锁，采用链表的形式进行排序，
     import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
     public class CLHLock {
         public static class CLHNode {
             private volatile boolean isLocked = true;
         }
         @SuppressWarnings("unused")
         private volatile CLHNode  tail;
         private static final ThreadLocal<CLHNode>  LOCAL   = new ThreadLocal<CLHNode>();
         private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class,
     CLHNode.class, "tail");
         public void lock() {
             CLHNode node = new CLHNode();
             LOCAL.set(node);
             CLHNode preNode = UPDATER.getAndSet(this, node);
             if (preNode != null) {
                 while (preNode.isLocked) {
                 }
                 preNode = null;
                 LOCAL.set(node);
             }
         }
        public void unlock() {
             CLHNode node = LOCAL.get();
             if (!UPDATER.compareAndSet(this, node, null)) {
                 node.isLocked = false;
             }
             node = null;
         }
   }
     CLHlock是不停的查询前驱变量， 导致不适合在NUMA 架构下使用（在这种结构下，每个线程分布在不同的物理内存区域）
     MCSLock则是对本地变量的节点进行循环。不存在CLHlock 的问题。
     01
     import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
     public class MCSLock {
         public static class MCSNode {
             volatile MCSNode next;
             volatile boolean isLocked = true;
         }
         private static final ThreadLocal<MCSNode>  NODE    = new ThreadLocal<MCSNode>();
         @SuppressWarnings("unused")
         private volatile MCSNode queue;
         private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class,MCSNode.class, "queue");
         public void lock() {
             MCSNode currentNode = new MCSNode();
             NODE.set(currentNode);
             MCSNode preNode = UPDATER.getAndSet(this, currentNode);
             if (preNode != null) {
                 preNode.next = currentNode;
                 while (currentNode.isLocked) {
                 }
             }
         }
         public void unlock() {
             MCSNode currentNode = NODE.get();
             if (currentNode.next == null) {
                 if (UPDATER.compareAndSet(this, currentNode, null)) {      
                 } else {
                     while (currentNode.next == null) {
                     }
                 }
             } else {
                 currentNode.next.isLocked = false;
                 currentNode.next = null;
             }
         }
     }
     **从代码上 看，CLH 要比 MCS 更简单，
     CLH 的队列是隐式的队列，没有真实的后继结点属性。
     MCS 的队列是显式的队列，有真实的后继结点属性。
     JUC ReentrantLock 默认内部使用的锁 即是 CLH锁（有很多改进的地方，将自旋锁换成了阻塞锁等等）**
 
   3、阻塞锁
      阻塞锁，与自旋锁不同，改变了线程的运行状态。
      在JAVA环境中，线程Thread有如下几个状态：
      1，新建状态
      2，就绪状态
      3，运行状态
      4，阻塞状态
      5，死亡状态
      阻塞锁，可以说是让线程进入阻塞状态进行等待，当获得相应的信号（唤醒，时间） 时，才可以进入线程的准备就绪状态，准备就绪状态的所有线程，通过竞争，进入运行状态。
      JAVA中，能够进入\退出、阻塞状态或包含阻塞锁的方法有 ，synchronized 关键字（其中的重量锁），ReentrantLock，Object.wait()\notify(),LockSupport.park()/unpart()(j.u.c经常使用)
      下面是一个JAVA 阻塞锁实例
      package lock;
      import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
      import java.util.concurrent.locks.LockSupport;
      public class CLHLock1 {
          public static class CLHNode {
              private volatile Thread isLocked;
          }
          @SuppressWarnings("unused")
          private volatile CLHNode  tail;
          private static final ThreadLocal<CLHNode> LOCAL   = new ThreadLocal<CLHNode>();
          private static final AtomicReferenceFieldUpdater<CLHLock1, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock1.class,CLHNode.class, "tail");
          public void lock() {
              CLHNode node = new CLHNode();
              LOCAL.set(node);
              CLHNode preNode = UPDATER.getAndSet(this, node);
              if (preNode != null) {
                  preNode.isLocked = Thread.currentThread();
                  LockSupport.park(this);
                  preNode = null;
                  LOCAL.set(node);
              }
          }
          public void unlock() {
              CLHNode node = LOCAL.get();
              if (!UPDATER.compareAndSet(this, node, null)) {
                  System.out.println("unlock\t" + node.isLocked.getName());
                  LockSupport.unpark(node.isLocked);
              }
              node = null;
          }
      }
      在这里我们使用了LockSupport.unpark()的阻塞锁。 该例子是将CLH锁修改而成。
      阻塞锁的优势在于，阻塞的线程不会占用cpu时间， 不会导致 CPu占用率过高，但进入时间以及恢复时间都要比自旋锁略慢。
      在竞争激烈的情况下 阻塞锁的性能要明显高于 自旋锁。
      理想的情况则是; 在线程竞争不激烈的情况下，使用自旋锁，竞争激烈的情况下使用，阻塞锁。
   4、可重入锁
   5、读写锁
   6、互斥锁
   7、悲观锁
   8、乐观锁
   9、公平锁
   10、非公平锁
   11、偏向锁
   12、对象锁
   13、线程锁
   14、锁粗化
   15、轻量级锁
   16、锁消除
   17、锁膨胀
   18、信号量