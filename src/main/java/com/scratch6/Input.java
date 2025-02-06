package com.scratch6;


public class Input<T> implements InputInterface<T> {
    private final String name;
    private final T element;
    private final String methodName;

    private Input(String name, T element, String methodName) {
        this.element = element;
        this.name = name;
        this.methodName = methodName;
    }

    public String getName() {
        return name;
    }

    public T getElement() {
        return element;
    }

    public String getMethodName() {
        return methodName;
    }

    public static <TBuilder> Builder<TBuilder> builder(){
        return new Builder<>();
    }

    public static final class Builder<E> {
        private String name;
        private E element;
        private String methodName;

        private Builder(
        ){}

        private Builder(String name, E element, String methodName){
            this.name = name;
            this.element = element;
            this.methodName = methodName;
        }

        public String getName() {
            return name;
        }

        public Builder<E> name(String name) {
            this.name = name;
            return this;
        }

        public String getMethodName() {
            return methodName;
        }

        public Builder<E> methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public static <TNew> Builder<TNew> from(Input<TNew> instance){
            return new Builder<>(
                instance.getName(),
                instance.getElement(),
                instance.getMethodName()
            );
        }

        public <E2> Builder<E2> element(E2 element){
            return new Builder<>(
                this.getName(),
                element,
                this.getMethodName()
            );
        }

        public E getElement(){
            return this.element;
        }

        public Input<E> build(){
            return new Input<>(getName(), getElement(), getMethodName());
        }
    }
}
