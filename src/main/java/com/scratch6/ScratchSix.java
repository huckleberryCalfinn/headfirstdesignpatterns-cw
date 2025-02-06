package com.scratch6;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

enum Status {
    PENDING(null),
    COMPLETE("COMPLETE"),
    OPEN_ITEMS("OPEN ITEMS"),
    ;

    private final String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public List<String> getStringList(){
        return List.of("a", "b", "c");
    }

    public static <L, R> Pair<L, R> pairOf(L leftVal, R rightVal){
        return Pair.of(leftVal, rightVal);
    }
}


public class ScratchSix {

    public static void main(String[] args) throws NoSuchMethodException {
        Set<Integer> s = Set.of(1,2,3);
        Collection<String> strClx = Set.of("a", "b");
        Pair<String, Integer> pair = Status.pairOf("calvin", 32);
        Pair<String, Integer> strIntPair = Status.pairOf("jed", 52);
        Input<Pair<String, Integer>> ipt = Input.builder()
            .name("strIntPair")
            .methodName("getRight")
            .element(strIntPair)
            .build();
        Method[] methods = Status.class.getDeclaredMethods();
        System.out.println(methods);
        Arrays.stream(Status.class.getDeclaredMethods()).map(m -> Pair.of(m, m.getGenericReturnType())).map(p -> {try {return Pair.<Method, ParameterizedType>of((Method)p.getLeft(), (ParameterizedType)p.getRight());} catch (Exception exception){return null;}}).filter(Objects::nonNull).flatMap(p -> Stream.of(p.getRight().getActualTypeArguments())).toList();
//
//        Method method = ipt.getClass().getDeclaredMethod("getElement");
//
//        ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
//        Type[] actualTypes = returnType.getActualTypeArguments();
//        System.out.println(actualTypes);
//        Result<Pair<String, Integer>> res = Result.builder()
//            .originalInput(ipt)
//            .genericReturnType(returnType)
//            .returnTypeEvaluatedParameterTypeArgs(actualTypes)
//            .build();
//        System.out.println(res);
//
//        var results = Stream.of(
//            Input.builder()
//                .element(s)
//                .name("s")
//                .methodName("iterator")
//                .build(),
//            Input.builder()
//                    .name("strClx")
//                    .element(strClx)
//                    .methodName("iterator")
//                    .build(),
//            Input.builder()
//                .element(pair)
//                .name("pair")
//                .methodName("getRight")
//                .build(),
//            Input.builder()
//                .element(strIntPair)
//                .name("strIntPair")
//                .methodName("getRight")
//                .build()
//        ).map(ipt -> {
//            try {
//                Method iptMethod = ipt.getElement().getClass().getDeclaredMethod(ipt.getMethodName());
//                ParameterizedType genericReturnType = (ParameterizedType) iptMethod.getGenericReturnType();
//                var actualReturnTypeArgs = genericReturnType.getActualTypeArguments();
//                return Result.builder()
//                        .originalInput(Input.Builder.from(ipt).build())
//                        .genericReturnType(genericReturnType)
//                        .returnTypeEvaluatedParameterTypeArgs(actualReturnTypeArgs)
//                        .build();
//            } catch (NoSuchMethodException e) {
//                System.out.println(ipt);
//                throw new RuntimeException(e);
//            }
//        }).toList();
//        System.out.println(results);
    }
}