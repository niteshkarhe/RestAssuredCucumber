package com.restassured.pojoutilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.GsonAnnotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class ConvertReqJsonToPojo 
{
	public static void convertRequestJSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{  
        JCodeModel codeModel = new JCodeModel();  
        URL source = inputJson;  
        GenerationConfig config = new DefaultGenerationConfig() {  
            @Override  
            public boolean isGenerateBuilders() { // set config option by overriding method  
            return true;  
            }
            
            @Override
            public boolean isIncludeToString()
            {
            	return false;
            }
            
            @Override
            public boolean isIncludeHashcodeAndEquals()
            {
            	return false;
            }
            public SourceType getSourceType(){  
        return SourceType.JSON;  
      }  
            };  
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new GsonAnnotator(config), new SchemaStore()), new SchemaGenerator());  
        mapper.generate(codeModel, className, packageName, source);  
        codeModel.build(outputPojoDirectory);  
   }
}
