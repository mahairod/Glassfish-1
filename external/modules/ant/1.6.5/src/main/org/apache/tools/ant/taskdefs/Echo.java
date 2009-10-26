/*
 * Copyright  2000-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
/**
 * Writes a message to the Ant logging facilities.
 *
 *
 * @since Ant 1.1
 *
 * @ant.task category="utility"
 */
public class Echo extends Task {
    protected String message = ""; // required
    protected File file = null;
    protected boolean append = false;

    // by default, messages are always displayed
    protected int logLevel = Project.MSG_WARN;

    /**
     * Does the work.
     *
     * @exception BuildException if something goes wrong with the build
     */
    public void execute() throws BuildException {
        if (file == null) {
            log(message, logLevel);
        } else {
            FileWriter out = null;
            try {
                out = new FileWriter(file.getAbsolutePath(), append);
                out.write(message, 0, message.length());
            } catch (IOException ioe) {
                throw new BuildException(ioe, getLocation());
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ioex) {
                        //ignore
                    }
                }
            }
        }
    }

    /**
     * Message to write.
     *
     * @param msg Sets the value for the message variable.
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /**
     * File to write to.
     * @param file the file to write to, if not set, echo to
     *             standard output
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * If true, append to existing file.
     * @param append if true, append to existing file, default
     *               is false.
     */
    public void setAppend(boolean append) {
        this.append = append;
    }

    /**
     * Set a multiline message.
     * @param msg the CDATA text to append to the output text
     */
    public void addText(String msg) {
        message += getProject().replaceProperties(msg);
    }

    /**
     * Set the logging level. Level should be one of
     * <ul>
     *  <li>error</li>
     *  <li>warning</li>
     *  <li>info</li>
     *  <li>verbose</li>
     *  <li>debug</li>
     * </ul>
     * <p>The default is &quot;warning&quot; to ensure that messages are
     * displayed by default when using the -quiet command line option.</p>
     * @param echoLevel the logging level
     */
    public void setLevel(EchoLevel echoLevel) {
        String option = echoLevel.getValue();
        if (option.equals("error")) {
            logLevel = Project.MSG_ERR;
        } else if (option.equals("warning")) {
            logLevel = Project.MSG_WARN;
        } else if (option.equals("info")) {
            logLevel = Project.MSG_INFO;
        } else if (option.equals("verbose")) {
            logLevel = Project.MSG_VERBOSE;
        } else {
            // must be "debug"
            logLevel = Project.MSG_DEBUG;
        }
    }

    /**
     * The enumerated values for the level attribute.
     */
    public static class EchoLevel extends EnumeratedAttribute {
        /**
         * @see EnumeratedAttribute#getValues
         * @return the strings allowed for the level attribute
         */
        public String[] getValues() {
            return new String[] {"error", "warning", "info",
                                 "verbose", "debug"};
        }
    }
}
