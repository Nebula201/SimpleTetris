package org.nebula.tetris.service.configuration;

public class ConfigurationLoaderService {
    protected static final ConfigurationLoaderService CONFIGURATION_PERSISTER_SERVICE = new ConfigurationLoaderService();

//    protected ConfigurationLoader provider = new ConfigurationXmlPersisterProvider();

    public static ConfigurationLoaderService getInstance() { return CONFIGURATION_PERSISTER_SERVICE; }

    protected ConfigurationLoaderService() {}

//    public ConfigurationLoaderService get() {
//        return provider;
//    }
}
