package com.example.module.configuration;

import com.example.module.annotation.EnableCustomModule;
import com.example.module.service.CustomModuleService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.util.logging.Logger;

@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class CustomModuleConfiguration implements ImportAware {

    private static final Logger LOGGER = Logger.getLogger(CustomModuleConfiguration.class.getSimpleName());

    @Nullable
    protected AnnotationAttributes enableCustomModule;

    /**
     * @return CustomModuleService instantiating bean if not exists
     */
    @Bean
    @ConditionalOnMissingBean
    public CustomModuleService getCustomModuleService() {
        return new CustomModuleService();
    }

    /**
     * @param importMetadata help to get attributes of the annotation for processing initialization
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        LOGGER.info("setImportMetadata: inside");
        this.enableCustomModule = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableCustomModule.class.getName()));
        LOGGER.info("setImportMetadata: annotation: " + EnableCustomModule.class.getName() + ", enableCustomModule: " + enableCustomModule);
        if (this.enableCustomModule == null) {
            throw new IllegalArgumentException("@EnableCustomModule is not present on importing class " + importMetadata.getClassName());
        }
    }

}
