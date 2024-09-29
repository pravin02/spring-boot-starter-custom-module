package com.example.module.configuration;

import com.example.module.annotation.EnableCustomModule;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomModuleConfigurationSelector extends AdviceModeImportSelector<EnableCustomModule> {

    private static final Logger LOGGER = Logger.getLogger(CustomModuleConfigurationSelector.class.getSimpleName());

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        LOGGER.info("selectImports: adviceMode: " + adviceMode);
        String[] data = {};
        switch (adviceMode) {
            case PROXY: {
                data = getProxyImports();
                break;
            }
            case ASPECTJ: {
                data = getAspectJImports();
            }
        }
        return data;
    }

    private String[] getAspectJImports() {
        return null;
    }

    private String[] getProxyImports() {
        List<String> result = new ArrayList<>(2);
        result.add(AutoProxyRegistrar.class.getName());
        result.add(CustomModuleConfiguration.class.getName());

        LOGGER.info("selectImports: getProxyImports: " + result);
        return StringUtils.toStringArray(result);
    }
}
