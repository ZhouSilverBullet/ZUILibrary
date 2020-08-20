package com.uilib;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/27 11:11
 * @Dec 略
 */
class A {
    public void a() {
        System.out.println("AA");
    }

    public String a(int a){
        return null;
    }
}
public class Test {
    public static void main(String[] args) {
//        int i;
//        System.out.println(i);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        A b = new A() {
            @Override
            public void a() {
                System.out.println("BB");
            }
        };
        b.a();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new DefaultHandler(){

            });
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
