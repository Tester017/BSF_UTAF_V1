
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
public class Match {

    @JsonProperty("arguments")
    public List<Argument> arguments = new ArrayList<Argument>();
    @JsonProperty("location")
    public String location;

}
