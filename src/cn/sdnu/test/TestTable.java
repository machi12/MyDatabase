package cn.sdnu.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestTable {

    public static void main(String[] args) {

        List<String> headerList = new ArrayList<>();
        headerList.add("Name");

        List<List<String>> rowsList = new ArrayList<>();
        ArrayList<String> Machi = new ArrayList<>();
        Machi.add("Machi");
        ArrayList<String> Guangtong = new ArrayList<>();
        Guangtong.add("Guangtong Hello world");

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Hellworlddd");
        rowsList.add(Machi);
        rowsList.add(Guangtong);
        rowsList.add(strings);
//        System.out.println("Hello world");
//        int i = 0;
//        System.out.println("i = " + i);
        System.out.println(TableGenerator.generateTable(headerList, rowsList));

    }

    @Test
    public void testHelloworld() {
        System.out.println("Helloworld");
    }

    @Test
    public void testOne(){
        String one = "abcdefg";
        String two = one.substring(0, one.length());
        System.out.println(two);
    }
}
