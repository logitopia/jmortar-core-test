/*
 *     JMortar - Tools to make your Java life easier.
 *     Copyright (C) 2015-2017 Stephen Cheesley
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.logitopia.platform.core.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <tt>AbstractUniTest</tt> is a unit test that provides access to the subject and private
 * subject resources.
 *
 * @author Stephen Cheesley
 * @param <T> The subject type.
 */
public abstract class AbstractUnitTest<T> {
  
  /**
   * The logger for this class.
   */
  private static final Logger LOG = LoggerFactory.getLogger(AbstractUnitTest.class);

  /**
   * The subject of the unit test (the class that we will be testing on).
   */
  private T subject;

  /**
   * Get the subject.
   *
   * @return The subject of this test.
   */
  public T getSubject() {
    return subject;
  }

  /**
   * Set the test subject.
   *
   * @param newSubject The subject of this test.
   */
  public void setSubject(final T newSubject) {
    subject = newSubject;
  }

  /**
   * Execute a private method on the given subject.
   *
   * @param methodName The name of the method on the subject to execute.
   * @param parameterTypes The types of the parameters that the method uses.
   * @param parameters The parameters to be passed to the method.
   * @return The response from the private method.
   */
  public Object executePrivateMethod(final String methodName,
          final Class[] parameterTypes,
          final Object[] parameters) {
    try {
      Method privateMethod = subject.getClass().getDeclaredMethod(methodName, parameterTypes);
      privateMethod.setAccessible(true);

      return privateMethod.invoke(subject, parameters);
    } catch (NoSuchMethodException ex) {
      LOG.error("The method requested does not exist on the subject.", ex);
    } catch (SecurityException ex) {
      LOG.error("There has been an issue with security", ex);
    } catch (IllegalAccessException ex) {
      LOG.error("Unable to access the requested method.", ex);
    } catch (IllegalArgumentException ex) {
      LOG.error("The requested method is not configured with the arguments requested.", ex);
    } catch (InvocationTargetException ex) {
      LOG.error("An invocation target exception has occurred", ex);
    }
    
    return null;
  }
}
