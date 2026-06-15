package exercises

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database

/**
 * DB 연결 유틸리티 — Exercise01에서 배운 HikariCP 설정을 기반으로 합니다.
 * Exercise02부터는 initDatabase()를 호출하면 됩니다.
 * 슬라이드 13 참고
 */
fun initDatabase() {
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
}
