package com.hengyi.study;

import com.hengyi.study.annotations.Person;
import com.hengyi.study.flatmap.Bar;
import com.hengyi.study.flatmap.Foo;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class Test {
    @org.junit.Test
    public void test1() {
 /*       Function<T, R>
            R apply(T t);*/
        Function<
                Function<Integer, Integer>,
                Function<
                        Function<Integer, Integer>,
                        Function<Integer, Integer>>
                > compose =
                x -> y -> z -> x.apply(y.apply(z));

        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;
        Integer apply = compose.apply(square).apply(triple).apply(2);
//        Function<Integer, Integer> f = compose.apply(square).apply(triple);
//        System.out.println(f.apply(2));
        System.out.println(compose.apply(square).apply(square).apply(3));
    }

    @org.junit.Test
    public void test2() {
        System.out.println(getNamesStartingWith("s", "sb", "sadd", "ccc"));

        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara",
                "Zoë", "Jayne", "Simon", "River", "Shepherd Book");

        Optional<String> first = names.stream()
                .filter(name -> name.startsWith("C"))
                .findFirst();

        System.out.println(first);
        System.out.println(first.orElse("None"));

        System.out.println(first.orElse(String.format("No result found in %s",
                names.stream().collect(Collectors.joining(", ")))));

        System.out.println(first.orElseGet(() ->
                String.format("No result found in %s",
                        names.stream().collect(Collectors.joining(", ")))));
    }

    public String getNamesStartingWith(String s, String... names) {
        return Arrays.stream(names)
                .filter(str -> str.startsWith(s))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", "));
    }

    @org.junit.Test
    public void test3() {
        new Thread(() -> getNamesStartingWith("a", "aa")).start();

        List<String> names =
                Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace",
                        "Karen Spärck Jones");
        names.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).forEach(System.out::println);
        names.stream().sorted(String::compareTo).forEach(System.out::println);

       /* List<Person> people = names.stream()
                .map(name -> new Person(name))
                .collect(Collectors.toList());

// 或采用以下方案

        List<Person> people2 = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());*/

    }


    @org.junit.Test
    public void compose() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCompose(n -> CompletableFuture.supplyAsync(() -> n + y));

        assertTrue(5 == completableFuture.get());
    }

    @org.junit.Test
    public void combine() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)

                        .thenCombine(CompletableFuture.supplyAsync(() -> y),
                                (Integer n1, Integer n2) -> {
                                    System.out.println(n2 + "yppp");
                                    return n1 + n2;
                                });

        assertTrue(5 == completableFuture.get());
    }

    @org.junit.Test
    public void testJson() {
        /*
         * 空的JSON对象
         */
        final JsonObject empty = new JsonObject();
        System.out.println(empty.encodePrettily());
        /*
         * 字面量初始化
         */
        final String literal = "{\"age\":18,\"name\":\"Lang\"}";
        final JsonObject strData = new JsonObject(literal);
        System.out.println(strData.encodePrettily());
        /*
         * 使用Map初始化
         */
        final Map<String, Object> map = new HashMap<String, Object>() {
            {
                this.put("email", "lang.yu@hpe.com");
                this.put("age", 18);
            }
        };
        final JsonObject mapData = new JsonObject(map);
        System.out.println(mapData.encodePrettily());
        /*
         * 使用Buffer初始化
         */
        final Buffer buffer = Buffer.buffer(literal);
        final JsonObject bufferData = new JsonObject(buffer);
        System.out.println(bufferData.encodePrettily());
    }

    @org.junit.Test
    public void testaa() {
        Optional<String> optional = Optional.of(null);

        optional.isPresent();           // true
        optional.get();                 // "bam"
        String fallback = optional.orElse("fallback");// "bam"
        System.out.println(fallback + "");

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    @org.junit.Test
    public void testMap() {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        String s = map.computeIfPresent(9, (num, val) -> null);
        System.out.println(s + "aaa");


        map.remove(9);
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9
        System.out.println("mergenull" + map.get(9));

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat
        System.out.println("mergenotnull" + map.get(9));


        map.containsKey(9);     // false
        int a = 10;
        int b = 9;
        if (add(a, b) == 90) {
            System.out.println("aaaa");
        }

    }

    public int add(int a, int b) {
        return a + b;
    }

    @org.junit.Test
    public void test14() {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);    // Pamela


        Person result =
                persons
                        .stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });

        System.out.format("name=%s; age=%s", result.name, result.age);

        Map<Integer, List<Person>> map = persons.stream().collect(Collectors.groupingBy(p -> p.age));
        map.forEach((a, b) -> {
            System.out.println(a + "===" + b);
        });
        persons.stream().collect(Collectors.averagingInt(p -> p.age));

        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));
        double rrr = ageSummary.getAverage();
        System.out.println(ageSummary);
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);


        // 自定义收集器
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID


    }

    //测试flatmap
    @org.junit.Test
    public void testFlatMap() {
        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4).forEach(a -> {
            foos.add(new Foo("Foo" + a));
        });
        // create bars
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
        foos.stream().flatMap(foo -> foo.bars.stream()).forEach(bar -> System.out.println(bar.name));

        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3
        Arrays.asList("a", "b", "c", "d")
                .parallelStream()
                .filter(a -> {
                    System.out.println(Thread.currentThread().getName() + "==" + a);
                    return true;
                })
                .map(b -> {
                    System.out.println(Thread.currentThread().getName() + "map" + b);
                    return b.toUpperCase();
                }).forEach(a -> {
            System.out.println(a + "&&" + Thread.currentThread().getName());
        });
    }

    @org.junit.Test
    public void tsPoolThead() {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        String result = null;
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    @org.junit.Test
    public void testFiles() {
        Path start = Paths.get("C:\\Users\\chen\\Desktop\\testfile");
        int maxDepth = 5;
        try {
            Stream<Path> stream = Files.find(start, maxDepth, (path, attr) -> String.valueOf(path).endsWith(".js"));

            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("Found: " + joined);
        } catch (Exception e
        ) {

        }
        HashSet a = new HashSet();
        a.add("a");
        a.add("a");
        a.add("b");
        a.add("d");
        a.add("c");
        a.stream().forEach(System.out::print);
    }

    @org.junit.Test
    public void testfile2() throws Exception {
/*        File file = new File("C:\\Users\\chen\\Desktop\\testfile","aa") ;
        System.out.println(file.isDirectory());
        File file1 = new File("C:\\Users\\chen\\Desktop\\testfile") ;
        Arrays.asList(file1.list((dir,name)->{
            System.out.println("name"+name);
            return name.endsWith(".js") ;
        })).forEach(System.out::print);   ;*/
        writFileTest();
        readFileTest();
    }


    private static void writFileTest() throws FileNotFoundException,
            IOException {
        // 创建文件对象
        File file = new File("E:\\a.txt");
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream(file);
        fos.write('g');
        fos.write('z');
        fos.write('i');
        fos.write('t');
        fos.write('c');
        fos.write('a');
        fos.write('s');
        fos.write('t');
        fos.close();
    }

    private static void readFileTest() throws FileNotFoundException,
            IOException {
        // 创建文件对象
        File file = new File("E:\\a.txt");
        // 创建文件输入流
        FileInputStream fis = new FileInputStream(file);
        // 有对多长，就读多少字节。
        for (int i = 0; i < file.length(); i++) {
            System.out.print((char) fis.read());
        }
        fis.close();
    }
    @org.junit.Test
    public void testStr(){
        final ServiceLoader<Test> load = ServiceLoader.load(Test.class);
        System.out.println("");
    }


}
