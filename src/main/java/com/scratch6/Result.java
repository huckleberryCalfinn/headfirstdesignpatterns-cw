package com.scratch6;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Result<T> {
    private final Input<T> originalInput;
    private final ParameterizedType genericReturnType;
    private final Type[] returnTypeEvaluatedParameterTypeArgs;

    private Result(Input<T> originalInput, ParameterizedType genericReturnType, Type[] returnTypeEvaluatedParameterTypeArgs){
        this.originalInput = originalInput;
        this.genericReturnType = genericReturnType;
        this.returnTypeEvaluatedParameterTypeArgs = returnTypeEvaluatedParameterTypeArgs;
    }

    public static <T> Builder<T> builder(){
        return new Builder<>();
    }

    public static class Builder<E> {
        private Input<E> originalInput;
        private ParameterizedType genericReturnType;
        private Type[] returnTypeEvaluatedParameterTypeArgs;

        private Builder(){}

        private Builder(
            Input<E> originalInput,
            ParameterizedType genericReturnType,
            Type[] returnTypeEvaluatedParameterTypeArgs
        ){
            this.originalInput = originalInput;
            this.genericReturnType = genericReturnType;
            this.returnTypeEvaluatedParameterTypeArgs = returnTypeEvaluatedParameterTypeArgs;
        }

        public <E2> Builder<E2> originalInput(Input<E2> originalInput) {
            return new Builder<>(
                originalInput,
                this.genericReturnType,
                this.returnTypeEvaluatedParameterTypeArgs
            );
        }

        public Builder<E> genericReturnType(ParameterizedType genericReturnType) {
            this.genericReturnType = genericReturnType;
            return this;
        }

        public Builder<E> returnTypeEvaluatedParameterTypeArgs(Type[] returnTypeEvaluatedParameterTypeArgs) {
            this.returnTypeEvaluatedParameterTypeArgs = returnTypeEvaluatedParameterTypeArgs;
            return this;
        }

        public Input<E> getOriginalInput(){
            return this.originalInput;
        }

        public ParameterizedType getGenericReturnType() {
            return this.genericReturnType;
        }
        public Type[] getReturnTypeEvaluatedParameterTypeArgs() {
            return this.returnTypeEvaluatedParameterTypeArgs;
        }
        
        public Result<E> build() {
            return new Result<>(getOriginalInput(), getGenericReturnType(), getReturnTypeEvaluatedParameterTypeArgs());
        }
    }

    public Input<T> getOriginalInput(){
        return this.originalInput;
    }

    public ParameterizedType getGenericReturnType() {
        return genericReturnType;
    }

    public Type[] getReturnTypeEvaluatedParameterTypeArgs() {
        return returnTypeEvaluatedParameterTypeArgs;
    }


}
