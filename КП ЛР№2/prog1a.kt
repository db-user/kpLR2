fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    val sumOfSquares = numbers.map { it * it }.sum()
    println(sumOfSquares)
}