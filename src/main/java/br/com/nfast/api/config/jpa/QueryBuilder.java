package br.com.nfast.api.config.jpa;

import br.com.nfast.api.utils.StringList;

import java.util.LinkedHashMap;
import java.util.Map;

public class QueryBuilder extends StringList {
    private final Map<String, Object> params = new LinkedHashMap<>();
    private int limit = 0;
    private int offset = 0;

    private String filter;
    private boolean counting;

    @Override
    public QueryBuilder add(Object value) {
        super.add(value);
        return this;
    }

    @Override
    public QueryBuilder clear() {
        super.clear();
        params.clear();
        limit = 0;
        offset = 0;
        return this;
    }

    public QueryBuilder set(String param, Object value) {
        params.put(param, value);
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isCounting() {
        return counting;
    }

    public void setCounting(boolean counting) {
        this.counting = counting;
    }

}