package es.deusto.deustotech.androjena;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class MainActivity extends Activity {

	public static final String NS = "http://www.deustotech.es/prueba.owl#";
	public OntModel ontModel = null;
	public OntClass ontClass = null;
	
	Reasoner reasoner;
	InfModel infModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		generateModel();
		
		reasoner = new GenericRuleReasoner(Rule.parseRules(loadRules()));
		
//		final String triples = "<http://www.deustotech.es/prueba.owl#edu> " +
//				"<http://www.w3.org/1999/02/22-rdf-syntax-ns#type> " +
//				"<http://www.deustotech.es/prueba.owl#User> . ";
		executeRules(generateModel());
		
		Log.d("FIN", "FIN");
	}

	private Model generateModel() {
		
//		Model data = ModelFactory.createDefaultModel();
//		String triples = "<http://www.deustotech.es/prueba.owl#edu> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.deustotech.es/prueba.owl#User> . " +
//						"<http://www.deustotech.es/prueba.owl#edu> <http://www.deustotech.es/prueba.owl#view_size> \"default\" . " +
//						"";
//		try {
//			data.read(new ByteArrayInputStream(triples.getBytes("UTF-8")), null, "N-TRIPLES");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		return data;
		
		this.ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		this.ontModel.setNsPrefix("prueba", NS);
		
		this.ontClass = ontModel.createClass(NS +"User");
		
		addInstanceWithJena("edu");
		
		this.ontModel.write(System.out);
		
		return this.ontModel;
	}
	
	public Individual addInstanceWithJena(String id) {
		Individual individual = this.ontClass.createIndividual(NS + id);

		Property viewSize = this.ontModel.getProperty(NS + "view_size");
		Literal literal = this.ontModel.createTypedLiteral("default");
		individual.setPropertyValue(viewSize, literal);

		Property output = this.ontModel.getProperty(NS + "output");
		literal = this.ontModel.createTypedLiteral("default");
		individual.setPropertyValue(output, literal);

		Property brightness = this.ontModel.getProperty(NS + "brightness");
		literal = this.ontModel.createTypedLiteral("default");
		individual.setPropertyValue(brightness, literal);

		return individual;
	}

	private String loadRules() {
		
		System.out.println("loadRules()");
		
		return "[adaptViewSize: " +
				"print(\"0\") " +  
				"(?u http://www.w3.org/1999/02/22-rdf-syntax-ns#type http://www.deustotech.es/prueba.owl#User) " +
				"(?u http://www.deustotech.es/prueba.owl#view_size ?vs) " +
				"equal(?vs, \"default\") " +
				
				" -> " +  
				
				"(?u http://edu.ontology.es#mipropiedadfalsa \"se ha ejecutado\")] ";
				//+ "print(?u, \"OK\")]";
	}
	
	public void executeRules(Model dataModel) {
			infModel = ModelFactory.createInfModel(reasoner, dataModel);
			infModel.prepare();
			
			for (Statement st : infModel.listStatements().toList())
			{
				Log.d("InfModel", st.toString());
			}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

