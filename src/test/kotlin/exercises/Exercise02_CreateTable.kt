package exercises

import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    initDatabase()

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Users, Authors, Posts, Tags, PostTags)
        println("✅ 테이블 생성 완료!")
    }
}
