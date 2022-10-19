package com.mattring.docsearch;

import io.quarkus.qute.TemplateData;

import java.util.LinkedHashMap;
import java.util.Map;

@TemplateData
public class DocSearchResultsData {
    public final boolean returnBodyOnly;
    public final boolean turboTag;
    public final Map<String, Integer> queryHits;

    public DocSearchResultsData(boolean returnBodyOnly, boolean turboTag, int numHits) {
        this.returnBodyOnly = returnBodyOnly;
        this.turboTag = turboTag;
        this.queryHits = new LinkedHashMap<>(numHits);
    }
}
