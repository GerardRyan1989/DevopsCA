package edu.cpp.cs580;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.cpp.cs580.data.provider.EBayGpsProductManager;
import edu.cpp.cs580.data.provider.FSUserManager;
import edu.cpp.cs580.data.provider.GpsProductManager;
import edu.cpp.cs580.data.provider.UserManager;

import io.sentry.context.Context;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {
    //kansdkgpfn
	private static final Logger logger = LoggerFactory.getLogger(App.class.getName());
    private static SentryClient sentry;
    /**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */

    @Bean
    public UserManager userManager() {
        UserManager userManager =  new FSUserManager();
        return userManager;
    }

    @Bean
    public GpsProductManager gpsProductManager() {
    		GpsProductManager gpsManager = new EBayGpsProductManager();
    		return gpsManager;
    }

    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
        sentry = SentryClientFactory.sentryClient();
        logWithStaticAPI();
    }

    private static void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }

    private static void logWithStaticAPI() {
        // Note that all fields set on the context are optional. Context data is copied onto
        // all future events in the current context (until the context is cleared).

        // Record a breadcrumb in the current context. By default the last 100 breadcrumbs are kept.
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage("User made an action").build()
        );

        // Set the user in the current context.
        Sentry.getContext().setUser(
                new UserBuilder().setEmail("hello@sentry.io").build()
        );

        // Add extra data to future events in this context.
        Sentry.getContext().addExtra("extra", "thing");

        // Add an additional tag to future events in this context.
        Sentry.getContext().addTag("tagName", "tagValue");

        /*
         This sends a simple event to Sentry using the statically stored instance
         that was created in the ``main`` method.
         */
        Sentry.capture("This is a test");

        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry using the statically stored instance
            // that was created in the ``main`` method.
            Sentry.capture(e);
        }
    }
}
