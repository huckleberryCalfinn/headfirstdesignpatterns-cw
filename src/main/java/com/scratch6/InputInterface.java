package com.scratch6;



public interface InputInterface<T> {
    String getName();
    T getElement();
    String getMethodName();

//    interface Builder<E> {
//        Builder<E> name(String name);
//        <E2> Builder<E2> element(E2 element);
//        Builder<E> methodName(String methodName);
//        InputInterface<E> build();
//    }
//
//    static InputInterface.Builder<T> builder();
}
