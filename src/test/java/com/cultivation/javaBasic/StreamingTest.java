package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                    words,
                    wordStream.collect(Collectors.toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                    words,
                    wordStream.toArray(String[]::new)
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }


    @Test
    void should_be_create_new_stream_when_modify_stream() {
        IntStream originalStream = IntStream.of(1, 2);
        IntStream modifyStream = originalStream.filter(elem -> elem > 1);

        originalStream.onClose(new Runnable() {
            @Override
            public void run() {
                System.out.println("stream has closed!");
            }
        });

        assertNotSame(modifyStream, originalStream);

    }

    @Test
    void should_not_close_stream_when_do_filter_action() {
        IntStream originalStream = IntStream.of(1, 2);
        ArrayList<Boolean> closeArrays = new ArrayList<>(1);
        closeArrays.add(false);
        originalStream.onClose(() -> closeArrays.set(0, true));
        assertFalse(closeArrays.get(0));

        originalStream.toArray();
        assertTrue(closeArrays.get(0));

    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.iterate("Echo", str -> str);
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }


    @Test
    void should_be_able_to_execute_0_times_when_skip() {
        int[] count = new int[1];

        Stream<String> originalStream = Stream.iterate("test", str -> {
            count[0]++;
            return str;
        });
        Stream<String> anotherStream = originalStream.skip(10000);
        assertTrue(count[0] == 0);
        Optional<String> optionalResult = anotherStream.findFirst();
        assertTrue(count[0] == 10000);
        optionalResult.get();
        assertTrue(count[0] == 10000);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        int[] count = {0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, number -> number + 1);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }


    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(word -> word.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        //this todo seems has problem  ????????

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map(word -> word.toUpperCase());
        // --end-->
        {
            assertArrayEquals(
                    new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                    filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start

        // this test still failed, need more time to see detail.
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                    new AnimeCharacter[]{
                            new AnimeCharacter("Naruto"),
                            new AnimeCharacter("Kisuke"),
                            new AnimeCharacter("Tomoya")
                    },
                    actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> nameList = Stream.of(Stream.of('a', 'b'), Stream.of('c', 'd'));

        Character[] resullt = nameList.flatMap(name -> name).toArray(Character[]::new);


        assertArrayEquals(new Character[]{'a', 'b', 'c', 'd'}, resullt);


    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        // this way maybe not the right answer. should find another solution.
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                    new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    finiteStream.toArray(Integer[]::new)
            );
        }
    }


    @Test
    void should_run_0_times() {
        int count[] = {0};
        Stream<Integer> integerStream = Stream.iterate(1, item -> {
            count[0]++;
            return item;
        });
        int limitNumber = 10;
        Stream<Integer> anotherStream = integerStream.limit(limitNumber);
        assertEquals(limitNumber - limitNumber, count[0]);
        anotherStream.toArray(Integer[]::new);
        assertEquals(limitNumber - 1, count[0]);
    }

    @Test
    void should_run_10_times_when_type_is_IntegerStream() {
        int count[] = {0};
        IntStream intStream = IntStream.iterate(1, item -> {
            count[0]++;
            return item;
        });


    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        // need to see more detail about concat
        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
        // --end-->
        {
            assertArrayEquals(
                    letters("HelloWorld").toArray(Character[]::new),
                    concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_concat_streams_when_stream_is_sorted() {
        Stream<Integer> helloStream = Stream.of(1, 3, 5);
        Stream<Integer> anotherHelloStream = Stream.of(1, 3, 5);
        Stream<Integer> worldStream = Stream.of(2, 4, 6);
        Stream<Integer> anotherWorldStream = Stream.of(2, 4, 6);
        Stream<Integer> unOrderStream = Stream.concat(helloStream, worldStream);
        assertArrayEquals(new Integer[]{1, 3, 5, 2, 4, 6}, unOrderStream.toArray(Integer[]::new));

        Stream<Integer> sortedSteam = Stream.concat(anotherHelloStream.sorted(), anotherWorldStream.sorted());
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, sortedSteam.toArray(Integer[]::new));

    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                    new Character[]{'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                    characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .filter(v -> v % 2 == 0)
                .sorted()
                .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {
        });

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = IntStream.iterate(0, i -> i + 2).limit(10).sum();
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }


    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, () -> empty.get());
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> {
            empty.orElseThrow(()->new IllegalStateException());
        };
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = (ch) -> {
            if (ch.isPresent()) result.add(ch.get().toUpperCase());
        };
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = (str) -> {
            Optional<Boolean> returnValue = Optional.empty();
            if (str.isPresent()) {
                result.add(str.get().toUpperCase());
                returnValue = Optional.of(true);
            }
            return returnValue;
        };
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);
        Optional<Boolean> test = optional.map(str->str.length()>0);
        test.get();
        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 4)
                .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 1)
                .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet =
                stream -> stream.findFirst().flatMap(YieldOptional::get);
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }
    Object lock=new Object();
    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        List<Integer> list = new ArrayList<>();
        int maxSize = 100000;
        for(int i=0; i <maxSize ; i++){
            list.add(i);
        }
       Stream<Integer> stream = list.stream();
       int count[] ={0,0,0};
       List<Integer> result = stream.parallel().collect(() -> {
           synchronized (lock){
               count[0]++;
           }
            return new ArrayList<>();
            },
               (arrayList, elem) -> {
                   synchronized(lock){
                       count[1]++;
                   }
                    arrayList.add(elem);
               }, (array1, array2) -> {
                 synchronized (lock){
                   count[2]++;
                   }
                   array1.addAll(array2);
               });
       assertEquals(count[0]-1,count[2]);
       assertEquals(maxSize,count[1]);
       assertEquals (ArrayList.class, result.getClass());
       assertArrayEquals (list.toArray(), result.toArray());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
          HashMap<String,Integer> map =stream.collect(Collector.of(HashMap::new,
                  (resultMap,item)->resultMap.put(item.getKey(),item.getValue()),
                  (left,right)->{left.putAll(right);
          return left;}));
        // --end-->

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = stream.collect(Collector.of(HashMap::new,(mapList, item)->{
            if(mapList.containsKey(item.getKey())){
                mapList.get(item.getKey()).add(item.getValue());
            }
            else{
                mapList.put(item.getKey(), new ArrayList<>());
                mapList.get(item.getKey()).add(item.getValue());
            }
        },(result, subResult) -> {
            result.putAll(subResult);
            return  result;
        }));

        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }


    @Test
    void collect_more_test() {
        Stream<Integer> stream =Stream.iterate(0,i->i+1).limit(9);
        Map<Integer,List<Integer>> result = stream.collect(Collector.of(HashMap::new,(map,item)->{
           if(! map.containsKey(item.intValue() % 3)){
               map.put(item.intValue() % 3,new ArrayList<>());
               map.get(item.intValue() % 3).add(item);
           }
           else{
               map.get(item.intValue() % 3).add(item);
           }
        },(combaner, rightResult)->{
            combaner.putAll(rightResult);
            return combaner;
        } ));
        HashMap<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(0,Arrays.asList(0,3,6));
        expected.put(1,Arrays.asList(1,4,7));
        expected.put(2,Arrays.asList(2,5,8));
        for(int i= 0 ;i<3;i++){
            assertArrayEquals(expected.get(i).toArray(),result.get(i).toArray());
        }

    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
//        Map<String, List<Integer>> map = stream.collect(Collectors.groupingBy(
//                                                             KeyValuePair::getKey,
//                                                             Collectors.mapping(KeyValuePair::getValue,
//                                                                                Collectors.toList())));
        Map<String, List<Integer>> map = stream.collect(Collectors.groupingBy(KeyValuePair::getKey,
                Collector.of(ArrayList::new,(list,item) -> {
                    list.add(item.getValue());
                },(result, subResult) ->{
                    result.addAll(subResult);
                    return result;
                })));

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start

        Map<String, Long> map = stream.collect(Collectors.groupingBy(KeyValuePair::getKey,Collectors.counting()));
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start

        Map<String, Integer> map = stream.collect(Collectors.groupingBy(KeyValuePair::getKey,Collectors.summingInt(t->t.getValue())));
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
                .iterate(1, i -> i + 1)
                .limit(100)
                .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = numbers.stream().reduce((result,number) -> result + number);
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = words.stream().mapToInt(m->m.length()).reduce(0, (result, length) -> result + length);
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */