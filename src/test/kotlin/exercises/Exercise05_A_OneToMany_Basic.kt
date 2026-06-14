package exercises

import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

// 슬라이드 43: 기초 1:N JOIN 실습 — Authors + Posts, INNER JOIN
// Tables.kt의 Authors, Posts 공유 테이블 사용

fun main() {
    initDatabase()

    transaction {
        SchemaUtils.drop(Posts, Authors)
        SchemaUtils.create(Authors, Posts)

        // 저자 2명 INSERT — insertAndGetId로 EntityID<Int> 반환
        val kimId = Authors.insertAndGetId { it[Authors.name] = "김작가"; it[Authors.email] = "kim@example.com" }
        val leeId = Authors.insertAndGetId { it[Authors.name] = "이작가"; it[Authors.email] = "lee@example.com" }

        // 각 저자에 포스트 2건씩 INSERT
        listOf("Kotlin 입문", "Kotlin 심화").forEach { t ->
            Posts.insert { it[Posts.title] = t; it[Posts.content] = "내용"; it[Posts.authorId] = kimId }
        }
        listOf("Spring Boot 입문", "JPA 완전정복").forEach { t ->
            Posts.insert { it[Posts.title] = t; it[Posts.content] = "내용"; it[Posts.authorId] = leeId }
        }
        println("✅ 저자 2명, 포스트 4건 삽입 완료")
    }

    // INNER JOIN — 저자별 포스트 전체 조회
    println("\n=== INNER JOIN — 저자별 포스트 전체 조회 ===")
    transaction {
        (Authors innerJoin Posts)
            .selectAll()
            .forEach { println("  [${it[Authors.name]}] ${it[Posts.title]}") }
    }

    // where 조건 — 특정 저자 포스트만 필터링
    println("\n=== 김작가 포스트만 조회 ===")
    transaction {
        (Authors innerJoin Posts)
            .selectAll()
            .where { Authors.name eq "김작가" }
            .forEach { println("  ${it[Posts.title]}") }
    }
}
