package com.example.decoratormodel;

/**
 * @author Gene.xie
 * @version 1.0
 * @projectName practice
 * @package com.example.decoratormodel
 * @className Decorator
 * @description
 * @date 2023/2/9 11:07
 */
public abstract  class Decorator implements Component{
    public Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}
 