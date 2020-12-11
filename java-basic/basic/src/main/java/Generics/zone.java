package Generics;
public @interface zone {}
/**
 * Created by Defias on 2020/07.
 * Description:  泛型 - 理解Type接口

 Type接口
 public interface Type {
    default String getTypeName() {
        return toString();
    }
 }

 所有类型的父接口, 如:
 原始类型(raw types,对应Class)
 参数化类型(parameterized types, 对应ParameterizedType)
 数组类型(array types,对应GenericArrayType)
 类型变量(type variables, 对应TypeVariable)
 基本(原生)类型(primitive types, 对应Class),子接口有ParameterizedType, TypeVariable<D>, GenericArrayType, WildcardType,
 实现类有Class

 Type及其子接口的来历
 1、泛型出现之前的类型
 没有泛型的时候，只有原始类型。此时，所有的原始类型都通过字节码文件类Class类进行抽象。Class类的一个具体对象就代表一个指定的原始类型

 2、泛型出现之后的类型
 泛型出现之后，扩充了数据类型。从只有原始类型扩充了参数化类型、类型变量类型、限定符类型 、泛型数组类型

 3、与泛型有关的类型不能和原始类型统一到Class的原因与java中的解决办法
 1）产生泛型擦除的原因
 原始类型和新产生的类型都应该统一成各自的字节码文件类型对象。但是由于泛型不是最初Java中的成分。如果真的加入了泛型，涉及到JVM指令集的修改，这是非常
 致命的

 2）Java中如何引入泛型
 为了使用泛型又不真正引入泛型，Java采用泛型擦除机制来引入泛型。Java中的泛型仅仅是给编译器javac使用的，确保数据的安全性和免去强制类型转换的麻烦。但
 是，一旦编译完成，所有的和泛型有关的类型全部擦除

 3）Class不能表达与泛型有关的类型
 因此，与泛型有关的参数化类型、类型变量类型、限定符类型 、泛型数组类型这些类型编译后全部被打回原形，在字节码文件中全部都是泛型被擦除后的原始类型，并
 不存在和自身类型对应的字节码文件。所以和泛型相关的新扩充进来的类型不能被统一到Class类中

 4）与泛型有关的类型在Java中的表示
 为了通过反射操作这些类型以迎合实际开发的需要，Java就新增了ParameterizedType, TypeVariable<D>, GenericArrayType, WildcardType几种类型来代表不能
 被归一到Class类中的类型但是又和原始类型齐名的类型

 5）引入Type的原因
 为了程序的扩展性，最终引入了Type接口作为Class和ParameterizedType, TypeVariable<D>, GenericArrayType, WildcardType这几种类型的总的父接口。这样
 可以用Type类型的参数来接受以上五种子类的实参或者返回值类型就是Type类型的参数。统一了与泛型有关的类型和原始类型Class

 6）Type接口中没有方法的原因
 从上面看到，Type的出现仅仅起到了通过多态来达到程序扩展性提高的作用，没有其他的作用。因此Type接口的源码中没有任何方法
 */
