package com.hxs.graphql.exceptions;

import com.hxs.service.exceptions.ResourceConflictException;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsteidel
 */
public class ResourceConflictGQLException extends ResourceConflictException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public ResourceConflictGQLException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
