package exercises

/**
 * Exercise 01: DB 연결 확인
 *
 * 슬라이드 13~16 참고
 *
 * TODO:
 * 1. HikariConfig().apply { ... } 로 DB 연결 설정
 *    - jdbcUrl              = "jdbc:postgresql://localhost:3232/exposed_db"
 *    - driverClassName      = "org.postgresql.Driver"
 *    - username             = "postgres"
 *    - password             = "password"
 *    - maximumPoolSize      = 10
 *    - isAutoCommit         = false
 *    - transactionIsolation = "TRANSACTION_REPEATABLE_READ"
 *
 * 2. Database.connect(HikariDataSource(config))
 *
 * 3. transaction { } 블록 안에서:
 *    - addLogger(StdOutSqlLogger)  // 슬라이드 16
 *    - println("✅ DB 연결 성공!")
 *
 * 힌트 (import):
 *   import com.zaxxer.hikari.HikariConfig
 *   import com.zaxxer.hikari.HikariDataSource
 *   import org.jetbrains.exposed.v1.core.StdOutSqlLogger
 *   import org.jetbrains.exposed.v1.core.addLogger
 *   import org.jetbrains.exposed.v1.jdbc.Database
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

fun main() {
    // TODO 1: HikariConfig 설정

    // TODO 2: Database.connect(HikariDataSource(config))

    // TODO 3: transaction { addLogger(...); println("✅ DB 연결 성공!") }

    println("TODO: DB 연결 구현하기")
}
