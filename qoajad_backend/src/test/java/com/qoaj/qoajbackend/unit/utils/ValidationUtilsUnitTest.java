package com.qoaj.qoajbackend.unit.utils;

import com.qoajad.backend.utils.ValidationUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidationUtilsUnitTest {

    @Test
    public void testRequireEqualReturnsTrue() {
        Assert.assertTrue(ValidationUtils.requireEqual(4, 4, ""));
        Assert.assertTrue(ValidationUtils.requireEqual(-1, -1, ""));
        Assert.assertTrue(ValidationUtils.requireEqual(-51452, -51452, ""));
        Assert.assertTrue(ValidationUtils.requireEqual(8749, 8749, ""));
    }

    @Test(expected = IllegalStateException.class)
    public void testRequireEqualReturnsFalse() {
        ValidationUtils.requireEqual(4, 5, "");
        ValidationUtils.requireEqual(-1, 0, "");
        ValidationUtils.requireEqual(-1, 1, "");
        ValidationUtils.requireEqual(8749, -3784, "");
    }

    @Test
    public void testRequireNotEqualReturnsTrue() {
        Assert.assertTrue(ValidationUtils.requireNotEqual(4, 9, ""));
        Assert.assertTrue(ValidationUtils.requireNotEqual(-1, -100, ""));
        Assert.assertTrue(ValidationUtils.requireNotEqual(51452, 301, ""));
        Assert.assertTrue(ValidationUtils.requireNotEqual(0, -10000, ""));
    }

    @Test(expected = IllegalStateException.class)
    public void testRequireNotEqualNotReturnsFalse() {
        ValidationUtils.requireNotEqual(7, 7, "");
        ValidationUtils.requireNotEqual(-1, -1, "");
        ValidationUtils.requireNotEqual(-345, -345, "");
        ValidationUtils.requireNotEqual(2222, 2222, "");
    }

    @Test
    public void testRequireLeftGreaterThanRightReturnsTrue() {
        Assert.assertTrue(ValidationUtils.requireLeftGreaterThanRight(10, 9, ""));
        Assert.assertTrue(ValidationUtils.requireLeftGreaterThanRight(-1, -100, ""));
        Assert.assertTrue(ValidationUtils.requireLeftGreaterThanRight(51452, 301, ""));
        Assert.assertTrue(ValidationUtils.requireLeftGreaterThanRight(100, -10000, ""));
    }

    @Test(expected = IllegalStateException.class)
    public void testRequireLeftGreaterThanRightThrowsException() {
        ValidationUtils.requireLeftGreaterThanRight(7, 7, "");
        ValidationUtils.requireLeftGreaterThanRight(15, 20, "");
        ValidationUtils.requireLeftGreaterThanRight(-345, -343, "");
        ValidationUtils.requireLeftGreaterThanRight(-2222, 1000, "");
    }
}