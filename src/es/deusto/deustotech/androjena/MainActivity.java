package es.deusto.deustotech.androjena;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Model model = ModelFactory.createDefaultModel();
		
		String ns = "http://example.com/test";
		
		Resource r = model.createResource(ns + "r");
		Property p = model.createProperty(ns + "p");
		
		r.addProperty(p, "hello world!", XSDDatatype.XSDstring);
		
		model.write(System.out, "Turtle");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
