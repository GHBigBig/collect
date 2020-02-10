package com.zjg.collect;

import com.zjg.collect.network.GetResource;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zjg
 * @create 2020-01-23 11:20
 */
public class Test01 {
    @Test
    public static void main(String[] args) {
        String str = "src=\"images/attachement/gif/site2/20180829/dcfe0718e9c81cf03a8712.gif\"";
        String s = str.replaceAll("src=\"?|[\">]?", "");
        System.out.println(s);
    }

    /**
     * 测试创建临时路径
     */
    @Test
    public void test01() {
        File temp = null;
        try {
            temp = File.createTempFile("testrunoobtmp", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件路径: "+temp.getAbsolutePath());
    }

    /**
     * 测试 FilenameUtils 获取文件名相关
     */
    @Test
    public void test02() {
        String s = "jin05.gif";
        System.out.println(FilenameUtils.getBaseName(s));
        System.out.println(FilenameUtils.getExtension(s));

    }

    /**
     * 测试 reduce
     * <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
     */
    @Test
    public void test03() {
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
                .reduce(new ArrayList<Integer>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator");
                                acc.addAll(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("--------");
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);
    }

    /**
     * 测试 reduce
     */
    @Test
    public void test04() {
        Byte aByte = new Byte((byte)8);
        Optional<Integer> reduce = Stream.of(1, 2, 3, 4)
                .reduce((acc, item) -> {
                    System.out.println("acc : " + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc: " + acc);
                    System.out.println("----------------");
                    return acc;
                });
        System.out.println(reduce);
    }

   @Test
   public void test05() {
        LinkedList<Integer> newList = new LinkedList<>();

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(2, 3, 4));

       LinkedList<Integer> accResult_ = arrayList.stream()
               .reduce(newList,
                       (acc, item) -> {
                           acc.add(item);
                           System.out.println("item: " + item);
                           System.out.println("acc: " + acc);
                           System.out.printf("", "BiFunction");
                           return acc;
                       },
                       (acc, item) -> null);
       System.out.println("accResult_: " + accResult_);

   }

    /**
     * 测试删除文件
     */
   @Test
    public void test06() {
        File file = new File("C:\\Users\\maize\\IdeaProjects\\collect\\001d7d31a9461b837e5902.jpg");
       if (file.exists()) {
           file.delete();
       }
   }

    /**
     * 测试获取域名
     */
    @Test
    public void test07() {
        List<String> list = new ArrayList<>();
        list.add("http://www.hezegd.com/");
        list.add("https://www.hezegd.com/");
        list.add("http://www.hzmdw.cn/article/20141101/4448.html");
        list.add("http://www.hzmdw.cn/column/39/");
        list.add("https://www.hzmdw.cn/article/20141101/4448...html");
        list.add("https://www.hzmdw.cn/column/39/");

        Pattern pattern = Pattern.compile("(https?://)([\\w.]*)");

        for (String s : list) {
            /*Matcher matcher = pattern.matcher(s);
            System.out.println(s);
            if (matcher.find()) {
                System.out.println(matcher.groupCount());
                System.out.println("----------------------------------");

            }*/
            String replaceAll = s.replaceAll("[\\w.]*", "######");
            System.out.println(replaceAll);
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(matcher.group(2));
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    /**
     *
     */
    @Test
    public void test08() {
        List<String> list = new ArrayList<>();
        list.add("http://www.hezegd.com/");
        list.add("https://www.hezegd.com/");
        list.add("http://www.hzmdw.cn/article/20141101/4448.html");
        list.add("http://www.hzmdw.cn/column/39/");
        list.add("https://www.hzmdw.cn/article/20141101/4448...html");
        list.add("https://www.hzmdw.cn/column/39/");

        List<String> list1 = list.subList(0, 3);

        System.out.println(list1);
        System.out.println(list);
    }

    /**
     * 测试 String的replace方法是不是只删除第一个
     */
    @Test
    public void test09() {
        GetResource getResource = new GetResource();
        List<String> list = new ArrayList<>();
        list.add("/article/20141101/4448.html");
        list.add("article/20141sfd101/4448.html");
        list.add("http://www.123.com/article201411014448.html");

//        List<String> list1 = getResource.completionSrc(list, "http://www.123.com/");
//        list1.forEach(System.out::println);
    }

    /**
     * 测试匹配开头的 ./
     */
    @Test
    public void test10() {
        String str = "./jcsp/201909/W020190911487077890625.png";
        System.out.println(str.startsWith("./"));
        System.out.println(str.replaceFirst("./", ""));
    }



}
