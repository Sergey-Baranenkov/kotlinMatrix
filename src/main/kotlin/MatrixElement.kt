import kotlin.math.pow

interface MatrixElement <T> {
    val a: T
    fun create(arg: T) : MatrixElement<T>
    fun add(b : MatrixElement<T>) : MatrixElement<T>
    fun div (b: MatrixElement<T>) : MatrixElement<T>
    fun mul (b: MatrixElement<T>) : MatrixElement<T>
    fun sub (b: MatrixElement<T>) : MatrixElement<T>
    fun zero(): T
    override fun toString(): String
}

data class ComplexElement (val real: Double, val imaginary: Double)

class RealNumber (override val a: Double): MatrixElement<Double> {
    override fun create(arg: Double): MatrixElement<Double> {
        return RealNumber(arg)
    }
    override fun add(b: MatrixElement<Double>):  MatrixElement<Double> {
        return RealNumber(a + b.a)
    }

    override fun div(b: MatrixElement<Double>): MatrixElement<Double> {
        return RealNumber(a / b.a)
    }

    override fun mul(b: MatrixElement<Double>): MatrixElement<Double> {
        return RealNumber(a * b.a)
    }

    override fun sub(b: MatrixElement<Double>): MatrixElement<Double> {
        return RealNumber(a - b.a)
    }

    override fun zero() = .0

    override fun toString() : String {
        return a.toString()
    }
}

class ComplexNumber (override val a: ComplexElement): MatrixElement<ComplexElement> {
    override fun add(b: MatrixElement<ComplexElement>): MatrixElement<ComplexElement> {
        return ComplexNumber(ComplexElement(a.real + b.a.real, a.imaginary + b.a.imaginary))
    }

    override fun div(b: MatrixElement<ComplexElement>): MatrixElement<ComplexElement> {
        return ComplexNumber(ComplexElement(
            (a.real * b.a.real + a.imaginary * b.a.imaginary) / (b.a.real.pow(2) + b.a.imaginary.pow(2)),
            (a.imaginary * b.a.real - a.real * b.a.imaginary) / (b.a.real.pow(2) + b.a.imaginary.pow(2))
        ))
    }

    override fun mul(b: MatrixElement<ComplexElement>): MatrixElement<ComplexElement> {
        return ComplexNumber(ComplexElement(
            a.real * b.a.real - a.imaginary * b.a.imaginary,
            a.real * b.a.imaginary + a.imaginary * b.a.real
        ))
    }

    override fun sub(b: MatrixElement<ComplexElement>): MatrixElement<ComplexElement> {
        return ComplexNumber(ComplexElement(a.real - b.a.real, a.imaginary - b.a.imaginary))
    }

    override fun zero() = ComplexElement(.0,.0)
    override fun create(arg: ComplexElement): MatrixElement<ComplexElement> {
        return ComplexNumber(arg)
    }

    override fun toString() : String {
        return "${a.real} + ${a.imaginary}(i)"
    }
}