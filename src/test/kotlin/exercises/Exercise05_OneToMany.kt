package exercises

/**
 * Exercise 05: 1:N 관계 매핑
 *
 * 슬라이드 42~50 참고
 * Tables.kt의 Authors, Posts 테이블 사용
 *
 * TODO:
 * 1. initDatabase() + drop(Posts, Authors) + create(Authors, Posts)
 *
 * 2. 저자 3명 삽입 (insertAndGetId 사용, 슬라이드 42)
 *    val kimId  = Authors.insertAndGetId { it[Authors.name] = "김작가"; it[Authors.email] = "..." }
 *    val leeId  = Authors.insertAndGetId { ... }
 *    val parkId = Authors.insertAndGetId { ... }
 *
 * 3. 각 저자당 포스트 2~3건 삽입 (총 6건 이상)
 *    Posts.insert { it[Posts.title] = "..."; it[Posts.content] = "..."; it[Posts.authorId] = kimId }
 *
 * 4. INNER JOIN — 저자별 포스트 목록 조회 (슬라이드 43)
 *    (Authors innerJoin Posts)
 *        .select(Authors.name, Posts.title)
 *        .orderBy(Authors.name)
 *        .forEach { println("[${it[Authors.name]}] ${it[Posts.title]}") }
 *
 * 5. LEFT JOIN — 포스트 없는 저자도 포함 (슬라이드 48)
 *    Authors.insertAndGetId { it[name] = "신작가"; it[email] = "new@example.com" }
 *    (Authors leftJoin Posts)
 *        .select(Authors.name, Posts.title)
 *        .forEach {
 *            val postTitle = it.getOrNull(Posts.title)
 *            println("${it[Authors.name]}: ${postTitle ?: "(포스트 없음)"}")
 *        }
 *
 * 6. N+1 문제 재현 후 .with()로 해결 (슬라이드 44~45)
 *    // DAO 엔티티 정의 (이 파일 상단에)
 *    class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
 *        companion object : IntEntityClass<AuthorEntity>(Authors)
 *        var name  by Authors.name
 *        val posts by PostEntity referrersOn Posts.authorId
 *    }
 *    class PostEntity(id: EntityID<Int>) : IntEntity(id) {
 *        companion object : IntEntityClass<PostEntity>(Posts)
 *        var title  by Posts.title
 *        var author by AuthorEntity referencedOn Posts.authorId
 *    }
 *    // N+1 발생
 *    AuthorEntity.all().forEach { author ->
 *        println("${author.name}: ${author.posts.count()}편")  // 매번 SELECT!
 *    }
 *    // Eager Loading 해결
 *    AuthorEntity.all().with(AuthorEntity::posts).forEach { author ->
 *        println("${author.name}: ${author.posts.count()}편")  // IN 쿼리 1번
 *    }
 *
 * 힌트 (import):
 *   import org.jetbrains.exposed.v1.core.dao.id.EntityID
 *   import org.jetbrains.exposed.v1.dao.IntEntity
 *   import org.jetbrains.exposed.v1.dao.IntEntityClass
 *   import org.jetbrains.exposed.v1.jdbc.SchemaUtils
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

// TODO: DAO 엔티티 class 정의 (AuthorEntity, PostEntity)

fun main() {
    // TODO 1: initDatabase() + drop/create

    // TODO 2: 저자 3명 insertAndGetId

    // TODO 3: 포스트 6건 삽입 (저자당 2~3건)

    // TODO 4: INNER JOIN 조회

    // TODO 5: LEFT JOIN (포스트 없는 저자 포함)

    // TODO 6: N+1 발생 확인

    // TODO 7: .with() Eager Loading 해결

    println("TODO: 1:N 관계 구현하기")
}
