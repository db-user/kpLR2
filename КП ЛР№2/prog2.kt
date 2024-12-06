fun main() {
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(3, 4)
    val pairs = list1.flatMap { a -> list2.map { b -> arrayOf(a, b) } }    
    pairs.forEach { println(it.joinToString(", ")) }
}