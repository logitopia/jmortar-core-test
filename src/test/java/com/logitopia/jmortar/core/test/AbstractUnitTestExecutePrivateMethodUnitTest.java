package com.logitopia.jmortar.core.test;

import com.logitopia.jmortar.core.test.exception.PrivateTestMethodException;
import com.logitopia.jmortar.core.test.fixtures.TestUnitTest;
import junit.framework.TestCase;
import org.junit.*;

public class AbstractUnitTestExecutePrivateMethodUnitTest extends TestCase {

    /**
     * Test Subject.
     */
    private TestUnitTest subject;

    /*
     * Default Constructor.
     */
    public AbstractUnitTestExecutePrivateMethodUnitTest() {
    }

    /**
     * {@inheritDoc}
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * {@inheritDoc}
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * {@inheritDoc}
     */
    @Before
    public void setUp() {
        subject = new TestUnitTest();
    }

    /**
     * {@inheritDoc}
     */
    @After
    public void tearDown() {
    }

    /**
     * Test that, given positive input, we get positive output.
     */
    @Test
    public void testBasicSuccess() {
        try {
            Object result = subject.executePrivateMethod("getValue", new Class[]{},
                    new Object[]{});
            assertNotNull("Has a result been returned?", result);
            assertTrue("Is result correct type", result instanceof Integer);
        } catch (PrivateTestMethodException e) {
            fail("An exception has been caught.");
        }
    }

    /**
     * Test that, given a missing private method, that we get an expected exception.
     */
    @Test
    public void testMissingPrivateMethod() {
        try {
            Object result = subject.executePrivateMethod("invalidMethod", new Class[]{},
                    new Object[]{});
            fail("An exception has not been thrown.");
        } catch (PrivateTestMethodException e) {
            assertTrue("Is exception of the correct type", e instanceof PrivateTestMethodException);
            assertTrue("Is the cause of expected type", e.getCause() instanceof NoSuchMethodException);
        }
    }

    /**
     * Test that, given an invalid parameter type for a private method, we get an expected exception
     */
    @Test
    public void testInvalidParameterOnPrivateMethod() {
        try {
            Object result = subject.executePrivateMethod("buildValue", new Class[]{Integer.class},
                    new Object[]{2});
            fail("An exception has not been thrown.");
        } catch (PrivateTestMethodException e) {
            assertTrue("Is exception of the correct type", e instanceof PrivateTestMethodException);
            assertTrue("Is the cause of expected type", e.getCause() instanceof NoSuchMethodException);
        }
    }
}
