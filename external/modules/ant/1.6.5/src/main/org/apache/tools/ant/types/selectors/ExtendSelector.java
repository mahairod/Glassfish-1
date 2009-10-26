/*
 * Copyright  2002-2004 The Apache Software Foundation
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

package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Vector;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/**
 * Selector that selects files by forwarding the request on to other classes.
 *
 * @since 1.5
 */
public class ExtendSelector extends BaseSelector {

    private String classname = null;
    private FileSelector dynselector = null;
    private Vector paramVec = new Vector();
    private Path classpath = null;

    /**
     * Default constructor.
     */
    public ExtendSelector() {
    }

    /**
     * Sets the classname of the custom selector.
     *
     * @param classname is the class which implements this selector
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * Instantiates the identified custom selector class.
     */
    public void selectorCreate() {
        if (classname != null && classname.length() > 0) {
            try {
                Class c = null;
                if (classpath == null) {
                    c = Class.forName(classname);
                } else {
                    AntClassLoader al
                            = getProject().createClassLoader(classpath);
                    c = Class.forName(classname, true, al);
                }
                dynselector = (FileSelector) c.newInstance();
                final Project project = getProject();
                if (project != null) {
                    project.setProjectReference(dynselector);
                }
            } catch (ClassNotFoundException cnfexcept) {
                setError("Selector " + classname
                    + " not initialized, no such class");
            } catch (InstantiationException iexcept) {
                setError("Selector " + classname
                    + " not initialized, could not create class");
            } catch (IllegalAccessException iaexcept) {
                setError("Selector " + classname
                    + " not initialized, class not accessible");
            }
        } else {
            setError("There is no classname specified");
        }
    }

    /**
     * Create new parameters to pass to custom selector.
     *
     * @param p The new Parameter object
     */
    public void addParam(Parameter p) {
        paramVec.addElement(p);
    }


    /**
     * Set the classpath to load the classname specified using an attribute.
     * @param classpath the classpath to use
     */
    public final void setClasspath(Path classpath) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (this.classpath == null) {
            this.classpath = classpath;
        } else {
            this.classpath.append(classpath);
        }
    }

    /**
     * Specify the classpath to use to load the Selector (nested element).
     * @return a classpath to be configured
     */
    public final Path createClasspath() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    /**
     * Get the classpath
     * @return the classpath
     */
    public final Path getClasspath() {
        return classpath;
    }

    /**
     * Set the classpath to use for loading a custom selector by using
     * a reference.
     * @param r a reference to the classpath
     */
    public void setClasspathref(Reference r) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createClasspath().setRefid(r);
    }

    /**
     * These are errors specific to ExtendSelector only. If there are
     * errors in the custom selector, it should throw a BuildException
     * when isSelected() is called.
     */
    public void verifySettings() {
        // Creation is done here rather than in isSelected() because some
        // containers may do a validation pass before running isSelected(),
        // but we need to check for the existence of the created class.
        if (dynselector == null) {
            selectorCreate();
        }
        if (classname == null || classname.length() < 1) {
            setError("The classname attribute is required");
        } else if (dynselector == null) {
            setError("Internal Error: The custom selector was not created");
        } else if (!(dynselector instanceof ExtendFileSelector)
                    && (paramVec.size() > 0)) {
            setError("Cannot set parameters on custom selector that does not "
                    + "implement ExtendFileSelector");
        }
    }


    /**
     * Allows the custom selector to choose whether to select a file. This
     * is also where the Parameters are passed to the custom selector,
     * since we know we must have them all by now. And since we must know
     * both classpath and classname, creating the class is deferred to here
     * as well.
     *
     * @exception BuildException if an error occurs
     */
    public boolean isSelected(File basedir, String filename, File file)
            throws BuildException {
        validate();
        if (paramVec.size() > 0 && dynselector instanceof ExtendFileSelector) {
            Parameter[] paramArray = new Parameter[paramVec.size()];
            paramVec.copyInto(paramArray);
            // We know that dynselector must be non-null if no error message
            ((ExtendFileSelector) dynselector).setParameters(paramArray);
        }
        return dynselector.isSelected(basedir, filename, file);
    }

}

