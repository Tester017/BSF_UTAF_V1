
package com.maveric.core.cucumber.reporter.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
public class CucumberResults {

    @JsonProperty("line")
    public Integer line;
    @JsonProperty("elements")
    public List<Element> elements = new ArrayList<Element>();
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("id")
    public String id;
    @JsonProperty("keyword")
    public String keyword;
    @JsonProperty("uri")
    public String uri;
    @JsonProperty("tags")
    public List<Tag> tags = new ArrayList<Tag>();

}
