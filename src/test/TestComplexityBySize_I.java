package test;

import org.junit.Test;
import Vikum.java.controllers.ComplexityBySize_I;

import static org.junit.Assert.*;

public class TestComplexityBySize_I {

    private ComplexityBySize_I testObj = new ComplexityBySize_I();

    @Test
    public void TestMatchPattern() {
        assertTrue(testObj.matchPattern("integer + double", "(?<!\\+)\\+(?![+=])"));
    }

    @Test
    public void TestCheckEmptyStatement() {
        assertTrue(testObj.checkEmptyStatement(""));
    }

    @Test
    public void TestCheckMultiLineCommentStart() {
        assertTrue(testObj.checkMultiLineCommentStart("/*commentstart"));
    }

    @Test
    public void TestCheckMultiLineCommentEnd() {
        assertTrue(testObj.checkMultiLineCommentEnd("commentend*/"));
    }

}

