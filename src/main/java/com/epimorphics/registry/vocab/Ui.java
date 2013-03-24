/* CVS $Id: $ */
package com.epimorphics.registry.vocab; 
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.ontology.*;
 
/**
 * Vocabulary definitions from file:/home/der/projects/java-workspace/ukl/ukl-registry-poc/src/main/vocabs/ui.ttl 
 * @author Auto-generated by schemagen on 24 Mar 2013 21:16 
 */
public class Ui {
    /** <p>The ontology model that holds the vocabulary terms</p> */
    private static OntModel m_model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://purl.org/linked-data/registry-ui#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>The ontology's owl:versionInfo as a string</p> */
    public static final String VERSION_INFO = "0.1";
    
}
