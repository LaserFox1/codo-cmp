package com.lkww.bitlog.btlg.exceptions;

import java.io.IOException;

public class ServiceException extends RuntimeException{


    private static final String EXEC_INTERRUPT = "Error executing Python Script - execution interrupted!";
    private static final String EXEC_CMD_NOT_FOUND = "Error executing Python Script - Script not found!";
    private static final String EXEC_UNDETERMINED = "Error executing Python Script due to undetermined reason";

    private static final String READ_IO = "Error reading InputStream - InputStream not found";
    private static final String READ_UNDETERMINED = "Error reading InputStream due to undetermined reason";

    public ServiceException(String message, Throwable rootCause){
        super(message,rootCause);
    }


    public static ServiceException execInterrupted(InterruptedException iE) {
        return new ServiceException(EXEC_INTERRUPT, iE);
    }
    public static ServiceException execCmdNotFound(IOException ioE) {
        return new ServiceException(EXEC_CMD_NOT_FOUND, ioE);
    }
    public static ServiceException execUndetermined(Throwable t) {
        return new ServiceException(EXEC_UNDETERMINED, t);
    }

    public static ServiceException readIO(IOException ioE) {
        return new ServiceException(READ_IO, ioE);
    }
    public static ServiceException readUndetermined(Throwable t) {
        return new ServiceException(READ_UNDETERMINED, t);
    }

}
