package com.huckleberrycalfinn.proxypattern;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote {
	public String sayHello() throws RemoteException;
}

///Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=59089:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/calvinwitt/code/java-stuff/headfirstdesignpatterns-cw/build/classes/java/main com.huckleberrycalfinn.iterable.IterablePeopleApp
//		Person(name=jed, age=50)
//		Person(name=toby, age=40)
//		Person(name=jed, age=50)
//		Person(name=toby, age=40)
//
//		Process finished with exit code 0
