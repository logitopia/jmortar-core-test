package com.logitopia.platform.core.test;

import com.logitopia.platform.core.test.exception.PrivateTestMethodException;
import com.logitopia.platform.core.test.fixtures.TestUnitTest;
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
}
