/* 
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons-sandbox//functor/src/test/org/apache/commons/functor/adapter/TestBinaryFunctionBinaryPredicate.java,v 1.5 2003/12/03 01:04:12 rwaldhoff Exp $
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived 
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.commons.functor.adapter;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.functor.BaseFunctorTest;
import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.core.Constant;

/**
 * @version $Revision: 1.5 $ $Date: 2003/12/03 01:04:12 $
 * @author Rodney Waldhoff
 */
public class TestBinaryFunctionBinaryPredicate extends BaseFunctorTest {

    // Conventional
    // ------------------------------------------------------------------------

    public TestBinaryFunctionBinaryPredicate(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestBinaryFunctionBinaryPredicate.class);
    }

    // Functor Testing Framework
    // ------------------------------------------------------------------------

    protected Object makeFunctor() {
        return new BinaryFunctionBinaryPredicate(new Constant(Boolean.TRUE));
    }

    // Lifecycle
    // ------------------------------------------------------------------------

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    // Tests
    // ------------------------------------------------------------------------    

    public void testTestWhenTrue() throws Exception {
        BinaryPredicate p = new BinaryFunctionBinaryPredicate(new Constant(Boolean.TRUE));
        assertTrue(p.test(null,null));
    }
    
    public void testTestWhenFalse() throws Exception {
        BinaryPredicate p = new BinaryFunctionBinaryPredicate(new Constant(Boolean.FALSE));
        assertTrue(!p.test(null,null));
    }

    public void testTestWhenNull() throws Exception {
        BinaryPredicate p = new BinaryFunctionBinaryPredicate(new Constant(null));
        try {
            p.test("xyzzy",Boolean.TRUE);
            fail("Expected NullPointerException");
        } catch(NullPointerException e) {
            // expected
        }
    }
    
    public void testTestWhenNonBoolean() throws Exception {
        BinaryPredicate p = new BinaryFunctionBinaryPredicate(new Constant(new Integer(2)));
        try {
            p.test("xyzzy",Boolean.TRUE);
            fail("Expected ClassCastException");
        } catch(ClassCastException e) {
            // expected
        }
    }
    
    public void testEquals() throws Exception {
        BinaryPredicate p = new BinaryFunctionBinaryPredicate(new Constant(Boolean.TRUE));
        assertEquals(p,p);
        assertObjectsAreEqual(p,new BinaryFunctionBinaryPredicate(new Constant(Boolean.TRUE)));
        assertObjectsAreNotEqual(p,Constant.truePredicate());
        assertObjectsAreNotEqual(p,new BinaryFunctionBinaryPredicate(null));
        assertObjectsAreNotEqual(p,new BinaryFunctionBinaryPredicate(new Constant(Boolean.FALSE)));
        assertObjectsAreEqual(new BinaryFunctionBinaryPredicate(null),new BinaryFunctionBinaryPredicate(null));
    }

    public void testAdaptNull() throws Exception {
        assertNull(BinaryFunctionBinaryPredicate.adapt(null));
    }

    public void testAdapt() throws Exception {
        assertNotNull(BinaryFunctionBinaryPredicate.adapt(new Constant(Boolean.TRUE)));
    }
}
