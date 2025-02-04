package org.example.helpers.report;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomReportPortalExtension extends ReportPortalExtension {

    private static final Logger logger = LoggerFactory.getLogger(CustomReportPortalExtension.class);

    public static void reportStep(String stepName) {
        logger.info("Reporting step to ReportPortal: " + stepName);
        // Use ReportPortal's @Step annotation
        executeStep(stepName);
    }

    @Step("{0}")
    private static void executeStep(String stepName) {
        // Placeholder to trigger the ReportPortal step logging
    }
}
