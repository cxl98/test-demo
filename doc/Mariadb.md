1.BUILD:所有支持平台的编译配置和生成文件。使用此文件夹进行编译和链接(The compilation configuration and make files for all platforms supported.Use this folder for compilation and linking)
client:MySQL命令行客户机工具(The MySQL command-line client tool)
2.dbug:调试中使用的实用程序(Utilities for use in debugging)
3.Docs:当前版本的文档。 Linux用户应使用support子文件夹中的generate-text-files.pl来生成文档。 Windows用户提供了一个manual.chm文件(Documentation for the current release. Linux users should usegenerate-text-files.pl in the support subfolder to generate the documentation. Windows users are provided with a manual.chm file).


4.include:The base system include files and headers.


5.libmysql:The C client API used for creating embedded systems. (See Chapter 6 formore details.)


6.libmysqld:The core server API files. Also used in creating embedded systems.(See Chapter 6 for more details.)


7.mysql-test:The MySQL system test suite. (See Chapter 4 for more details.)


8.mysys:The majority of the core operating system API wrappers and helper functions.


9.regex:A regular expression library. Used in the query optimizer and execution to resolve expressions.


10.scripts: A set of shell script-based utilities.


11.sql: The main system code. You should start your exploration from this folder.


12.sql-bench: A set of benchmarking utilities.


13.SSL: A set of Secure Socket Layer utilities and definitions.


14.storage: The MySQL pluggable storage engine source code is located inside this folder. Also included is the storage engine example code. (See Chapter 7 for more details.)


15.strings: The core string handling wrappers. Use these for all of your string handling needs.


16.support-files: A set of preconfigured configuration files for compiling with different options.


17.tests: A set of test programs and test files.


18.vio: The network and socket layer code.


19.zlib: Data compression tools.

