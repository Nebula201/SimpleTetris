package org.nebula.tetris.service.configuration;

import org.nebula.tetris.model.configuration.Configuration;

public interface ConfigurationLoader {
    Configuration load();

    void save(Configuration configuration);
}
