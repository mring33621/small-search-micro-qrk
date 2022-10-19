package com.mattring.docsearch;

import com.mattring.smallsearch.Search;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/docSearch.turbo")
public class DocSearchResourceForTurbo {

    @Inject
    @Location("DocSearchResults.html")
    Template resultsTemplate;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response find(@MultipartForm DocSearchFormData docSearchFormData) {
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
        TemplateInstance templateInstance = resultsTemplate.data("resultsData", resultsData);
        return Response
                .ok()
                .header("Content-Type", "text/vnd.turbo-stream.html")
                .entity(templateInstance.render())
                .build();
    }
}