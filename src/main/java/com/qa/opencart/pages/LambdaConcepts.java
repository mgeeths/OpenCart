package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class LambdaConcepts {

    public  void sampleFunctionLambda(){
        Function<String, Integer>  func = (name) -> name.length();
        int len = func.apply("Hello World");
        System.out.println(len);
    }

    public  void sampleUnaryLambda(){
        UnaryOperator<Integer> func = (n) -> n*n;
        int nSquare = func.apply(6);
        System.out.println(nSquare);
    }

    public  void sampleBinaryLambda(){
        BinaryOperator<Integer> func = (n, n1) -> n + n1;
        int product = func.apply(6,2);
        System.out.println(product);
    }

    public  void samplePredicateLambda(){
        Predicate<Integer> func = (n) -> n >5;
        List<Integer> numList = Arrays.asList(2,4,1,6,8,7,9,6,7,9);
        List<Integer> filteredList = numList.stream().filter(func).collect(Collectors.toList());
        System.out.println(filteredList);
    }

    public  void sampleConsumerLambda(){
        Consumer<String> func = (names) -> System.out.println(names) ;
        func.accept("Print this text");
        List<Integer> numList = Arrays.asList(2,4,1,6,8,7,9,6,7,9);
        numList.forEach(n -> System.out.println(n));
    }

    public  void sampleSupplierLambda(){
        Supplier<Date> func = ()-> new Date();
        Date systemDate = func.get();
        System.out.println(systemDate.toString());
    }

}
