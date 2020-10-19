package com.resjz.web.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.addViewController("/czPassword").setViewName("czPassword.html");
        registry.addViewController("/czSuccess").setViewName("czSuccess.html");
//        registry.addViewController("/wjfa1.html").setViewName("wjfa1.html");
        registry.addViewController("/index.html").setViewName("forward:/index");
    }
}
