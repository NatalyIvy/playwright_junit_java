package org.example.helpers;

import org.aeonbits.owner.ConfigCache;

//is a singleton class with a private constructor to prevent instantiation.
public final class PropertiesAccessor {

    private PropertiesAccessor() {
        // No need to allow instantiation of this class
    }

    /*
    The getInstance() method returns the singleton instance of PropertiesAccessor using the
    static inner class SingletonHolder.
     */
    public static PropertiesAccessor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /*
    The values() method returns an instance of a class that implements the PropertyMapper interface.
    This instance is obtained using ConfigCache.getOrCreate(PropertyMapper.class, System.getenv()).
     */
    public PropertyMapper values() {
        return ConfigCache.getOrCreate(PropertyMapper.class, System.getenv());
    }

    private static class SingletonHolder {
        private static final PropertiesAccessor INSTANCE = new PropertiesAccessor();
    }
}