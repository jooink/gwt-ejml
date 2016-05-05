package org.ejml;

import org.ejml.alg.dense.linsol.TestLinearSolverSafe;
import org.ejml.data.TestCD1Matrix64F;
import org.ejml.data.TestCDenseMatrix64F;
import org.ejml.data.TestD1Submatrix64F;
import org.ejml.data.TestDenseMatrix32F;
import org.ejml.data.TestDenseMatrix64F;
import org.ejml.data.TestDenseMatrixBool;
import org.ejml.data.TestMatrixIterator32F;
import org.ejml.data.TestMatrixIterator64F;
import org.ejml.ops.TestComplexMath64F;
import org.ejml.ops.TestConvertMatrixType;
import org.ejml.ops.TestMatrixIO;
import org.ejml.ops.TestReadMatrixCsv;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class GWTEJMLTestSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for gwtEJML");
		suite.addTestSuite(TestUtilEjml.class);
		suite.addTestSuite(TestLinearSolverSafe.class);

		suite.addTestSuite(TestCD1Matrix64F.class);
		suite.addTestSuite(TestCDenseMatrix64F.class);
		suite.addTestSuite(TestD1Submatrix64F.class);
		suite.addTestSuite(TestDenseMatrix32F.class);

		suite.addTestSuite(TestDenseMatrix64F.class);
		suite.addTestSuite(TestDenseMatrixBool.class);
		suite.addTestSuite(TestMatrixIterator32F.class);
		suite.addTestSuite(TestMatrixIterator64F.class);

		suite.addTestSuite(TestComplexMath64F.class);

		//suite.addTestSuite(TestConvertMatrixType.class);
		//suite.addTestSuite(TestMatrixIO.class);
		//suite.addTestSuite(TestReadMatrixCsv.class);



		return suite;
	}
}
