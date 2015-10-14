package com.blep.modularTechnology.core.common.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author bluemonster122 <boo122333@gmail.com>
 */
public class LogHelper
{
    private static String MODID;

    public final Logger LOGGER = LogManager.getLogger(MODID);

    public void warn(String msg)
    {
        LOGGER.warn(msg);
    }

    public void error(String msg)
    {
        LOGGER.error(msg);
    }

    public void info(String msg)
    {
        LOGGER.info(msg);
    }

    public void debug(String msg)
    {
        LOGGER.debug(msg);
    }

    public LogHelper(String mod)
    {
        MODID = mod;
    }

}
