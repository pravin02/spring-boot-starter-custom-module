# spring-boo-starter-custom-module

The purpose of the application is to demonstration how we can develop separate spring boot application and add in another application which will be autoconfigured or enabled via annotation by applying on main application.

Below are the three spring boot applications.

### modules are as below
    i. main-app
    ii. module-app-with-annotation
    iii. spring-boot-starter-custom-module

1. ### main-app
    At the time of application startup spring boot looks for below named file for all the added dependencies in main-app pom.xml.

        META-INF/org.springframework.boot.autoconfigure.AutoConfiguration.imports

2. ### module-app-with-annotation

        Here `CustomModuleService` is part of annotation module which will be registered to BeanFactory only if @EnableCustomModule applied on the application.

        @EnableCustomModule annotation can be used to configure module-app-with-annotation.
        As a demonstration it applied on Main class of main-app module
           
        @EnableCustomModule
        @SpringBootApplication
        public class MainAppApplication {}
               
        To make it work we wrote own annotation inside module-app-with-annotation
        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        @Import(CustomModuleConfigurationSelector.class)
        public @interface EnableCustomModule {}
        
        On this annotation we applied @Import annotation from spring boot which will help to register our beans in proxy or advice mode for this example we have proxy mode only which updated as default value in annotation defination.
        
        CustomModuleConfigurationSelector extends AdviceModeImportSelector<EnableCustomModule> and takes our EnableCustomModule annotation as generic and overries selectImports method where we are providing our CustomModuleConfiguration class and springs AutoProxyRegistrar class.
        
        CustomModuleConfiguration.java - in this configuration we are instantiating beans which could be used if @EnableCustomModule annotation applied.

        @Configuration(proxyBeanMethods = false)
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        public class CustomModuleConfiguration implements ImportAware {
        
            @Nullable
            protected AnnotationAttributes enableCustomModule;

            @Bean
            @ConditionalOnMissingBean
            public CustomModuleService getCustomModuleService() {
                return new CustomModuleService();
            }
                
            @Override
            public void setImportMetadata(AnnotationMetadata importMetadata) {
                LOGGER.info("setImportMetadata: inside");
                this.enableCustomModule = AnnotationAttributes.fromMap(
                        importMetadata.getAnnotationAttributes(EnableCustomModule.class.getName()));
                LOGGER.info("setImportMetadata: moduleName: " + EnableCustomModule.class.getName() + "enableCustomModule: " + enableCustomModule);
                if (this.enableCustomModule == null) {
                    throw new IllegalArgumentException("@EnableCustomModule is not present on importing class " + importMetadata.getClassName());
                }
            }
        }

3. ### spring-boot-starter-custom-module
       CustomStarterService is part of autoconfiguration module like starter which will be registered to BeanFactory when dependency will available on classpath.

       @AutoConfiguration
       @ConditionalOnClass(CustomStarterService.class)
       public class CustomStarterAutoConfiguration {
           @Bean
           @ConditionalOnMissingBean
           public CustomStarterService getCustomStarterService() {
               return new CustomStarterService();
           }
       }
       For this module to register our AutoConfiguration annotated class with spring boot we need to create 
       `src/main/resource/META-INF/org.springframework.boot.autoconfigure.AutoConfiguration.imports` named file and add this `com.example.module.starter.configuration.CustomStarterAutoConfiguration` line.

