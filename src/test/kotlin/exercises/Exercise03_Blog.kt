package exercises

/**
 * Exercise 03: 블로그 포스트 CRUD
 *
 * 슬라이드 28~33 참고
 * Tables.kt의 Authors, Posts 테이블 사용 (먼저 Exercise02 완료 필요)
 *
 * TODO:
 * 1. initDatabase() + 테이블 초기화
 *    SchemaUtils.drop(Posts, Authors)   // FK 때문에 Posts 먼저!
 *    SchemaUtils.create(Authors, Posts)
 *
 * 2. batchInsert — 저자 2명 (Authors 테이블, 슬라이드 31)
 *    val authorIds = Authors.batchInsert(authorData) { (name, email) ->
 *        this[Authors.name]  = name
 *        this[Authors.email] = email
 *    }.map { it[Authors.id] }
 *
 * 3. batchInsert — 포스트 6건 (Posts 테이블)
 *    Posts.batchInsert(postData) { (title, authorId, content) ->
 *        this[Posts.title]    = title
 *        this[Posts.content]  = content
 *        this[Posts.authorId] = authorId
 *    }
 *    println("✅ 포스트 6건 batchInsert 완료")
 *
 * 4. 전체 포스트 조회 (슬라이드 29)
 *    Posts.selectAll().forEach { println("[${it[Posts.id]}] ${it[Posts.title]}") }
 *
 * 5. where 필터링 — 특정 저자 포스트만 (슬라이드 29)
 *    Posts.selectAll().where { Posts.authorId eq kimId }.forEach { ... }
 *
 * 6. 포스트 제목 Update (슬라이드 30)
 *    Posts.update({ Posts.title eq "Kotlin 입문" }) { it[Posts.title] = "Kotlin 입문 (개정판)" }
 *
 * 7. 페이지네이션 — page=2, pageSize=3 (슬라이드 32)
 *    Posts.selectAll()
 *        .orderBy(Posts.publishedAt, SortOrder.DESC)
 *        .limit(pageSize)
 *        .offset(((page - 1) * pageSize).toLong())
 *
 * 힌트:
 *   import org.jetbrains.exposed.v1.core.SortOrder
 *   import org.jetbrains.exposed.v1.jdbc.SchemaUtils
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

fun main() {
    // TODO 1: initDatabase() + drop/create

    // TODO 2: Authors batchInsert (2명)

    // TODO 3: Posts batchInsert (6건 — 저자당 3건씩)

    // TODO 4: 전체 포스트 selectAll 조회

    // TODO 5: where 조건으로 특정 저자 포스트 필터링

    // TODO 6: 포스트 제목 Update

    // TODO 7: 페이지네이션 (page=2, pageSize=3)

    println("TODO: 블로그 CRUD 구현하기")
}
