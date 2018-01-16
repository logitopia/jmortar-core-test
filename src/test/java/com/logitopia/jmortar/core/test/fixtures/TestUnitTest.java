package com.logitopia.jmortar.core.test.fixtures;

import com.logitopia.jmortar.core.test.AbstractUnitTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestUnitTest extends AbstractUnitTest<ClassUnderTest> {

    public TestUnitTest() {
        setSubject(new ClassUnderTest());
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
    }

    /**
     * {@inheritDoc}
     */
    @After
    public void tearDown() {
    }
}
