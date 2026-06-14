package exercises

/**
 * Exercise 06: N:M 관계 매핑
 *
 * 슬라이드 46~53 참고
 * Tables.kt의 Authors, Posts, Tags, PostTags 테이블 사용
 * (Tables.kt의 Tags, PostTags TODO 먼저 완성 필요)
 *
 * TODO:
 * 1. initDatabase() + drop(PostTags, Tags, Posts, Authors) + create(Authors, Posts, Tags, PostTags)
 *    ※ 순서 주의: FK 참조하는 테이블 먼저 drop, 참조되는 테이블 먼저 create
 *
 * 2. 저자 2명 + 포스트 3건 삽입
 *    val kimId = Authors.insertAndGetId { ... }
 *    val p1 = Posts.insertAndGetId { it[Posts.title] = "Kotlin 입문"; ... it[Posts.authorId] = kimId }
 *
 * 3. 태그 3개 생성 (슬라이드 46)
 *    val kotlinTag  = Tags.insertAndGetId { it[Tags.name] = "Kotlin" }
 *    val exposedTag = Tags.insertAndGetId { it[Tags.name] = "Exposed" }
 *    val backendTag = Tags.insertAndGetId { it[Tags.name] = "Backend" }
 *
 * 4. N:M 연결 데이터 삽입 (슬라이드 46)
 *    - 포스트1 → Kotlin, Backend
 *    - 포스트2 → Kotlin, Exposed
 *    - 포스트3 → Backend
 *    PostTags.insert { it[PostTags.postId] = p1; it[PostTags.tagId] = kotlinTag }
 *
 * 5. 포스트별 태그 목록 조회 — triple join (슬라이드 47)
 *    (Posts innerJoin PostTags innerJoin Tags)
 *        .select(Posts.title, Tags.name)
 *        .orderBy(Posts.title)
 *        .forEach { println("[${it[Posts.title]}] 태그: ${it[Tags.name]}") }
 *
 * 6. 특정 태그("Kotlin") 포스트 조회 (슬라이드 47)
 *    (Tags innerJoin PostTags innerJoin Posts)
 *        .select(Tags.name, Posts.title)
 *        .where { Tags.name eq "Kotlin" }
 *        .forEach { println("[${it[Tags.name]}] ${it[Posts.title]}") }
 *
 * 힌트:
 *   import org.jetbrains.exposed.v1.jdbc.SchemaUtils
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

fun main() {
    // TODO 1: initDatabase() + drop/create (순서 중요!)

    // TODO 2: 저자 2명 + 포스트 3건 삽입

    // TODO 3: 태그 3개 생성 (Kotlin, Exposed, Backend)

    // TODO 4: PostTags N:M 연결 삽입

    // TODO 5: 포스트별 태그 triple join 조회

    // TODO 6: 'Kotlin' 태그 포스트 조회

    println("TODO: N:M 관계 구현하기")
}
