package org.CustomJsonParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException, TransformerException
    {
    	TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("C:\\Users\\Nishant\\git\\integration\\CustomJsonParser\\src\\main\\resources\\text2xml.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("C:\\Users\\Nishant\\git\\integration\\CustomJsonParser\\src\\main\\resources\\sample.txt"));
        transformer.transform(text, new StreamResult(new File("output.xml")));
    }
}
