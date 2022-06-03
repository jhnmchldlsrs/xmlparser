package io.quadcom.xmlparser;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		TextView textView = findViewById(R.id.log);
		
		try {
			
			AssetManager assetManager = getAssets();
			InputStream inputStream = assetManager.open("xmldoc.xml");
		
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
			Document document = dbuilder.parse(inputStream);
			document.getDocumentElement().normalize();
			
			textView.setText("ROOT ELEMENT : " + document.getDocumentElement().getNodeName() + "\n");
			
			NodeList node = document.getElementsByTagName("tag");
			
			for (int temp = 0; temp < node.getLength(); temp++) {
			
				Node nNode = node.item(temp);
				textView.setText(textView.getText() + "\n" + "CURRENT ELEMENT : " + document.getDocumentElement().getNodeName());
				
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					textView.setText(textView.getText() + "\n" + "ATTRIBUTE1 : " + eElement.getAttribute("attribute1") + "\nATTRIBUTE2 : " + eElement.getAttribute("attribute2") + "\n");
				}
			}
			
		} catch (ParserConfigurationException e) {
			
			Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			
		} catch (IOException e) {
			
			Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			
		} catch (SAXException e) {
			
			Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			
		}
    }
	
}
