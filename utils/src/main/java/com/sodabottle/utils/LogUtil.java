/*
 * PubMatic Inc. ("PubMatic") CONFIDENTIAL
 *
 * Unpublished Copyright (c) 2006-2018 PubMatic, All Rights Reserved.
 *
 *
 *
 * NOTICE: All information contained herein is, and remains the property of
 * PubMatic. The intellectual and technical concepts contained
 *
 * herein are proprietary to PubMatic and may be covered by U.S. and Foreign
 * Patents, patents in process, and are protected by trade secret or copyright
 * law.
 *
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained
 *
 * from PubMatic. Access to the source code contained herein is hereby forbidden
 * to anyone except current PubMatic employees, managers or contractors who have
 * executed
 *
 * Confidentiality and Non-disclosure agreements explicitly covering such
 * access.
 *
 *
 *
 * The copyright notice above does not evidence any actual or intended
 * publication or disclosure of this source code, which includes
 *
 * information that is confidential and/or proprietary, and is a trade secret,
 * of PubMatic. ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC
 * PERFORMANCE,
 *
 * OR PUBLIC DISPLAY OF OR THROUGH USE OF THIS SOURCE CODE WITHOUT THE EXPRESS
 * WRITTEN CONSENT OF PubMatic IS STRICTLY PROHIBITED, AND IN VIOLATION OF
 * APPLICABLE
 *
 * LAWS AND INTERNATIONAL TREATIES. THE RECEIPT OR POSSESSION OF THIS SOURCE
 * CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
 *
 * TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR
 * SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 */

package com.sodabottle.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class LogUtil {
    private static String LOG_SUCCESS_PATTERN = " +++++++++++++++ ";
    private static String LOG_ERROR_PATTERN = " --------------- ";
    private static String DEBUG_PATTERN = " *************** ";
    private static String STANDARD_LOG_PATTERN = " %s %s %s "; // Pattern + message + Pattern

    private static String appendPatternToLogMessage(String message, final LogState state) {
        String formattedLogMsg = null;

        switch (state) {
            case INFO:
                formattedLogMsg = String
                        .format(STANDARD_LOG_PATTERN, LOG_SUCCESS_PATTERN, message, LOG_SUCCESS_PATTERN);
                break;

            case DEBUG:
                formattedLogMsg = String.format(STANDARD_LOG_PATTERN, DEBUG_PATTERN, message, DEBUG_PATTERN);
                break;

            case WARN:
            case ERROR:
                formattedLogMsg = String.format(STANDARD_LOG_PATTERN, LOG_ERROR_PATTERN, message, LOG_ERROR_PATTERN);
                break;

        }
        return formattedLogMsg;
    }

    private static void logMessage(final String parsedMessage, final Logger logger, final LogState state) {
        String finalParsedPatternMessage = null;
        switch (state) {
            case ERROR:
                logger.error(String.format(STANDARD_LOG_PATTERN, LOG_ERROR_PATTERN, parsedMessage, LOG_ERROR_PATTERN));
                break;

            case INFO:
                logger.info(
                        String.format(STANDARD_LOG_PATTERN, LOG_SUCCESS_PATTERN, parsedMessage, LOG_SUCCESS_PATTERN));
                break;

            case WARN:
                logger.warn(String.format(STANDARD_LOG_PATTERN, LOG_ERROR_PATTERN, parsedMessage, LOG_ERROR_PATTERN));
                break;

            case DEBUG:
                logger.debug(String.format(STANDARD_LOG_PATTERN, DEBUG_PATTERN, parsedMessage, DEBUG_PATTERN));
                break;

            default:
                break;
        }

    }

    /**
     * Logs a Debug message based on the message, arguments and originating Class name log instance.
     *
     * @param message   : Log message to be printed with dynamic arguments. message = "%s 123 %s"
     * @param arguments : Arguments to be substituted in the message. %s will be replaced by arguments array.
     * @param logger    : Error Originating Class Logger Instance
     */
    public static void logDebugMessage(final String message, String[] arguments, final Logger logger) {
        logMessage(String.format(message, arguments), logger, LogState.DEBUG);
    }

    /**
     * Logs a success message based on the message, arguments and originating Class name log instance.
     *
     * @param message   : Log message to be printed with dynamic arguments. message = "%s 123 %s"
     * @param arguments : Arguments to be substituted in the message. %s will be replaced by arguments array.
     * @param logger    : Error Originating Class Logger Instance
     */
    public static void logMessage(final String message, String[] arguments, final Logger logger) {
        logMessage(String.format(message, arguments), logger, LogState.INFO);
    }

    /**
     * Logs an error message based on the message, arguments and originating Class name log instance.
     *
     * @param message   : Log message to be printed with dynamic arguments. message = "%s 123 %s"
     * @param arguments : arguments to be substituted in the message. %s will be replaced by arguments array.
     * @param logger    : Error Originating Class Logger Instance
     */
    public static void logErrorMessage(final String message, String[] arguments, final Logger logger) {
        logMessage(String.format(message, arguments), logger, LogState.ERROR);
    }

    /**
     * Logs a warn message based on the message, arguments and originating Class name log instance.
     *
     * @param message   : Log message to be printed with dynamic arguments. message = "%s 123 %s"
     * @param arguments : arguments to be substituted in the message. %s will be replaced by arguments array.
     * @param logger    : Error Originating Class Logger Instance
     */
    public static void logWarnMessage(final String message, String[] arguments, final Logger logger) {
        logMessage(String.format(message, arguments), logger, LogState.WARN);
    }

    public enum LogState {
        DEBUG, INFO, WARN, ERROR
    }
}
