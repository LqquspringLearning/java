package com.cultivation.javaBasicExtended.matrixMultiplication;

import java.util.Arrays;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {
        return storage.length;
    }

    public int columns() {
        return storage[0].length;
    }

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        if (matrixArray == null)
            throw new IllegalArgumentException("Raw matrix is null");
        if (matrixArray.length == 0) {
            throw new IllegalArgumentException("Raw matrix contains 0 row");
        }
        int columCount = matrixArray[0].length;
        for (int[] rowArray : matrixArray) {
            if (rowArray == null) {
                throw new IllegalArgumentException("Raw matrix contains null row");
            }
            if (rowArray.length == 0) {
                throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
            }
            if (rowArray.length != columCount) {
                throw new IllegalArgumentException("Raw matrix is not rectangle");
            }

        }

        storage = matrixArray;
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null)
            throw new IllegalArgumentException();
        //左边列等于右边行
        if(left.storage[0].length!=right.storage.length){
            throw new IllegalArgumentException();
        }

        int rowCount = left.storage.length;
        int columnCount = right.storage[0].length;

        int[][] result = new int[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int tempResult = 0;
                for (int k = 0; k < right.storage.length; k++) {
                    tempResult += left.storage[i][k] * right.storage[k][j];
                }
                result[i][j] = tempResult;
            }
        }
        return new Matrix(result);

        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) {
            throw new IllegalArgumentException();
        }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (Matrix.class != obj.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
                .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
