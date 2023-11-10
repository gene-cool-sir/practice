package com.tts.voicetts.mock;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

/**
 * 参考: https://segmentfault.com/a/1190000006746409
 */
public class MockBaseTest {

    @Test
    public void createMockObject() {
        // 使用mock静态方法创建Mock对象
        List mockList = mock(List.class);
        Assert.assertTrue(mockList instanceof List);

        // mock 方法不仅可以Mock 接口类, 还可以Mock具体的类型
        ArrayList arrayList = mock(ArrayList.class);
        Assert.assertTrue(arrayList instanceof ArrayList);
        Assert.assertTrue(arrayList instanceof List);
    }

    /**
     * 使用 when(​...).thenReturn(​...) 方法链来定义一个行为, 例如 "when(mockedList.add("one")).thenReturn(true)" 表示: 当调用了mockedList.add("one"), 那么返回 true.. 并且要注意的是,
     * when(​...).thenReturn(​...) 方法链不仅仅要匹配方法的调用, 而且要方法的参数一样才行
     */
    @Test
    public void configMockObject() {
        List mockedList = mock(List.class);

        // 我们定制了当调用 mockedList.add("one") 时, 返回 true
        when(mockedList.add("one")).thenReturn(true);
        // 当调用 mockedList.size() 时, 返回 1
        when(mockedList.size()).thenReturn(1);

        Assert.assertTrue(mockedList.add("one"));
        // 因为我们没有定制 add("two"), 因此返回默认值, 即 false.
        Assert.assertFalse(mockedList.add("two"));
        Assert.assertEquals(mockedList.size(), 1);

        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello,").thenReturn("Mockito!");
        String result = i.next() + " " + i.next();
        //assert
        Assert.assertEquals("Hello, Mockito!", result);
    }

    @Test
    public void testForIOException() throws Exception {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello,").thenReturn("Mockito!"); // 1
        String result = i.next() + " " + i.next(); // 2
        Assert.assertEquals("Hello, Mockito!", result);
        // 我们使用了一个新语法: doThrow(ExceptionX).when(x).methodCall, 它的含义是: 当调用了 x.methodCall 方法后, 抛出异常 ExceptionX.
        // 因此 doThrow(new NoSuchElementException()).when(i).next() 的含义就是: 当第三次调用 i.next() 后, 抛出异常 NoSuchElementException.(因为 i 这个迭代器只有两个元素)
        doThrow(new NoSuchElementException()).when(i).next(); // 3
        i.next(); // 4
    }

    /**
     * 我们可以通过 verify() 静态方法来来校验指定的方法调用是否满足断言.
     */
    @Test
    public void testVerify() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        when(mockedList.size()).thenReturn(5);
        Assert.assertEquals(mockedList.size(), 5);

        verify(mockedList, atLeastOnce()).add("one");
        verify(mockedList, times(1)).add("two");
        verify(mockedList, times(3)).add("three times");
        verify(mockedList, never()).isEmpty();
    }

    /**
     * Mockito 提供的 spy 方法可以包装一个真实的 Java 对象, 并返回一个包装后的新对象.
     * 若没有特别配置的话, 对这个新对象的所有方法调用, 都会委派给实际的 Java 对象.
     */
    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        // 对 spy.size() 进行定制.
        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        // 因为我们没有对 get(0), get(1) 方法进行定制,
        // 因此这些调用其实是调用的真实对象的方法.
        Assert.assertEquals(spy.get(0), "one");
        Assert.assertEquals(spy.get(1), "two");

        Assert.assertEquals(spy.size(), 100);
    }


    @Test
    public void testCaptureArgument() {
        List<String> list = Arrays.asList("1", "2");
        List mockedList = mock(List.class);
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        mockedList.addAll(list);
        verify(mockedList).addAll(argument.capture());

        Assert.assertEquals(2, argument.getValue().size());
        Assert.assertEquals(list, argument.getValue());
    }


}
