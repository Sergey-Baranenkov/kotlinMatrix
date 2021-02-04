class Matrix <T> (val matrix: ArrayList<ArrayList<MatrixElement<T>>>){
    fun add(b: Matrix<T>) : Matrix<T> {
        val resMatrix = ArrayList<ArrayList<MatrixElement<T>>>()
        matrix.forEachIndexed{i, row ->
            val curRow = ArrayList<MatrixElement<T>>()
            row.forEachIndexed{j, col ->
                curRow.add(col.add(b.matrix[i][j]))
            }
            resMatrix.add(curRow)
        }
        return Matrix(resMatrix)
    }

    fun multiply(b: Matrix<T>,): Matrix<T> {
        val ar = matrix.size
        val bc = b.matrix[0].size
        val ac = matrix[0].size
        val zero = matrix[0][0].create(matrix[0][0].zero())

        val res = ArrayList<ArrayList<MatrixElement<T>>>()
        for (i in 0 until ar) {
            val row = ArrayList<MatrixElement<T>>()
            for (j in 0 until bc) {
                row.add(zero)
                for (k in 0 until ac) {
                    row[j] = row[j].add(matrix[i][k].mul(b.matrix[k][j]))
                }
            }
            res.add(row)
        }

        return Matrix(res)
    }

    fun transpose(): Matrix<T> {
        val resMatrix = ArrayList<ArrayList<MatrixElement<T>>>()
        for (j in 0 until matrix[0].size) {
            val curRow = ArrayList<MatrixElement<T>>()
            for (i in 0 until matrix.size) {
                curRow.add(matrix[i][j])
            }
            resMatrix.add(curRow)
        }
        return Matrix(resMatrix)
    }

    fun print() {
        println()
        for (row in matrix){
            val currRow = ArrayList<String>()
            for (col in row) {
                currRow.add(col.toString())
            }
            println(currRow)
        }
        println()
    }
}