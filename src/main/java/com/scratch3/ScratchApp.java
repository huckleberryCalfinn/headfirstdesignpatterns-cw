package com.scratch3;
import static com.scratch3.utils.IOHelpers.printKV;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Properties;

public class ScratchApp {
    public static void main(String[] args){
        Properties sysProps = System.getProperties();
        System.out.println(String.format("sysProps: \n\t%s", sysProps));
        Map<String,String> env = System.getenv();
        System.out.println(String.format("env: \n\t:%s", env));
        Box<SystemPropertiesAndEnv> systemBox = ImmutableBox.<SystemPropertiesAndEnv>builder()
            .obj(
                ImmutableSystemPropertiesAndEnv.builder()
                    .props(sysProps)
                    .env(env)
                    .build()
            )
            .build();
        var boxCommand = new Command(new BoxSystemPropertiesGetter());
        var sysBoxProps = boxCommand.execute(systemBox);
        System.out.println(sysBoxProps);
        MyFunction<Box<SystemPropertiesAndEnv>, String> sysPathGetter = new MyFunction<>(){
            private String sysPath;
            public String apply(Box<SystemPropertiesAndEnv> systemBox){
                this.accept(systemBox);
                return this.sysPath;
            }
            private void accept(Box<SystemPropertiesAndEnv> systemBox){
                this.sysPath = systemBox.getObj().getEnv().getOrDefault("PATH", "/opt/homebrew/bin:/usr/local/bin");
            }
        };
        printKV("getSysPathGetter.apply(systemBox))",sysPathGetter.apply(systemBox));
        MyFunction<Box<SystemPropertiesAndEnv>, String> lambdaGetSysPath = systemBoxObj -> systemBoxObj.getObj().getEnv().getOrDefault("PATH", "/opt/homebrew/bin:/usr/local/bin");
        String sysPath = lambdaGetSysPath.apply(systemBox);
        System.out.println(sysPath);
        FunctionComposition<Box<
            SystemPropertiesAndEnv>,
            SystemPropertiesAndEnv,
            SystemPropertiesAndEnv,
            String> compFunc =  new FunctionComposition<>(
                sysBox -> sysBox.getObj(),
                sysPropsAndEnv -> sysPropsAndEnv.getEnv().getOrDefault("PATH", "/opt/homebrew/bin:/usr/local/bin")
        );
        String sysPathFromComposed = compFunc.apply(systemBox);
        printKV("sysPathFromComposed", sysPathFromComposed);
    }
}

class FunctionComposition<TFirst, RFirst extends TSecond, TSecond,  RSecond> implements FunctionCompositionInterface<TFirst, RFirst, TSecond, RSecond> {
    private MyFunction<TFirst, RFirst> firstFunc;
    private MyFunction<TSecond, RSecond> secondFunc;
    private TFirst firstFuncInput;
    private RFirst firstFuncOutput;
    private TSecond secondFuncInput;
    private RSecond secondFundOutput;

    public FunctionComposition(MyFunction<TFirst, RFirst> firstFunc, MyFunction<TSecond, RSecond> secondFunc){
        this.firstFunc = firstFunc;
        this.secondFunc = secondFunc;
    }

    @Override
    public MyFunction<TFirst, RFirst> getFirstFunc() {
        return this.firstFunc;
    }

    @Override
    public MyFunction<TSecond, RSecond> getSecondFunc() {
        return this.secondFunc;
    }
    @Override
    public RSecond apply(TFirst obj){
        this.accept(obj);
        return this.secondFundOutput;
    }
    private void accept(TFirst firstFuncInput){
        this.firstFuncInput = firstFuncInput;
        this.firstFuncOutput = getFirstFunc().apply(firstFuncInput);
        this.secondFuncInput = this.firstFuncOutput;
        this.secondFundOutput = getSecondFunc().apply(this.secondFuncInput);
    }
}

interface FunctionCompositionInterface<TFirst, RFirst extends TSecond, TSecond, RSecond>{
    MyFunction<TFirst, RFirst> getFirstFunc();
    MyFunction<TSecond, RSecond> getSecondFunc();
    RSecond apply(TFirst obj);

}

@Value.Immutable
interface SystemPropertiesAndEnv {
    Properties getProps();
    Map<String, String> getEnv();
}


@Value.Immutable
abstract class Box<T> {
  public abstract T getObj();
}

class Command<T, R> implements CommandInterface<T, R> {
    private MyFunction<T, R> func;
    public Command(MyFunction<T, R> func){
        this.func = func;
    }
    public R execute(T obj) {
        return this.func.apply(obj);
    }
}

interface CommandInterface<T, R> {
    R execute(T obj);
}

class BoxSystemPropertiesGetter implements MyFunction<Box<SystemPropertiesAndEnv>, Properties> {
    private Properties sysProps;
    public Properties apply(Box<SystemPropertiesAndEnv> obj) {
         this.accept(obj);
         return this.sysProps;
    }
    private void accept(Box<SystemPropertiesAndEnv> obj){
        this.sysProps = obj.getObj().getProps();
    }
}


interface MyFunction<T, R> {
    R apply(T obj);
}
