//package com.scratch4;
//
//import java.util.Optional;
//import java.util.function.Function;
//
//
//public class BuilderPatternApp {
//    public static void main(String[] args){
//        var jed = Person.builder()
//            .name("jed")
//            .age(52)
//            .job("potus")
//            .build();
//
//        System.out.println(jed);
//    }
//}
//
//interface BaseBuilderInterface {
//
//    Optional<String> getName();
//    Optional<Integer> getAge();
//    Optional<String> getJob();
//}
//
//
//interface ImmutableBuilderInterface {
//
//    String getName();
//
//    Integer getAge();
//
//    String getJob();
//}
//
//
//
//interface MutableBuilderInterface extends BaseBuilderInterface {
//    MutableBuilderInterface name(String name);
//    MutableBuilderInterface age(Integer age);
//    MutableBuilderInterface job(String job);
//    ImmutableBuilderInterface build();
//}
//
//
//
//class Person implements ImmutableBuilderInterface {
//    private final String name;
//    private final Integer age;
//    private final String job;
//
//    public Person(String name, Integer age, String job) {
//        this.name = name;
//        this.age = age;
//        this.job = job;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public Integer getAge() {
//        return this.age;
//    }
//
//    public String getJob() {
//        return this.job;
//    }
//
//    public static MutableBuilderInterface builder() {
//        return new PersonBuilder();
//    }
//
//    private static class PersonBuilder implements MutableBuilderInterface {
//        private Optional<String> name;
//        private Optional<Integer> age;
//        private Optional<String> job;
//
//        public PersonBuilder() {
//            this.name = null;
//            this.age = null;
//            this.job = null;
//        }
//
//        public MutableBuilderInterface name(Optional<String> name) {
//            this.name = name;
//            return this;
//        }
//
//        public MutableBuilderInterface age(Optional<Integer> age) {
//            this.age = age;
//            return this;
//        }
//
//        public MutableBuilderInterface job(Optional<String> job) {
//            this.job = job;
//            return this;
//        }
//
//        public Optional<String> getName() {
//            return this.name;
//        }
//
//        public Optional<Integer> getAge() {
//            return this.age;
//        }
//
//        public Optional<String> getJob() {
//            return this.job;
//        }
//
//        private abstract class ConvertOrElse<T, R> {
//            private final Function<T, R> converter;
//            private final Function<T, T> orElse;
//
//            public ConvertOrElse(Function<T, R> converter, Function<T, T> orElse) {
//                this.converter = converter;
//                this.orElse = orElse;
//            }
//
//            public R convert(T obj) {
//                try {
//                    return this.converter.apply(obj);
//                } catch (Exception exc) {
//                    return this.orElse.apply(exc);
//                }
//            }
//        }
//    }
//}
//
////            RuntimeException<Exception, Exception> orElse = new Function<Exception, Exception>(){
////                public Exception apply(Exception apply){
////
////                    return new RuntimeException("failed conversion", exc);
////                }
////            }
////            public final default R tryConvert(T from){
////                try {
////                    (Person p) -> {
////                        p.getName() orElseThrow(conversionException),
////                            p.getAge().orElseThrow(conversionException),
////                            p.getJob().orElseThrow(conversionException)
////                    } catch (Exception as exc){
////
////
////                    }
////                    new Person(
////                        builder.getName()
////                    )
////                    tryConvert(this.converter.apply(from
////                }
////            }
////        }
////    }
////}
////            public default R void tryConvert(T from, R to){
////
////            }
////        }
////
////            public transform()
////
////                    return R convertedObj = converter.apply(T from, R to);
////                }
////            }
////
////        }
////
////        public static ImmutableBuilderInterface fromBuilder (MutableBuilderInterface builder){
////                Exception readyForAction = new IllegalArgumentException(
////                    String.format("excption encounted while trying to convert from % to %s", MutableBuilderInterface.class.getName(), ImmutableBuilderInterface.class.getName()), null);
////                return new ImmutableBuilderInterface() {
////                }
////
////                return new Person(
////                    Person(
////                        builder.getName().map(Optional::orElseThrow);
////        } catch (Exception exc){
////            throw new
////        }
////
////
////
////        public ImmutableBuilderInterface build() throws RuntimeException {
////            Optional.ofNullable(this.getName()).orElseThrow()
////            return Stream.of(
////                this.getName(),
////                this.getAge(),
////                this.getJob()
////            ).map(prop -> prop .orElseThrow(
////                () -> new IllegalArgumentException(
////                    String.format("null value provided for required parameter.")
////                )
////            ).(Collectors.toList());
////        }
////    }
////}
