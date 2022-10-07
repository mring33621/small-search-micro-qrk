package com.mattring.docsearch;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

public class DocSearchFormData {
    @FormParam("docText")
    @PartType(MediaType.TEXT_PLAIN)
    public String docText;
    @FormParam("queries")
    @PartType(MediaType.TEXT_PLAIN)
    public String queries;

    @FormParam("returnBodyOnly")
    @PartType(MediaType.TEXT_PLAIN)
    public String returnBodyOnly;
}
