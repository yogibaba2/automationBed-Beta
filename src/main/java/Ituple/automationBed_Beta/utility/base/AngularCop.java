package Ituple.automationBed_Beta.utility.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;



public class AngularCop {

	public JavascriptExecutor jsDriver;
	
	public String scope;
	public String scopeVariable;
	
	public AngularCop(WebDriver driver) {
		this.jsDriver=(JavascriptExecutor)driver;
	}
	
	public AngularCop getScopeForElement(String element){
		this.scope=element;
		return this;
	}
	
	public String getVariableFromScope(String VariableName){
		Object obj= jsDriver.executeScript(
				"var elementScope=window.angular.element('"+scope+"').scope();"+
				"var scopeVariable = elementScope."+VariableName+";"+
				"return scopeVariable;"
				);
		
		return (obj==null)?"":obj.toString();
	}
	
	
}
