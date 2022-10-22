package com.huckleberrycalfinn.htmlgeneratordecorator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class HtmlGeneratorApp {
	public static void main(String[] args){
		Div d = new Div();
		Div innerD = new Div();
		innerD.setStyle("width: 100%;");
		d.addChild(innerD);
		System.out.println(d.getDom());
		System.out.println(
				(
						new Dark(
						new Row(
								new Div()
						)
				)
				).getDom()
		);
	}
}



interface Dom {
	enum SingleOrDoubleTag {
	  SINGLE,
	  DOUBLE
	}

	SingleOrDoubleTag getSingleOrDoubleTag();
	String getDom();
	String getTag();
  	String getStyle();
	List<Dom> getChildren();
	void addChild(Dom child);
	void removeChild(Dom child);
}


abstract class Elt implements Dom {
  private String tag;
  private List<Dom> children;
  private SingleOrDoubleTag singleOrDoubleTag;
  private String style;

  public Elt(String tag, SingleOrDoubleTag singleOrDoubleTag) {
	this.tag = tag;
	this.singleOrDoubleTag = singleOrDoubleTag;
	this.children = new ArrayList<>();
  }

  public Elt(String tag) {
	this(tag, SingleOrDoubleTag.DOUBLE);
  }

  public String getTag(){
	return this.tag;
  }

  public String getStyle(){
	return this.style;
  }

  public void setStyle(String style){
	this.style = style;
  }

  public List<Dom> getChildren(){
	return this.children;
  }

  public void addChild(Dom child){
	this.children.add(child);
  }

  public void removeChild(Dom child){
	this.children.remove(child);
  }

  public SingleOrDoubleTag getSingleOrDoubleTag(){
	return this.singleOrDoubleTag;
  }

  public String getDom() {
	String styleString = "";
	if (this.getStyle() != null){
		styleString = " style=\"" + this.getStyle() + "\"";
	}
	if ( this.singleOrDoubleTag == SingleOrDoubleTag.SINGLE) {
	  return "<" + this.getTag() + styleString + "/>";
	} else {
	  String childrenDom = "";
	  for (Dom child: this.getChildren()) {
		childrenDom += child.getDom();
	  }
	  return "<" + this.getTag() + styleString + ">" + childrenDom + "</" + this.tag + ">";
	}
  }

}


class Div extends Elt {

  public Div(){
	super("div");
  }

}


abstract class StyleDecorator implements Dom {

  private Dom innerDom;

  public StyleDecorator(Dom innerDom) {
	this.innerDom = innerDom;
  }

  public abstract String getDecoratorStyle();

  public SingleOrDoubleTag getSingleOrDoubleTag() {
	return this.innerDom.getSingleOrDoubleTag();
  }

  public String getTag() {
	return this.innerDom.getTag();
  }

  public List<Dom> getChildren() {
	return this.innerDom.getChildren();
  }

  public void addChild(Dom child) {
	this.innerDom.addChild(child);
  }

  public void removeChild(Dom child) {
	this.innerDom.removeChild(child);
  }

  public String getStyle() {
	if (this.getDecoratorStyle() == null) {
	  return this.innerDom.getStyle();
	} else {
	  if (this.innerDom.getStyle() == null) {
		return this.getDecoratorStyle();
	  } else {
		return this.innerDom.getStyle() + this.getDecoratorStyle();
	  }
	}
  }


  public String getDom() {
	String styleString = "";
	if (this.getStyle() != null) {
	  styleString = " style=\"" + this.getStyle() + "\"";
	}
	if (this.getSingleOrDoubleTag() == SingleOrDoubleTag.SINGLE) {
	  return "<" + this.getTag() + styleString + "/>";
	} else {
	  String childrenDom = "";
	  for (Dom child: this.getChildren()) {
		childrenDom += child.getDom();
	  }
	  return "<" + this.getTag() + styleString + ">" + childrenDom + "</" + this.getTag() + ">";
	}
  }
}


class Row extends StyleDecorator {
  public Row(Dom dom){
	super(dom);
  }

  public String getDecoratorStyle(){
	return "text-align: center; width: 100%;";
  }
}

class Dark extends StyleDecorator {
  public Dark(Dom dom){
	super(dom);
  }

  public String getDecoratorStyle(){
	return "background-color: dark-grey;";
  }
}
