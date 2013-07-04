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
		Resource user = model.createResource(userUri);
		
		Property output = model.createProperty("OUTPUT");
		Property viewSize = model.createProperty("VIEW_SIZE");
		Property brightness = model.createProperty("BRIGHTNESS");
		
		user.addProperty(viewSize, "DEFAULT");
		user.addProperty(output, "DEFAULT");
		user.addProperty(brightness, "DEFAULT");
		
		//Context
		Resource context = model.createResource(contextUri);
		
		Property temperature = model.createProperty("TEMPERATURE");
		Property illuminance = model.createProperty("ILLUMINANCE");
		
		context.addProperty(temperature, "NORMAL");
		context.addProperty(illuminance, "SUNLIGHT");
		
		//Device
		Resource device = model.createResource(deviceUri);
		
		device.addProperty(viewSize, "DEFAULT");
		device.addProperty(output, "DEFAULT");
		device.addProperty(brightness, "DEFAULT");
		
		//FinalConfiguration
		Resource finalUIConfiguration = model.createResource(finalUIUri);
		
		Property viewColor = model.createProperty("VIEW_COLOR");
		
		finalUIConfiguration.addProperty(viewSize, "DEFAULT");
		finalUIConfiguration.addProperty(viewColor, "DEFAULT");
	}

	private void loadRules() {
		/* if (user.output.equals("DEFAULT") || user.output.equals("HAPTIC")){
		 * 		if (device.output.equals("DEFAULT") || device.output.equals("HAPTIC")){
		 * 			if (user.viewSize.equals(device.viewSize)){
		 * 				if (user.brightness.equals(device.brightness){
		 * 					if (context.illuminance.notEquals(user.brightness){
		 * 						finalUIConfiguration.setProperty(viewSize, BIG);
		 * 						finalUIConfiguration.setProperty(viewColor, RED);
		 * 					}
		 * 				}
		 * 			}
		 * 		}
		 * }
		 * 
		 */
		
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

