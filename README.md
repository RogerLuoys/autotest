# 一、概述
autotest是一个可以同时实现http、rpc和ui自动化的工程。\
它最大的特点是所有框架内方法都通过(统一入口).(接口).(方法)的模式调用。\
主要功能有：\
1、http、rpc接口调用\
2、ui自动化元素的获取和控制\
3、数据库操作\
4、常用的数据转换功能

# 二、功能模块介绍
1、oldCode.api  包含用于http和rpc接口调用的公共类\
2、oldCode.db   包含用于数据库操作的公共类\
3、oldCode.ui   包含用于ui自动化元素的操作的公共类\
4、oldCode.util 包含其他可能用到的工具类

# 三、测试模块介绍
1、***Proxy 自定义的统一入口类，包含所有框架方法的实例对象\
2、***TestBase 所有测试类的超类，统一入口类在此实例化，通用参数和准备步骤也在此\
3、***Test 测试类，需继承对应的TestBase超类，内部包含针对性的数据准备步骤，和对应的测试步骤
