//package com.neusoft.OpXml;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import org.dom4j.*;
//import org.dom4j.io.SAXReader;
//
//public class dom4jXml {
//	public static void main(String[] args) {
//		//dom4jXml ptr = new dom4jXml();
//		//ptr.createXML();
//
//		SAXReader saxReader = new SAXReader();
//		try {
//			Document document = saxReader.read(new File("xmlDoc.xml"));
//			Element ele = document.getRootElement();
//			parserNode(ele);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public void createXML()
//	{
//		Document document = DocumentHelper.createDocument();
//        Element root = document.addElement("root");
//
//        Element author1 = root.addElement("author")
//            .addAttribute("name", "James")
//            .addAttribute("location", "UK")
//            .addText("James Strachan");
//
//        Element author2 = root.addElement("author")
//            .addAttribute("name", "Bob")
//            .addAttribute("location", "US")
//            .addText("Bob McWhirter");
//
//        System.out.println(document.asXML());
//
//        try {
//			FileWriter out = new FileWriter("foo.xml");
//			document.write(out);
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void parserNode(Element ele){
//
//		System.out.println(ele.getName()+":"+ele.getText().trim());
//		//从Users根节点开始遍历，像【属性=值】的形式存为一个Attribute对象存储在List集合中
//		List<Attribute> attrList = ele.attributes();
//		for(Attribute attr : attrList){
//			//每循环一次，解析此节点的一个【属性=值】，没有则输出空
//			String name = attr.getName();
//			String value = attr.getValue();
//			System.out.println(name+"="+value);
//		}
//
//		List<Element> eleList = ele.elements();
//		//递归遍历父节点下的所有子节点
//		for(Element e : eleList){
//			parserNode(e);
//		}
//	}
//
//}
