package com.mattring.docsearch;

import io.quarkus.qute.TemplateData;

import java.util.LinkedHashMap;
import java.util.Map;

@TemplateData
public class DocSearchResultsData {
    public final boolean returnBodyOnly;
    public final Map<String, Integer> queryHits;

    public DocSearchResultsData(boolean returnBodyOnly, int numHits) {
        this.returnBodyOnly = returnBodyOnly;
        this.queryHits = new LinkedHashMap<>(numHits);
    }
}
