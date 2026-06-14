package exercises

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    val config = HikariConfig().apply {
        jdbcUrl              = "jdbc:postgresql://localhost:3232/exposed_db"
        driverClassName      = "org.postgresql.Driver"
        username             = "postgres"
        password             = "password"
        maximumPoolSize      = 10
        isAutoCommit         = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    }
    Database.connect(HikariDataSource(config))

    transaction {
        addLogger(StdOutSqlLogger)
        println("✅ DB 연결 성공!")
    }
}
