package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.NameImpl;
import com.cultivation.javaBasic.showYourIntelligence.PersonForEquals;
import com.cultivation.javaBasic.util.InterfaceExtendsInterfaceWithDefaultMethod;
import com.cultivation.javaBasic.util.InterfaceExtendsInterfaceWithDefaultMethodImpl;
import com.cultivation.javaBasic.util.InterfaceWithDefaultMethodImpl;
import com.cultivation.javaBasic.util.InterfaceWithOverrideDefaultImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterfaceTest {

    @Test
    void should_support_default_method() {
        InterfaceWithDefaultMethodImpl instance = new InterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is 42";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method() {
        InterfaceWithOverrideDefaultImpl instance = new InterfaceWithOverrideDefaultImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Anime";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method_continued() {
        InterfaceExtendsInterfaceWithDefaultMethod instance = new InterfaceExtendsInterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Game";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_resolve_ambiguity_by_yourself() {
        NameImpl instance = new NameImpl();

        String name = instance.getName();

        assertEquals("Person", name);
    }

    @Test
    void should_clone_this_object() throws CloneNotSupportedException {
        CloneClass cloneClass = new CloneClass("123", 2018);

        assertEquals("123", ((CloneClass) cloneClass.clone()).name);
        assertEquals(2018, ((CloneClass) cloneClass.clone()).year);
    }

    @Test
    void should_compare_by_our_method() {
        PersonForEquals[] arrays = new PersonForEquals[]{new PersonForEquals("jams", (short) 1990),
                new PersonForEquals("jams", (short) 1991),
                new PersonForEquals("alice", (short) 1990)};

        Arrays.sort(arrays);

        assertArrayEquals(new PersonForEquals[]
                {new PersonForEquals("alice", (short) 1990)
                        , new PersonForEquals("jams", (short) 1990)
                        , new PersonForEquals("jams", (short) 1991)}, arrays);
    }
}

/*
 * - Can you clone an object without a default constructor?
 */
