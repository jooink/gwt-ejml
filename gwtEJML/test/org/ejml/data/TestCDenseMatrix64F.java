/*
 * Copyright (c) 2009-2014, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ejml.data;

import org.ejml.GWTEJMLTestCase;
import org.ejml.ops.CCommonOps;
import org.ejml.ops.CMatrixFeatures;
import org.ejml.ops.CRandomMatrices;
import org.ejml.ops.EjmlUnitTests;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Peter Abeles
 */
public class TestCDenseMatrix64F  extends GWTEJMLTestCase  {

    Random rand = new Random(234);

    @Test
    public void testconstructor_darray() {
        CDenseMatrix64F a = new CDenseMatrix64F(new double[][]{{1,2,3,4},{5,6,7,8},{5,6,7,8}});
        CDenseMatrix64F b = new CDenseMatrix64F(3,2,true,1,2,3,4,5,6,7,8,5,6,7,8);

        EjmlUnitTests.assertEquals(a, b, 1e-8);
    }

    @Test
    public void testconstructor_cmatrix() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);
        a.set(1,3,9,2);

        CDenseMatrix64F b = new CDenseMatrix64F(a);
        for (int i = 0; i < a.getDataLength(); i++) {
            assertEquals(a.data[i],b.data[i],1e-8);
        }
    }

    @Test
    public void testconstructor_shape() {
        CDenseMatrix64F a = new CDenseMatrix64F(5,7);
        assertEquals(5,a.numRows);
        assertEquals(7,a.numCols);
        assertEquals(5*7*2,a.data.length);

    }

    @Test
    public void testgetIndex() {
        CDenseMatrix64F a = new CDenseMatrix64F(5,7);

        assertEquals(2*14+6,a.getIndex(2,3));
    }

    @Test
    public void testreshape() {
        CDenseMatrix64F a = new CDenseMatrix64F(5,7);

        assertEquals(5*7*2,a.data.length);
        assertEquals(5, a.numRows);
        assertEquals(7,a.numCols);

        // make it larger
        a.reshape(10,6);

        assertEquals(10*6*2,a.data.length);
        assertEquals(10,a.numRows);
        assertEquals(6,a.numCols);

        // make it smaller
        a.reshape(3,2);

        assertTrue(a.data.length > 3*2*2);
        assertEquals(3,a.numRows);
        assertEquals(2,a.numCols);
    }

    @Test
    public void testget() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);

        a.data[2*4*2 + 2] = 5;
        a.data[2*4*2 + 3] = 6;

        Complex64F c = new Complex64F();
        a.get(2,1,c);

        assertEquals(c.real,5,1e-8);
        assertEquals(c.imaginary, 6, 1e-8);
    }

    @Test
    public void testset_rowcolumn() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);

        a.set(2, 1, 5, 6);

        assertEquals(5,a.data[2*4*2+2],1e-8);
        assertEquals(6,a.data[2*4*2+3],1e-8);
    }

    @Test
    public void testgetReal() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);

        a.data[2*4*2 + 2] = 5;
        a.data[2*4*2 + 3] = 6;

        Complex64F c = new Complex64F();
        a.get(2,1,c);

        assertEquals(a.getReal(2, 1), 5, 1e-8);
    }

    @Test
    public void testsetReal() {

        CDenseMatrix64F a = new CDenseMatrix64F(3,4);

        a.setReal(2,1,5);

        assertEquals(5, a.data[2 * 4 * 2 + 2], 1e-8);
    }

    @Test
    public void testgetImaginary() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);

        a.data[2*4*2 + 2] = 5;
        a.data[2*4*2 + 3] = 6;

        Complex64F c = new Complex64F();
        a.get(2,1,c);

        assertEquals(a.getImaginary(2, 1), 6, 1e-8);
    }

    @Test
    public void testsetImaginary() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);

        a.setImaginary(2, 1, 6);

        assertEquals(6, a.data[2 * 4 * 2 + 3], 1e-8);
    }

    @Test
    public void testgetDataLength() {
        assertEquals(3*4*2,new CDenseMatrix64F(3,4).getDataLength());
    }

    @Test
    public void testcopy() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);
        a.set(1, 3, 9, 2);

        CDenseMatrix64F b = a.copy();
        for (int i = 0; i < a.getDataLength(); i++) {
            assertEquals(a.data[i],b.data[i],1e-8);
        }
    }

    @Test
    public void testgetRowStride() {
        CDenseMatrix64F a = new CDenseMatrix64F(3,4);
        assertEquals(4*2,a.getRowStride());
    }

    @Test
    public void testset_array() {
        CDenseMatrix64F A = CRandomMatrices.createRandom(3,4,rand);

        CDenseMatrix64F B = new CDenseMatrix64F(1,1);
        B.set(3,4,true,A.data);

        assertTrue(CMatrixFeatures.isEquals(A,B));

        CDenseMatrix64F A_tran = new CDenseMatrix64F(4,3);
        CCommonOps.transpose(A,A_tran);

        B.set(3,4,false,A_tran.data);

        assertTrue(CMatrixFeatures.isEquals(A,B));
    }
}
