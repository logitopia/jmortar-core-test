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
package com.logitopia.jmortar.core.test;

import com.logitopia.jmortar.core.test.exception.PrivateTestMethodException;
import com.logitopia.jmortar.core.test.exception.TestFieldException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * The <tt>AbstractUniTest</tt> is a unit test that provides access to the subject and private
 * subject resources.
 *
 * @param <T> The subject type.
 * @author Stephen Cheesley
 */
public abstract class AbstractUnitTest<T> {

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
     * Get the value from a private field on the test subject class.
     *
     * @param fieldName The name of the field we wish to get the value for.
     * @return The Object representing the value of the field.
     * @throws TestFieldException thrown when the field is missing or inaccessible.
     */
    public Object getFieldValue(final String fieldName) throws TestFieldException {
        try {
            Field field = getField(subject.getClass(), fieldName);
            field.setAccessible(true);

            return field.get(subject);
        } catch (NoSuchFieldException e) {
            String msg = new StringBuilder("The field ")
                    .append(fieldName)
                    .append(" was missing from the test object of type ")
                    .append(subject.getClass().getSimpleName())
                    .toString();
            throw new TestFieldException(msg, e);
        } catch (IllegalAccessException e) {
            String msg = new StringBuilder("Unable to access the field ")
                    .append(fieldName)
                    .append(" from the test object of type ")
                    .append(subject.getClass().getSimpleName())
                    .toString();
            throw new TestFieldException(msg, e);
        }
    }

    private Field getField(final Class clazz, final String fieldName) throws NoSuchFieldException {
        Field declaredField;
        try {
            declaredField = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            }
            declaredField = getField(superClass, fieldName);
        }
        return declaredField;
    }

    /**
     * Execute a private method on the given subject.
     *
     * @param methodName     The name of the method on the subject to execute.
     * @param parameterTypes The types of the parameters that the method uses.
     * @param parameters     The parameters to be passed to the method.
     * @return The response from the private method.
     * @throws PrivateTestMethodException An exception occurs when we can't reflectively access the private method.
     */
    public Object executePrivateMethod(final String methodName,
                                       final Class[] parameterTypes,
                                       final Object[] parameters) throws PrivateTestMethodException {
        try {
            Method privateMethod = subject.getClass().getDeclaredMethod(methodName, parameterTypes);
            privateMethod.setAccessible(true);

            return privateMethod.invoke(subject, parameters);
        } catch (Exception ex) {
            throw new PrivateTestMethodException(ex);
        }
    }
}
