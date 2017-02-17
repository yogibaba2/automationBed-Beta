package Ituple.automationBed_Beta.utility.ngwebdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;


public class ByAngularPartialButtonText extends ByAngular.BaseBy {

    public ByAngularPartialButtonText(String rootSelector, String partialButtonText) {
        super(rootSelector);
        this.searchText = partialButtonText;
    }

    private String searchText;

    protected Object getObject(SearchContext context, JavascriptExecutor javascriptExecutor) {
        return javascriptExecutor.executeScript(
                "var using = arguments[0] || document;\n" +
                        "var searchText = '" + searchText + "';\n" +
                        "\n" +
                        ByAngular.functions.get("findByPartialButtonText")
                , context);
    }

    @Override
    public String toString() {
        return "searchText(" + searchText + ')';
    }
}
