package exercises

import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.like
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    initDatabase()

    transaction {
//        SchemaUtils.drop(Posts, Authors)
//        SchemaUtils.create(Authors, Posts)

        val authorData = listOf(
            "김작가" to "kim@example.com",
            "이작가" to "lee@example.com",
        )
        val authorIds = Authors.batchInsert(authorData) { (name, email) ->
            this[Authors.name]  = name
            this[Authors.email] = email
        }.map { it[Authors.id] }

        val postData = listOf(
            Triple("Kotlin 입문",          authorIds[0], "Kotlin 기초부터 배우는 여정"),
            Triple("Kotlin 심화",          authorIds[0], "코루틴, 확장함수, 위임 패턴"),
            Triple("Kotlin 실전 프로젝트", authorIds[0], "Spring Boot + Exposed 연동"),
            Triple("Spring Boot 입문",     authorIds[1], "Spring Boot 3.x 시작하기"),
            Triple("Spring Data JPA",      authorIds[1], "JPA 완전정복 가이드"),
            Triple("Exposed 완전정복",     authorIds[1], "Exposed ORM 심층 분석"),
        )
        Posts.batchInsert(postData) { (title, authorId, content) ->
            this[Posts.title]    = title
            this[Posts.content]  = content
            this[Posts.authorId] = authorId
        }
        println("✅ 포스트 6건 batchInsert 완료")
    }

    println("\n=== 전체 포스트 조회 ===")
    transaction {
        Posts.selectAll().forEach {
            println("  [${it[Posts.id]}] ${it[Posts.title]}")
        }
    }

    println("\n=== 김작가 포스트만 where 필터링 ===")
    transaction {
        val kimId = Authors.selectAll()
            .where { Authors.name eq "김작가" }
            .first()[Authors.id]
        Posts.selectAll()
            .where { Posts.authorId eq kimId }
            .forEach { println("  ${it[Posts.title]}") }
    }

    println("\n=== 포스트 제목 Update ===")
    transaction {
        Posts.update({ Posts.title eq "Kotlin 입문" }) {
            it[Posts.title] = "Kotlin 입문 (개정판)"
        }
        println("  'Kotlin 입문' → 'Kotlin 입문 (개정판)' 수정 완료")
        Posts.selectAll()
            .where { Posts.title like "Kotlin%" }
            .forEach { println("  ${it[Posts.title]}") }
    }

    println("\n=== 페이지네이션 (page=2, pageSize=3) ===")
    transaction {
        val pageSize = 3
        val page     = 2
        val total    = Posts.selectAll().count()
        val items    = Posts.selectAll()
            .orderBy(Posts.publishedAt, SortOrder.DESC)
            .limit(pageSize)
            .offset(((page - 1) * pageSize).toLong())
            .map { it[Posts.title] }
        println("  전체: ${total}건  |  2페이지: ${items.size}건")
        items.forEach { println("  - $it") }
    }
}
