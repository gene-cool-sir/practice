package com.study.rocketmq.component;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 *
 */
@Slf4j
@NoArgsConstructor
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean, BeanFactoryPostProcessor, Serializable {

	private static final long serialVersionUID = 1L;
	private static ApplicationContext applicationContext = null;
	private static BeanDefinitionRegistry beanDefinitionFactory = null;

	public SpringContextHolder(ApplicationContext applicationContext) {
		if (applicationContext != null) {
			synchronized (SpringContextHolder.class) {
				SpringContextHolder.applicationContext = applicationContext;
				if (applicationContext instanceof BeanDefinitionRegistry) {
					SpringContextHolder.beanDefinitionFactory = (BeanDefinitionRegistry) applicationContext;
				}
			}
		}
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 获取环境
	 * 
	 * @return
	 */
	public static Environment getEnvironment() {
		assertContextInjected();
		return applicationContext.getEnvironment();
	}

	/**
	 * 获取环境属性
	 * 
	 * @param key 属性名
	 * @return
	 */
	public static String getEnvProperty(String key) {
		assertContextInjected();
		return applicationContext.getEnvironment().getProperty(key);
	}

	/**
	 * 获取环境属性
	 * 
	 * @param key          属性名
	 * @param defaultValue 不存在时的默认值
	 * @return
	 */
	public static String getEnvProperty(String key, String defaultValue) {
		assertContextInjected();
		return applicationContext.getEnvironment().getProperty(key, defaultValue);
	}

	/**
	 * 获取环境属性
	 * 
	 * @param <T>
	 * @param key        属性名
	 * @param targetType 属性类型
	 * @return
	 */
	public static <T> T getEnvProperty(String key, Class<T> targetType) {
		assertContextInjected();
		return applicationContext.getEnvironment().getProperty(key, targetType);
	}

	/**
	 * 获取环境属性
	 * 
	 * @param <T>
	 * @param key          属性名
	 * @param targetType   属性类型
	 * @param defaultValue 不存在时的默认值
	 * @return
	 */
	public static <T> T getEnvProperty(String key, Class<T> targetType, T defaultValue) {
		assertContextInjected();
		return applicationContext.getEnvironment().getProperty(key, targetType, defaultValue);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得定义的beanNames, 自动转型为所赋值对象的类型.
	 */
	public static String[] getBeanDefinitionNames() {
		assertContextInjected();
		return applicationContext.getBeanDefinitionNames();
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 获取可能为Null的Bean对象
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public static <T> T getNullableBean(Class<T> requiredType) {
		Map<String, T> map = getBeanMap(requiredType);
		if (map == null || map.isEmpty()) {
			return null;
		}
		return map.values().stream().filter(Objects::nonNull).findFirst().orElse(null);
	}

	/**
	 * 获取某个类型的Bean对象map集合
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public static <T> Map<String, T> getBeanMap(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBeansOfType(requiredType);
	}

	/**
	 * 遍历找到第一个符合类型的bean
	 * 
	 * @param <T>
	 * @param beanType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findFirstBean(@NonNull Class<T> beanType) {
		assertContextInjected();
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		if (beanDefinitionNames == null) {
			return null;
		}
		return (T) Arrays.stream(beanDefinitionNames).filter(Objects::nonNull)
				.map(applicationContext::getBean).filter(obj -> true)
				.filter(b -> beanType.isAssignableFrom(b.getClass())).findAny().orElse(null);
	}

	/**
	 * 判定存在某个class的bean
	 * 
	 * @param <T>
	 * @param beanType
	 * @return
	 */
	public static <T> boolean isContains(@NonNull Class<T> beanType) {
		return getNullableBean(beanType) != null;
	}

	/**
	 * 获取某个类型的Bean列表
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public static <T> List<T> getBeanList(Class<T> requiredType) {
		Map<String, T> map = getBeanMap(requiredType);
		if (map != null) {
			List<T> list = new ArrayList<>(map.size());
			for (Entry<String, T> entry : map.entrySet()) {
				T bean = entry.getValue();
				if (bean != null) {
					list.add(bean);
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		applicationContext = null;
		log.debug("清除SpringContextHolder中的BeanDefinitionRegistry:" + beanDefinitionFactory);
		beanDefinitionFactory = null;
	}

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (log.isDebugEnabled()) {
			log.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
		}
		if (SpringContextHolder.applicationContext != null) {
			if (SpringContextHolder.applicationContext.equals(applicationContext)) {
				return;
			}
			if (applicationContext != null) {
				log.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
						+ SpringContextHolder.applicationContext);
			} else {
				return;
			}
		}
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() {
		SpringContextHolder.clearHolder();
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new NullPointerException("applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
		}
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertFactoryInjected() {
		if (beanDefinitionFactory == null) {
			throw new NullPointerException(
					"beanDefinitionFactory属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
		}
	}

	/**
	 * 从类中提取bean名称,类名的简称，并首字母小写
	 * 
	 * @param clazz
	 * @return
	 */
	private static String getBeanNameFromClass(Class<?> clazz) {
		String clazzName = clazz.getSimpleName();
		return clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
	}

	/**
	 * 注册已知的对象作为bean，如果名称已经存在，则抛出异常
	 * 
	 * @param javaBean  已知非空对象
	 */
	public static <T> void registerBeanDefinitionFromJavaBean(
			@NonNull final T javaBean) {
		// 使用已有实例,该方法特别适用于动态代理模式生成bean
		@SuppressWarnings("unchecked")
		Class<T> javaClass = (Class<T>) javaBean.getClass();
		registerBeanDefinition(beanDefinitionFactory, javaClass, getBeanNameFromClass(javaClass), null, () -> javaBean);
	}

	/**
	 * 注册已知的对象作为bean，如果名称已经存在，则抛出异常
	 * 
	 * @param beanUniqueName bean名
	 * @param javaBean       已知非空对象
	 */
	public static <T> void registerBeanDefinitionFromJavaBean(
			String beanUniqueName,
			final @NonNull T javaBean) {
		// 使用已有实例,该方法特别适用于动态代理模式生成bean
		@SuppressWarnings("unchecked")
		Class<T> javaClass = (Class<T>) javaBean.getClass();
		registerBeanDefinition(beanDefinitionFactory, javaClass, beanUniqueName, null, () -> javaBean);
	}

	/**
	 * 注册已知的对象作为bean，如果名称已经存在，则抛出异常 
	 * 
	 * @param beanUniqueName   bean唯一名
	 * @param beforeRegister   在注入bean之前执行额外任务，常用于配置bean, 创建beanDefinition之前，配置builder，常用于配置bean的构造，属性等,配置bean的init-method，destroy-method等,可以通过beanDefinitionBuilder.getBeanDefinition()获取bean定义
	 * @param javaBean         已知非空对象
	 */ 
	public static <T> void registerBeanDefinitionFromJavaBean(
			String beanUniqueName,
			final Consumer<BeanDefinitionBuilder> beforeRegister,
			final @NonNull T javaBean) {
		// 使用已有实例,该方法特别适用于动态代理模式生成bean
		@SuppressWarnings("unchecked")
		Class<T> javaClass = (Class<T>) javaBean.getClass();
		registerBeanDefinition(beanDefinitionFactory, javaClass, beanUniqueName, beforeRegister, () -> javaBean);
	}
   
	/**
	 * 注册构造bean
	 * 
	 * @param beanClass                   bean类
	 * @param propertiesValueMap          属性-值Map
	 * @param propertiesRefMap            属性-引用（类似property ref=‘xxx’） 属性依赖bean的值Map
	 * @param orderedConstructorArgValues 构造参数
	 */
	public static void registerBeanDefinition(
			Class<?> beanClass, 
			Map<String, Object> propertiesValueMap,
			Map<String, String> propertiesRefMap, 
			Object... orderedConstructorArgValues) {
		registerBeanDefinition(beanClass, getBeanNameFromClass(beanClass), propertiesValueMap, propertiesRefMap, orderedConstructorArgValues);
	}

	/**
	 * 注册构造bean
	 * 
	 * @param beanClass                   bean类
	 * @param beanUniqueName              bean唯一名
	 * @param propertiesValueMap          属性-值Map
	 * @param propertiesRefMap            属性-引用（类似property ref=‘xxx’） 属性依赖bean的值Map
	 * @param orderedConstructorArgValues 构造参数
	 */
	public static synchronized void registerBeanDefinition(
			Class<?> beanClass, 
			String beanUniqueName,
			Map<String, Object> propertiesValueMap, 
			Map<String, String> propertiesRefMap,
			Object... orderedConstructorArgValues) {
		registerBeanDefinition(beanDefinitionFactory, beanClass, beanUniqueName, (beanDefinitionBuilder) -> {
			if (orderedConstructorArgValues != null && orderedConstructorArgValues.length > 0) {
				for (Object arg : orderedConstructorArgValues) {
					beanDefinitionBuilder.addConstructorArgValue(arg);
				}
			}
			if (propertiesValueMap != null && propertiesValueMap.size() > 0) {
				propertiesValueMap.entrySet().stream().forEach(e -> {
					beanDefinitionBuilder.addPropertyValue(e.getKey(), e.getValue());
				});
			}
			if (propertiesRefMap != null && propertiesRefMap.size() > 0) {
				propertiesRefMap.entrySet().stream().forEach(e -> {
					beanDefinitionBuilder.addPropertyReference(e.getKey(), e.getValue());
				});
			}
		}, null);
	}
	
	/**
	 * 生成已知对象，并注册Bean
	 * 
	 * @param beanClass        bean类
	 */
	public static <T> void registerBeanDefinition(Class<T> beanClass) {
		registerBeanDefinition(beanDefinitionFactory, beanClass, getBeanNameFromClass(beanClass), null, () -> {
			try {
				return beanClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
  
	/**
	 * 生成已知对象，并注册Bean
	 * 
	 * @param beanClass        bean类
	 * @param supplierFunction 生成bean对应对象接口
	 */
	public static <T> void registerBeanDefinition(
			Class<T> beanClass,  
			Supplier<T> supplierFunction) {
		registerBeanDefinition(beanDefinitionFactory, beanClass, getBeanNameFromClass(beanClass), null, supplierFunction);
	}
	
	/**
	 * 生成已知对象，并注册Bean
	 * 
	 * @param beanClass        bean类
	 * @param beanUniqueName   bean唯一名
	 * @param supplierFunction 生成bean对应对象接口
	 */
	public static <T> void registerBeanDefinition(
			Class<T> beanClass, 
			String beanUniqueName, 
			Supplier<T> supplierFunction) {
		registerBeanDefinition(beanDefinitionFactory, beanClass, beanUniqueName, null, supplierFunction);
	}
	
	/**
	 * 生成已知对象，并注册Bean
	 * 
	 * @param beanClass        bean类 
	 * @param beforeRegister   在注入bean之前执行额外任务，常用于配置bean, 创建beanDefinition之前，配置builder，常用于配置bean的构造，属性等,配置bean的init-method，destroy-method等,可以通过beanDefinitionBuilder.getBeanDefinition()获取bean定义
	 * @param supplierFunction 生成bean对应对象接口
	 */
	public static <T> void registerBeanDefinition(
			Class<T> beanClass,  
			Consumer<BeanDefinitionBuilder> beforeRegister, 
			Supplier<T> supplierFunction) {
		registerBeanDefinition(beanDefinitionFactory, beanClass, getBeanNameFromClass(beanClass), beforeRegister, supplierFunction);
	}

	/**
	 * 生成已知对象，并注册Bean
	 * 
	 * @param beanClass        bean类
	 * @param beanUniqueName   bean唯一名
	 * @param beforeRegister   在注入bean之前执行额外任务，常用于配置bean, 创建beanDefinition之前，配置builder，常用于配置bean的构造，属性等,配置bean的init-method，destroy-method等,可以通过beanDefinitionBuilder.getBeanDefinition()获取bean定义
	 * @param supplierFunction 生成bean对应对象接口
	 */
	public static <T> void registerBeanDefinition(
			Class<T> beanClass, 
			String beanUniqueName,
			Consumer<BeanDefinitionBuilder> beforeRegister, 
			Supplier<T> supplierFunction) {
		registerBeanDefinition(beanDefinitionFactory, beanClass, beanUniqueName, beforeRegister, supplierFunction);
	}

	/**
	 * 生成已知对象，并注册Bean
	 * 
	 * @param factory          Bean工厂
	 * @param beanClass        bean类
	 * @param beanUniqueName   bean唯一名
	 * @param beforeRegister   在注入bean之前执行额外任务，常用于配置bean, 创建beanDefinition之前，配置builder，常用于配置bean的构造，属性等,配置bean的init-method，destroy-method等,可以通过beanDefinitionBuilder.getBeanDefinition()获取bean定义
	 * @param supplierFunction 生成bean对应对象接口
	 */
	public static synchronized <T> void registerBeanDefinition(
			BeanDefinitionRegistry factory,
			@NonNull Class<T> beanClass, 
			@NonNull String beanUniqueName, 
			Consumer<BeanDefinitionBuilder> beforeRegister,
			Supplier<T> supplierFunction) {
		assertFactoryInjected();
		if (factory.isBeanNameInUse(beanUniqueName) || factory.containsBeanDefinition(beanUniqueName)) {
			throw new RuntimeException("Bean '" + beanUniqueName + "' already exists.");
		}
		BeanDefinitionBuilder beanDefinitionBuilder = supplierFunction == null 
				? BeanDefinitionBuilder.genericBeanDefinition(beanClass) 
				: BeanDefinitionBuilder.genericBeanDefinition(beanClass, supplierFunction); 
		Optional.ofNullable(beforeRegister).ifPresent(br -> br.accept(beanDefinitionBuilder));  
		factory.registerBeanDefinition(beanUniqueName, beanDefinitionBuilder.getRawBeanDefinition());
	}

	@Override
	public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (SpringContextHolder.applicationContext != null
				&& SpringContextHolder.applicationContext instanceof BeanDefinitionRegistry) {
			SpringContextHolder.beanDefinitionFactory = (BeanDefinitionRegistry) SpringContextHolder.applicationContext;
			return;
		}
		if (beanFactory instanceof BeanDefinitionRegistry) {
			SpringContextHolder.beanDefinitionFactory = (BeanDefinitionRegistry) beanFactory;
			return;
		}
		throw new BeanNotOfRequiredTypeException("beanDefinitionFactory", BeanDefinitionRegistry.class,
				beanFactory.getClass());
	}
}
