package com.sodabottle.freadr.utils;

import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ModelMapperUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T> Optional<T> map(Object source, Class<T> destinationClass) {
        if (null == source || null == destinationClass) {
            return Optional.empty();
        }
        return Optional.of(modelMapper.map(source, destinationClass));
    }

}
