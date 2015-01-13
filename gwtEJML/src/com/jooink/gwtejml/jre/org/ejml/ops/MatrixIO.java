package org.ejml.ops;

import org.ejml.data.*;
import org.ejml.ops.ReadMatrixCsv;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Provides simple to use routines for reading and writing matrices to and from files.
 *
 * @author Peter Abeles
 */
public class MatrixIO {

	static Logger lg=Logger.getLogger(MatrixIO.class.getName());

    /**
     * Saves a matrix to disk using Java binary serialization.
     *
     * @param A The matrix being saved.
     * @param fileName Name of the file its being saved at.
     * @throws java.io.IOException
     */
    public static void saveBin(RealMatrix64F A, String fileName)
        throws IOException {
    	//GWT does not support such an operation!
    	throw new UnsupportedOperationException("MatrixIO.saveBin: GWT-EJML does not support saving to file");
    	//
    }

    /**
     * Loads a DeneMatrix64F which has been saved to file using Java binary
     * serialization.
     *
     * @param fileName The file being loaded.
     * @return  DenseMatrix64F
     * @throws IOException
     */
    public static <T extends RealMatrix64F> T loadBin(String fileName)
        throws IOException {
    	//GWT does not support such an operation!
    	throw new UnsupportedOperationException("MatrixIO.loadBin: GWT-EJML does not support loading from file");
    	//
    }

    /**
     * Saves a matrix to disk using in a Column Space Value (CSV) format. For a 
     * description of the format see {@link MatrixIO#loadCSV(String)}.
     *
     * @param A The matrix being saved.
     * @param fileName Name of the file its being saved at.
     * @throws java.io.IOException
     */
    public static void saveCSV( RealMatrix64F A , String fileName )
        throws IOException {
    	//GWT does not support such an operation!
    	throw new UnsupportedOperationException("MatrixIO.saveCSV: GWT-EJML does not support saving from file");
    	//
    }

    /**
     * Reads a matrix in which has been encoded using a Column Space Value (CSV)
     * file format. The number of rows and columns are read in on the first line. Then
     * each row is read in the subsequent lines.
     *
     * @param fileName The file being loaded.
     * @return DenseMatrix64F
     * @throws IOException
     */
    public static DenseMatrix64F loadCSV( String fileName )
        throws IOException {
    	//GWT does not support such an operation!
    	//
    	throw new UnsupportedOperationException("MatrixIO.loadCSV: GWT-EJML does not support loading from file");
    	//
    }

    /**
     * Reads a matrix in which has been encoded using a Column Space Value (CSV)
     * file format.  For a description of the format see {@link MatrixIO#loadCSV(String)}.
     *
     * @param fileName The file being loaded.
     * @param numRows number of rows in the matrix.
     * @param numCols number of columns in the matrix.
     * @return DenseMatrix64F
     * @throws IOException
     */
    public static DenseMatrix64F loadCSV( String fileName , int numRows , int numCols )
        throws IOException
    {
    	//GWT does not support such an operation!
    	//
    	throw new UnsupportedOperationException("MatrixIO.loadCSV: GWT-EJML does not support loading from file");
    	//
    }

    public static void print( PrintStream out , RealMatrix64F mat ) {
        print(out,mat,6,3);
    }

    public static void print(PrintStream out, RealMatrix64F mat , int numChar , int precision ) {
        //String format = "%"+numChar+"."+precision+"f ";
		String format = "####000.000 ";

        print(out, mat,format);
    }

    public static void print(PrintStream out , RealMatrix64F mat , String format ) {
    	//TODO search the type of the matrix modifying "isAssignableFrom" not supported by GWT jre 
		String type = "--";//ReshapeMatrix64F.class.isAssignableFrom(mat.getClass()) ? "dense" : "dense fixed";

		lg.log(Level.INFO, "Type = "+type+" , numRows = "+mat.getNumRows()+" , numCols = "+mat.getNumCols());
		//format += " ";
		for(int i=0; i<mat.getNumRows(); i++) {
			StringBuffer row= new StringBuffer("");
			for(int j=0; j<mat.getNumCols(); j++) {
				double val = mat.get(i,j);
                BigDecimal bd = new BigDecimal(val);
                bd = bd.setScale(3, RoundingMode.HALF_UP);
                
        		if(bd.doubleValue()>=0)
        			row.append(" +"+bd+" ");
        		else
        			row.append(" "+bd+" ");
			}
			lg.log(Level.INFO,row.toString());
		}

    }

    public static void print( PrintStream out , RealMatrix64F mat , String format ,
                              int row0 , int row1, int col0 , int col1 ) {
        out.println("Type = submatrix , rows "+row0+" to "+row1+"  columns "+col0+" to "+col1);

        //format += " ";
		for(int i=row0; i<row1; i++) {
			StringBuffer row= new StringBuffer("");
			for(int j=col0; j<col1; j++) {
				double val = mat.get(i,j);
                BigDecimal bd = new BigDecimal(val);
                bd = bd.setScale(3, RoundingMode.HALF_UP);
                
        		if(bd.doubleValue()>=0)
        			row.append(" +"+bd+" ");
        		else
        			row.append(" "+bd+" ");
			}
			lg.log(Level.INFO,row.toString());
		}
    }

    public static void print( PrintStream out , ComplexMatrix64F mat ) {
        print(out,mat,6,3);
    }

    public static void print(PrintStream out, ComplexMatrix64F mat , int numChar , int precision ) {
        String format = "%"+numChar+"."+precision+"f + %"+numChar+"."+precision+"fi";

        print(out, mat,format);
    }

    public static void print(PrintStream out , ComplexMatrix64F mat , String format ) {
		String type = "Dense";

		lg.log(Level.INFO, "Type = Complex "+type+" , numRows = "+mat.getNumRows()+" , numCols = "+mat.getNumCols());
		//format += " ";
        Complex64F c = new Complex64F();

		for(int i=0; i<mat.getNumRows(); i++) {
			StringBuffer row= new StringBuffer("");
			for(int j=0; j<mat.getNumCols(); j++) {
				mat.get(i,j,c);
                BigDecimal bd_real = new BigDecimal(c.real);
                bd_real = bd_real.setScale(3, RoundingMode.HALF_UP);
                BigDecimal bd_imag = new BigDecimal(c.imaginary);
                bd_imag = bd_imag.setScale(3, RoundingMode.HALF_UP);
                
        		if(bd_real.doubleValue()>=0)
        			row.append(" (+"+bd_real+", ");
        		else
        			row.append(" ("+bd_real+", ");

        		if(bd_imag.doubleValue()>=0)
        			row.append(" +"+bd_imag+") ");
        		else
        			row.append(" "+bd_imag+") ");
        		
			}
			lg.log(Level.INFO,row.toString());
		}


    }

//    public static void main( String []args ) {
//        Random rand = new Random(234234);
//        DenseMatrix64F A = RandomMatrices.createRandom(50,70,rand);
//
//        SingularValueDecomposition decomp = DecompositionFactory.svd();
//
//        decomp.decompose(A);
//
//        displayMatrix(A,"Original");
//        displayMatrix(decomp.getU(false),"U");
//        displayMatrix(decomp.getV(false),"V");
//        displayMatrix(decomp.getW(null),"W");
//    }
}
