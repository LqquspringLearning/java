package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnonymousClassUpdateField;
import com.cultivation.javaBasic.util.InnerClassUpdateField;
import com.cultivation.javaBasic.util.LocalClassUpdateField;
import com.cultivation.javaBasic.util.StaticInnerClass;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InnerClassTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_access_instance_field_of_parent_class() {

    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_refer_inner_class_from_outside() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);
        InnerClassUpdateField.YearIncrementer yearIncrementer = innerClassUpdateField.new YearIncrementer();
        yearIncrementer.increment(2);
        assertTrue(2020 == innerClassUpdateField.getYear());
    }

    @Test
    void should_refer_inner_class_from_outside_class() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);
        InnerClassUpdateField.YearIncrementer yearIncrementer = innerClassUpdateField.new YearIncrementer(2);
        assertTrue(2020 == innerClassUpdateField.getYear());
    }

    @Test
    void should_add_inner_class_filed_to_outer_class() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);
        InnerClassUpdateField.YearIncrementer yearIncrementer = innerClassUpdateField.new YearIncrementer(2);
        yearIncrementer.add();
        assertTrue(2020 == innerClassUpdateField.getYear());

    }

    @Test
    void should_call_outer_class_add_method() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);
        innerClassUpdateField.add();
        assertTrue(2020 ==innerClassUpdateField.getYear());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_update_field_using_local_class() {
        LocalClassUpdateField instance = new LocalClassUpdateField();
        //inner class directly write in a method.
        // and after define create a new inner class and call a method in a class method.
        // how to call this  inner class method?
        // ans: after define new <innerClassName>().method()
        instance.somethingHappen();

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Integer> expected = Optional.of(2019);
        // --end-->

        assertEquals(expected.get().intValue(), instance.getYear());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_update_field_using_anonymous_class() {
        AnonymousClassUpdateField instance = new AnonymousClassUpdateField();
        instance.somethingHappen();

        // TODO: please modify the following code to pass the test
        // <--start
        //what is runnable?
        // The Runnable interface should be implemented by
        // any class whose instances are intended to be executed by a thread.
        // The class must define a method of no arguments called run.
        //why we need override run method?
        //When an object implementing interface Runnable is used to create a thread,
        // starting the thread causes the object's run method to be called in
        // that separately executing thread.
        //Thread.run():If this thread was constructed using a separate Runnable run object,
        // then that Runnable object's run method is called; otherwise,
        // this method does nothing and returns.
        final Optional<Integer> expected = Optional.of(2019);
        // --end-->

        assertEquals(expected.get().intValue(), instance.getYear());
    }

    @Test
    void should_create_instance_for_static_inner_class() {
        StaticInnerClass instance = new StaticInnerClass();
        StaticInnerClass.Inner inner = instance.createInner();
         // a static inner class can be use new <innerClassName>(),
        // even if the innerClass is static.
        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "Hello";
        // --end-->

        assertEquals(expected, inner.getName());
    }
}
