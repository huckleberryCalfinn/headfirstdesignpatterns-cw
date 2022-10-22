package com.huckleberrycalfinn.proxypattern;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
  private static final long serialVersionUID = 1L;

  public MyRemoteImpl() throws RemoteException {}

  public String sayHello() {
	return "Server says, 'Hey'";
  }

  public static void main(String[] args) {
	try {
	  MyRemote service = new MyRemoteImpl();
	  System.setProperty("java.rmi.server.hostname", "127.0.0.1");
	  Naming.rebind("RemoteHelloService", service);
	} catch (Exception ex) {
	  ex.printStackTrace();
	}
  }
}
