Maven设计原则:
1）Convention Over Configuration (约定优于配置)。在软件开发过程中,如果我们事先约定好所有项目的目录结构，标 准开发过程（编译，测试，。。。），所有人都遵循这个约定，
软件项目的管理就会变得简单很多。

2）Reuse Build Logic (重用构建逻辑)：Maven把构建逻辑封装到插件中来达到重用的目的。这样在Maven就有用于编译的插件，单元测试的插件，打包的插件， 。。。
Maven可以被理解成管理这些插件的框架。

3）Declarative Execution (声明式执行)：Maven中所有的插件都是通过在POM中声明来定义的。Maven会理解所有在POM中的声明，并执行相应的插件。


命令：
创建一个Maven项目：mvn archetype:create -DgroupId=net.jianxi.tutorials  -DartifactId=helloworld  -DpackageName=net.jianxi.tutorials

mvn archetype:generate  生成项目骨架

mvn help:system  打印所有Java系统属性和环境变量

mvn dependency:list   查看当前项目已解析依赖

mvn dependency:tree   查看当前项目的依赖树

mvn dependency:analyze   分析当前项目的依赖

mvn clean compile  编译

mvn clean test   测试

mvn clean package   打包（默认jar）

mvn clean install    安装（本地仓库）

mvn clean deploy  将项目构建输出的构件部署到配置对应的远程仓库

mvn package -DskipTests.  打包但跳过测试（可以在POM xml文件中配置包含于排除测试用例）

mvn test -Dtest=Random*Test, AccoutTest   命令行动态指定要运行的测试（未指定的不会运行）

mvn test -Dtest -DfailIfNoTests= false   test必须至少匹配一个测试类否则会保存除非加上该参数

mvn cobertura:cobertura.  代码覆盖率测试

mvn cargo:redeploy    使用Cargo进行自动化部署（本地货远程web容器）

mvn jetty:run   方便开发测试（内置的jetty web容器，开发测试时省去打包和部署的步骤）

mvn release:prepare  准备版本发布（pom中需要配置scm）

mvn release:rollback  回退release:prepare 所执行的操作

mvn release:perform  执行版本发布（签出release:prepare 生成的标签中的源代码，并在此基础上执行mvn deploy打包并部署构建至仓库）