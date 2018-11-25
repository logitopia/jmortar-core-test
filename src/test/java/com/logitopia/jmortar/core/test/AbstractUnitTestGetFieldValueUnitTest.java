package com.logitopia.jmortar.core.test;

import com.logitopia.jmortar.core.test.exception.TestFieldException;
import com.logitopia.jmortar.core.test.fixtures.TestUnitTest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * A unit test of the <tt>getFieldValue</tt> method in the <tt>AbstractUnitTest</tt> class.
 */
public class AbstractUnitTestGetFieldValueUnitTest extends TestCase {

    /**
     * A test implementation of the abstract class.
     */
    private TestUnitTest subject;

    /**
     * Default Constructor.
     */
    public AbstractUnitTestGetFieldValueUnitTest() {
    }

    /**
     * {@inheritDoc}
     */
    @Before
    public void setUp() {
        subject = new TestUnitTest();
    }

    /**
     * Test that, given positive input, we get positive output.
     */
    @Test
    public void testBasicSuccess() {
        try {
            Object result = subject.getFieldValue("testString");
            assertNotNull("Has a result been returned?", result);
            assertTrue("Is result correct type", result instanceof String);

            // Check that the value is expected
            assertEquals("Does the content of the result match as expected", "testStringVal", result);
        } catch (TestFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that, given a missing field, that we get an expected exception.
     */
    @Test
    public void testMissingField() {
        try {
            Object result = subject.getFieldValue("doesNotExist");
            fail("An exception has not been thrown.");
        } catch (TestFieldException e) {
            assertTrue("Is the cause of expected type", e.getCause() instanceof NoSuchFieldException);
        }
    }

    /**
     * Test that, given positive primitive input, we get positive output.
     */
    @Test
    public void testPrimitiveSuccess() {
        try {
            Object result = subject.getFieldValue("testInt");
            assertNotNull("Has a result been returned?", result);
            // Test that integer is autoboxed
            assertTrue("Is result correct type", result instanceof Integer);

            // Check that the value is expected
            assertEquals("Does the content of the result match as expected", 6, result);
        } catch (TestFieldException e) {
            e.printStackTrace();
            fail("An unexpected exception occurred");
        }
    }
}
