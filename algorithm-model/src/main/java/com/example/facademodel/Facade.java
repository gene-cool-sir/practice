package com.example.facademodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.facademodel
 * @className Facade
 * @description
 * 外观角色（Facade）:也称门面角色，系统对外的统一接口
 * 子系统角色（SubSystem）:可以同时有一个或者多个SubSystem，SubSystem并不知道Facade的存在，Facade对于SubSystem而言仅仅只是一个客户端
 *
 * 门面模式应用场景
 *
 * 对分层结构系统构建时，使用门面模式定义子系统中每层的入口点可以简化子系统之间的依赖关系。
 * 当一个复杂系统的子系统很多时，外门面模式可以为系统设计一个简单的接口供外界访问
 * 当客户端与多个子系统之间存在很大的联系时，引入门面模式可将它们分离，从而提高子系统的独立性和可移植性
 *
 * @date 2023/2/9 10:52
 */
public class Facade {
    private final SystemA systemA = new SystemA();
    private final SystemB systemB = new SystemB();
    private final SystemC systemC = new SystemC();

    public void doSomething() {
        systemA.doSomething();
        systemB.doSomething();
        systemC.doSomething();
    }
}
 