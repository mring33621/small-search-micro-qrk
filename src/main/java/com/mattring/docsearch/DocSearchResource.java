package com.mattring.docsearch;

import com.mattring.smallsearch.Search;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.server.servlet.HttpServletResponseWrapper;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("/docSearch.action")
public class DocSearchResource {

    @Inject
    @Location("DocSearchResults.html")
    Template resultsTemplate;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance find(@MultipartForm DocSearchFormData docSearchFormData) {
        List<Object> queryList = Arrays.asList(docSearchFormData.queries.split("\\R"));
        List<Object> results = Search.find(queryList, docSearchFormData.docText);
        DocSearchResultsData resultsData =
                new DocSearchResultsData(
                        Boolean.valueOf(docSearchFormData.returnBodyOnly),
                        Boolean.valueOf(docSearchFormData.turboTag),
                        results.size());
        int i = 0;
        for (Object result : results) {
            resultsData.queryHits.put(queryList.get(i++).toString(), Integer.valueOf(result.toString()));
        }
        return resultsTemplate.data("resultsData", resultsData);
    }
}