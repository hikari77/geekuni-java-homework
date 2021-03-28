#### 必做作业4 不同GC和堆的总结


### Serial GC
+ xmx512m 时前几次young gc（平均20ms）之后都是频繁的full gc（平均30ms）
+ xmx1g，两次full gc(平均50ms) 总次数17次, young gc(平均30ms)，堆大了以后gc总次数和full gc的次数明显都减少。
+ 蓄水池的作用发挥了，但是gc时间明显增加，水池子大了清理起来更费时

### Parallel GC
+ 与串行的情况差不多，xmx大了之后gc次数减少 full gc频率减少很多，平均时间都增加
+ 若不设置xmx 总gc次数会少但是单次的时间会长，可能是因为有开销在动态调整堆的大小。


### CMS GC
+ 本机环境 OpenJDK15 
+ 启用CMS GC命令后发现CMS已经不支持了,所以去查了一下,从java9开始deprecated,java14的时候已经移除。
  
+ JEP 291: Deprecate the Concurrent Mark Sweep (CMS) Garbage Collector
+ JEP 363: Remove the Concurrent Mark Sweep (CMS) Garbage Collector

### G1 GC
+ 全部默认配置的时候没有产生full gc，可以看到堆大小动态增加到2G以上
+ 4G的时候也没有产生full gc，每次时间比较稳定猜测是因为region大小已固定
+ 512m的时候非常频繁的并发收集以及full gc，也会很多的humongous区域的开辟


小结：总的来看回收需要耗费的时间和要回收的区域大小是正比

低延时，高吞吐，内存占用等等多个维度考量的话只能满足一两个，所以因情况而定。