package com.sodabottle.freadr.utils;

public class RandomNumberGenerator {

    /**
     * Generates a random number between min and max including both
     *
     * @param min
     * @param max
     * @return randomNumber
     */
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
