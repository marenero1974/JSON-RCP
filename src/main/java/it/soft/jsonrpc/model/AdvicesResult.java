package it.soft.jsonrpc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AdvicesResult {

  @JsonProperty("total_results")
  public String totalResults;

  @JsonProperty("query")
  public String query;

  @JsonProperty("slips")
  public List<it.soft.jsonrpc.model.SlipAdvice> slips;


  public String getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(String totalResults) {
    this.totalResults = totalResults;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public List<it.soft.jsonrpc.model.SlipAdvice> getSlips() {
    return slips;
  }

  public void setSlips(List<it.soft.jsonrpc.model.SlipAdvice> slips) {
    this.slips = slips;
  }
}
