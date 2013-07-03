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
		
		generateModel();
		loadRules();
	}

	private void generateModel() {
		final String userUri 	= "http://adaptation/user";
		final String contextUri = "http://adaptation/context";
		final String deviceUri 	= "http://adaptation/device";
		final String finalUIUri = "http://adaptation/finalui";
		
		Model model = ModelFactory.createDefaultModel();
		
		//User
		Resource testUser = model.createResource(userUri);
		
		Property viewSize = model.createProperty("VIEW_SIZE");
		Property output = model.createProperty("OUTPUT");
		Property brightness = model.createProperty("BRIGHTNESS");
		
		testUser.addProperty(viewSize, "DEFAULT");
		testUser.addProperty(output, "DEFAULT");
		testUser.addProperty(brightness, "DEFAULT");
		
		//Context
		Resource testContext = model.createResource(contextUri);
		
		Property temperature = model.createProperty("TEMPERATURE");
		Property illuminance = model.createProperty("ILLUMINANCE");
		
		testContext.addProperty(temperature, "NORMAL");
		testContext.addProperty(illuminance, "SUNLIGHT");
		
		//Device
		Resource testDevice = model.createResource(deviceUri);
		
		testDevice.addProperty(viewSize, "DEFAULT");
		testDevice.addProperty(output, "DEFAULT");
		testDevice.addProperty(brightness, "DEFAULT");
		
		//FinalConfiguration
		Resource testFinalUI = model.createResource(finalUIUri);
		
		Property viewColor = model.createProperty("VIEW_COLOR");
		
		testFinalUI.addProperty(viewSize, "DEFAULT");
		testFinalUI.addProperty(viewColor, "DEFAULT");
	}

	private void loadRules() {
		String rules = "[adaptViewSize: " +
				"(?a http://www.w3.org/1999/02/22-rdf-syntax-ns#type ?x) " +
				"(?x http://www.w3.org/1999/02/22-rdf-syntax-ns#type http://www.w3.org/2002/07/owl#Class) " +
				"(?t http://www.w3.org/1999/02/22-rdf-syntax-ns#type http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneTag) " + 
				"notEqual(?a, ?t) notEqual(?a, http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneInstance) " +
				" -> " +
				"(http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneInstance http://www.w3.org/1999/02/22-rdf-syntax-ns#type http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneCall) " +
				"(http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneInstance http://www.w3.org/1999/02/22-rdf-syntax-ns#type http://www.lagunfc.com/ontologies/lagunfc.owl#Action) " +
				"(http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneInstance http://www.lagunfc.com/ontologies/lagunfc.owl#hasAttribute ?a) " +
				"(http://www.lagunfc.com/ontologies/lagunfc.owl#TelephoneInstance http://www.lagunfc.com/ontologies/lagunfc.owl#hasTag ?t)]";	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

