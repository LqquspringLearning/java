* 思想的转变。
    * 改变原来的搜索方式，改为Google+英语。
    * 改变原来的学习习惯, 刨根问底的去学习。（踢箱子）
    * 改变原来获取知识的方式：不是看博客，而是看官方文档和source code.
* 学习内容
    * Java basic
        * 所有的东西计算机中的存储方式。
        * `>>>` diff `>>`
        * `!` > `&` > `|` 优先级问题。
        * 不同的类型有大小不同的空间去存储，并且有最大表示范围和最小表示范围。
        * 不同进制之间的相互转化。
        * 小心溢出行为：`该类型`所能表示的`最大数`+1，和`该类型`所能表示的`最小数`-1
        * 数据类型之间的相互转化：强转注意精度丢失问题（强制取整）。
        * 1.0D / 0.0D, -1.0D / 0.0D, 0.0D / 0.0D。
        * NaN(not a number)!=NaN 用于判断NaN相等。
        * Java编码历史（UCS-2 -> UTF-16(1.5+)），Code Point.
        * `||` diff `|` 和 `&&` diff `&`
        * 自己如何实现一个简单的`Stack`
        * `==` diff `.equals()`
        * 类型数组是一个类，
        * 不同类型的默认值。
        * 初始化类时候，基本类型是有默认值的，类型类为`Null`.
        * 除了FunctionalInterface，`Object`是其他类类型的基类。
        * 构造函数的调用顺序（parent-> child）,field 初始化顺序和code block调用顺序（谁在上边先调用谁）。
        * final表示指向关系不能变，但是指向地址的内容可以变。
        * 子类和父类的数组，之间没有关系，强制转化没有问题，但是给元素赋值时会抛异常，因为数组元素size不同。
        * 如何通过类名字符串获取对象的实例。
        * 如何获取一个类内所有的具有某种修饰符的方法，field。
        * 如何获取具有某种注解的方法和类。
        * 如何自己写注解，并指定注解的作用范围，和存在的时期。
        * 接口的default方法，接口的默认修饰符`abstract`,接口方法的默认修饰符`public`,
        * 如何在继承多个接口子类中显式的调用接口的`default`method.
        * 如何自定义排序方法`Compareable`接口。
        * 不同的语言，有不同的代码命名规范`under_score`,`camelCase`,`UPPER_CASE `,`Pascal case`,Java using `camelCase`.
        * 如何自己写lambda方法。
        * `lambda` diff `anonymous class` diff `method ref`(`::`)
        * 函数签名构成（方法名+参数列表）
        * 各种快捷键
        * `getClass()` diff `instanceof`（在重写`equal`方法过程中）
        * `equal` `hashCode`强弱程度。
        * Java 闭包: 函数+运行环境（capture 定义时的context）
       
