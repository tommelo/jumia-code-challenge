package com.jumia.customer.converter;

import java.util.List;

/**
 * The entity converter.
 * A helper interface to convert entities and models.
 *
 * @param <I> The input type to be converted
 * @param <O> The output type
 */
public interface EntityConverter<I, O> {

    /**
     * Converts the given input {@code I} into the output {@code O}.
     *
     * @param entity The input entity
     * @return model The output model
     */
    O convert(I entity) throws EntityConversionException;

    /**
     * Converts the given input {@code List<I>} into the output {@code List<O>}.
     *
     * @param entities The list of entities to be converted
     * @return models The list of converted models
     */
    List<O> convert(List<I> entities) throws EntityConversionException;

}