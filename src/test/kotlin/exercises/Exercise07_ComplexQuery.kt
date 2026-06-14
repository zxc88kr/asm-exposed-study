package exercises

/**
 * Exercise 07: 복잡한 쿼리
 *
 * 슬라이드 54~64 참고
 * Tables.kt의 Authors, Posts 테이블 사용
 *
 * TODO:
 * 1. initDatabase() + drop(Posts, Authors) + create(Authors, Posts)
 *
 * 2. 데이터 삽입 — 슬라이드 62 조건 맞추기
 *    - 김작가: 포스트 3건
 *    - 이작가: 포스트 2건
 *    - 박작가: 포스트 1건
 *    val kim = Authors.insertAndGetId { it[Authors.name] = "김작가"; it[Authors.email] = "kim@example.com" }
 *    Posts.insert { it[Posts.title] = "Kotlin 1편"; it[Posts.content] = "내용"; it[Posts.authorId] = kim }
 *
 * 3. GROUP BY — 저자별 포스트 수 집계 (슬라이드 55)
 *    (Authors innerJoin Posts)
 *        .select(Authors.name, Posts.id.count())
 *        .groupBy(Authors.id, Authors.name)
 *        .orderBy(Posts.id.count(), SortOrder.DESC)
 *        .forEach { println("${it[Authors.name]}: ${it[Posts.id.count()]}편") }
 *
 * 4. HAVING — 3편 이상인 저자만 필터링 (슬라이드 56)
 *    .having { Posts.id.count() greaterEq 3 }
 *
 * 5. CASE WHEN — 포스트 수 등급 부여 (슬라이드 57)
 *    val postCount = Posts.id.count()
 *    val grade = Case()
 *        .When(postCount greaterEq 3, stringLiteral("활발"))
 *        .When(postCount greaterEq 2, stringLiteral("보통"))
 *        .Else(stringLiteral("신인"))
 *    (Authors innerJoin Posts)
 *        .select(Authors.name, postCount, grade)
 *        .groupBy(Authors.id, Authors.name)
 *        .forEach { println("${it[Authors.name]}: ${it[grade]} (${it[postCount]}편)") }
 *
 * 6. SubQuery — 포스트 있는 저자만 조회 (슬라이드 58)
 *    val activeAuthorIds = Posts.select(Posts.authorId).withDistinct()
 *    Authors.selectAll()
 *        .where { Authors.id inSubQuery activeAuthorIds }
 *        .forEach { println(it[Authors.name]) }
 *
 * 힌트:
 *   import org.jetbrains.exposed.v1.core.Case
 *   import org.jetbrains.exposed.v1.core.SortOrder
 *   import org.jetbrains.exposed.v1.core.stringLiteral
 *   import org.jetbrains.exposed.v1.jdbc.SchemaUtils
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

fun main() {
    // TODO 1: initDatabase() + drop/create

    // TODO 2: 데이터 삽입 (김:3편, 이:2편, 박:1편)

    // TODO 3: GROUP BY — 저자별 포스트 수

    // TODO 4: HAVING — 3편 이상 저자만

    // TODO 5: CASE WHEN — 등급 부여

    // TODO 6: SubQuery — 활동 저자 조회

    println("TODO: 복잡한 쿼리 구현하기")
}
