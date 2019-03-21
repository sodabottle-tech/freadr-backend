package com.sodabottle.utils;

import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ModelMapperUtils {
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFullTypeMatchingRequired(false);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public static <T> Optional<T> map(Object source, Class<T> destinationClass) {
        if (null == source || null == destinationClass) {
            return Optional.empty();
        }
        return Optional.of(modelMapper.map(source, destinationClass));
    }

}
