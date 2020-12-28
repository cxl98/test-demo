1.BUILD:所有支持平台的编译配置和生成文件。使用此文件夹进行编译和链接(The compilation configuration and make files for all platforms supported.Use this folder for compilation and linking)
client:MySQL命令行客户机工具(The MySQL command-line client tool)
2.dbug:调试中使用的实用程序(Utilities for use in debugging)
3.Docs:当前版本的文档。 Linux用户应使用support子文件夹中的generate-text-files.pl来生成文档。 Windows用户提供了一个manual.chm文件(Documentation for the current release. Linux users should usegenerate-text-files.pl in the support subfolder to generate the documentation. Windows users are provided with a manual.chm file).


4.include:基本系统包括文件和头文件(The base system include files and headers).


5.libmysql:用于创建嵌入式系统的C客户端API(The C client API used for creating embedded systems). 


6.libmysqld:核心服务器API文件。也用于创建嵌入式系统(The core server API files. Also used in creating embedded systems.)


7.mysql-test:MySQL系统测试套件(The MySQL system test suite).


8.mysys:大多数核心操作系统API包装器和帮助器功能(The majority of the core operating system API wrappers and helper functions).


9.regex:正则表达式库。在查询优化器和执行中用于解析表达式(A regular expression library. Used in the query optimizer and execution to resolve expressions).


10.scripts:一组基于Shell脚本的实用程序(A set of shell script-based utilities) .


11.sql:主要系统代码。您应该从该文件夹开始探索(The main system code. You should start your exploration from this folder).


12.sql-bench: 一组基准测试实用程序(A set of benchmarking utilities).


13.SSL: 一组安全套接字层实用程序和定义(A set of Secure Socket Layer utilities and definitions).


14.storage:MySQL可插拔存储引擎源代码位于此文件夹内。还包括存储引擎示例代码 (The MySQL pluggable storage engine source code is located inside this folder. Also included is the storage engine example code). 


15.strings: 核心字符串处理包装器。使用这些来满足您所有的字符串处理需求(The core string handling wrappers. Use these for all of your string handling needs).


16.support-files: 一组用于使用不同选项进行编译的预配置文件(A set of preconfigured configuration files for compiling with different options).


17.tests: 一套测试程序和测试文件(A set of test programs and test files).


18.vio:网络和套接字层代码(The network and socket layer code) .


19.zlib: 数据压缩工具(Data compression tools).

