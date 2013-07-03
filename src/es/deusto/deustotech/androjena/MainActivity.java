package es.deusto.deustotech.androjena;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

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
		
		
		final String userUri = "http://adaptation/user";
		
		Resource testUser = model.createResource(userUri);
		Property viewSize = model.createProperty("VIEW_SIZE");
		Property output = model.createProperty("OUTPUT");
		Property brightness = model.createProperty("BRIGHTNESS");
		
		testUser.addProperty(viewSize, "DEFAULT");
		testUser.addProperty(output, "DEFAULT");
		testUser.addProperty(brightness, "DEFAULT");
		
		
		final String contextUri = "http://adaptation/context";
		
		Resource testContext = model.createResource(contextUri);
		Property temperature = model.createProperty("TEMPERATURE");
		Property illuminance = model.createProperty("ILLUMINANCE");
		
		testContext.addProperty(temperature, "NORMAL");
		testContext.addProperty(illuminance, "SUNLIGHT");
		
		
		final String deviceUri = "http://adaptation/device";
		
		Resource testDevice = model.createResource(deviceUri);
		
		testDevice.addProperty(viewSize, "DEFAULT");
		testDevice.addProperty(output, "DEFAULT");
		testDevice.addProperty(brightness, "DEFAULT");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

