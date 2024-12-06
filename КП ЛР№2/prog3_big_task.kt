import Currency.EUR
import Currency.UAH
import Currency.USD

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val month: Int, val value: Int, val currency: Currency)
enum class Currency {
    UAH, USD, EUR
}

fun main() {
    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")
    val transactions = listOf(
        Transaction(brian, 2011, 12, 300, UAH),
        Transaction(raoul, 2012, 10, 1000, UAH),
        Transaction(raoul, 2011, 11, 400, USD),
        Transaction(mario, 2012, 9, 710, UAH),
        Transaction(mario, 2012, 7, 700, USD),
        Transaction(alan, 2012, 4, 950, EUR)
    )

    // 1. Знайти усі транзакції за 2011 рік і посортувати за вартістю (від малого до високого)
    val transactions2011 = transactions.filter { it.year == 2011 }
        .sortedBy { it.value }
    println("Transactions from 2011 sorted by value: $transactions2011")

    // 2. У яких унікальних містах працюють трейдери?
    val uniqueCities = transactions.map { it.trader.city }.distinct()
    println("Unique cities where traders work: $uniqueCities")

    // 3. Знайдіть усіх трейдерів із Кембриджа та відсортуйте їх за назвою
    val tradersInCambridge = transactions.filter { it.trader.city == "Cambridge" }
        .map { it.trader }
        .distinct()
        .sortedBy { it.name }
    println("Traders in Cambridge sorted by name: $tradersInCambridge")

    // 4. Поверніть рядок імен усіх трейдерів, відсортованих за алфавітом
    val sortedTradersNames = transactions.map { it.trader.name }
        .distinct()
        .sorted()
        .joinToString(", ")
    println("Traders names sorted alphabetically: $sortedTradersNames")

    // 5. Чи є трейдери в Мілані?
    val tradersInMilan = transactions.any { it.trader.city == "Milan" }
    println("Are there any traders in Milan? $tradersInMilan")

    // 6. Виведіть у консоль всі значення транзакцій від трейдерів, які проживають у Кембриджі
    val transactionsInCambridge = transactions.filter { it.trader.city == "Cambridge" }
    println("Transactions from traders in Cambridge: $transactionsInCambridge")

    // 7. Знайдіть транзакцію з найбільшою вартістю.
    val maxTransaction = transactions.maxByOrNull { it.value }
    println("Transaction with the highest value: $maxTransaction")

    // 8. Згрупуйте всі транзакції за валютою.
    val transactionsGroupedByCurrency = transactions.groupBy { it.currency }
    println("Transactions grouped by currency: $transactionsGroupedByCurrency")

    // 9. Знайдіть суму транзакцій у гривнях.
    // Для цього потрібно врахувати курс обміну. Ми припускаємо, що:
    // 1 USD = 41 UAH
    // 1 EUR = 44 UAH
    val totalInUAH = transactions.sumOf { transaction ->
        when (transaction.currency) {
            UAH -> transaction.value
            USD -> transaction.value * 41
            EUR -> transaction.value * 44
        }
    }
    println("Total transactions in UAH: $totalInUAH")

    // 10. Створіть рядок, у якому буде виведена послідовність транзакцій відсортована за датою
    val sortedTransactions = transactions.sortedWith(compareBy({ it.year }, { it.month }))
    val transactionSequence = sortedTransactions
        .mapIndexed { index, transaction -> 
            "${index + 1}. ${transaction.month}-${transaction.year}: ${transaction.value} ${transaction.currency} " 
        }
        .joinToString("-> ")
    println("Sorted transactions sequence: $transactionSequence")
}