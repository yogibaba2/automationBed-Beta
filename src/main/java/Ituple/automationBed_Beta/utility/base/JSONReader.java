package Ituple.automationBed_Beta.utility.base;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

 
 
public class JSONReader {
	
	
/**
 * JSONReader is customized class for Reading JSON files 
 * 
 * @author YogendraPorwal
 * */	
	
	
	private JSONObject JsonObject;
	
	/**
	 * JSONReader(JSONObject obj) is used  when there is nested object in Json file 
	 * 
	 * @param JSONObject
	 * */
	public JSONReader(JSONObject JsonObject) {
		this.JsonObject= JsonObject;
	}
	
	/**
	 * JSONReader(String a) initialize JsonObject to read
	 * 
	 *  @param String
	 * */
	public JSONReader(String JsonPath) {
		JSONParser parser= new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(JsonPath));
			JsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * to get generic object from json
	 * 
	 *  @param String
	 * */
	private Object get(String attribute){
		Object obj=null;
		try {
			obj=JsonObject.get(attribute);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
	
	/**
	 * return String from JSON
	 * 
	 *  @param String
	 * */
	public String getString(String attribute){
		String value=null;
		try {
			value=(String)JsonObject.get(attribute);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return value;
	}
	
	
	/**
	 * return Array from JSON
	 * 
	 *  @param String
	 * */
	public Object[] getArray(String attribute){
		Object tempArray[]; 
		try {
			JSONArray Jarray= (JSONArray)get(attribute);
			tempArray=(Object[])Jarray.toArray();
		} catch (Exception e) {
			tempArray=null;
			e.printStackTrace();
		}
		
		return tempArray;
	}
	
	
	/**
	 * return JSONReader obj for nested jsonObject from JSON
	 * 
	 *  @param String
	 * */
	public JSONReader getJsonObject(String attribute){
		 JSONReader jsonReader;
		try {
			jsonReader= new JSONReader((JSONObject)JsonObject.get(attribute));
		} catch (Exception e) {
			jsonReader=null;
			e.printStackTrace();
		}
		
		return jsonReader;
	}
	
	
	
}
