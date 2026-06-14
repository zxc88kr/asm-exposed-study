package exercises

import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

// 슬라이드 Sec 08: Spring Boot 연동 패턴 실습
//
// [Spring Boot 실제 환경]
//   @Repository — Spring이 Bean으로 등록, @Transactional이 transaction{} 역할 담당
//   @Service    — @Transactional 선언 → SpringTransactionManager가 트랜잭션 관리
//
// [이 파일] Spring 없이 동일 패턴을 transaction{}으로 구현 (standalone 실행 가능)
// Tables.kt의 Posts 공유 테이블 사용

// ── DTO ───────────────────────────────────────────────────────
data class PostRecord(val id: Int, val title: String, val content: String)

// ── Repository ─────────────────────────────────────────────────
// Spring Boot: @Repository + @Transactional 범위에서 transaction{} 불필요
class PostRepo {
    fun findAll(): List<PostRecord> = transaction {
        Posts.selectAll().map { PostRecord(it[Posts.id].value, it[Posts.title], it[Posts.content]) }
    }

    fun findById(id: Int): PostRecord? = transaction {
        Posts.selectAll().where { Posts.id eq id }
            .map { PostRecord(it[Posts.id].value, it[Posts.title], it[Posts.content]) }
            .firstOrNull()
    }

    fun save(title: String, content: String): Int = transaction {
        Posts.insertAndGetId { it[Posts.title] = title; it[Posts.content] = content }.value
    }

    fun update(id: Int, title: String): Boolean = transaction {
        Posts.update({ Posts.id eq id }) { it[Posts.title] = title } > 0
    }

    fun delete(id: Int): Boolean = transaction {
        Posts.deleteWhere { Posts.id eq id } > 0
    }
}

// ── Service ─────────────────────────────────────────────────────
// Spring Boot: @Service + @Transactional / @Transactional(readOnly = true)
class PostService(private val repo: PostRepo) {
    fun createPost(title: String, content: String): Int = repo.save(title, content)
    fun getPost(id: Int): PostRecord? = repo.findById(id)        // Spring: @Transactional(readOnly = true)
    fun getAllPosts(): List<PostRecord> = repo.findAll()          // Spring: @Transactional(readOnly = true)
    fun updatePost(id: Int, title: String): Boolean = repo.update(id, title)
    fun deletePost(id: Int): Boolean = repo.delete(id)
}

// ── Query 객체 유출 패턴 시연 ────────────────────────────────────
fun badPattern(): List<PostRecord> {
    val query = transaction {
        Posts.selectAll()  // ❌ Query 객체만 반환 — SQL 미실행 상태
    }
    println("❌ badPattern: transaction 종료 후 iterate → 커넥션 이미 반납됨")
    // query.map { ... }  ← 실행하면 "Connection is closed" 오류
    return emptyList()
}

fun goodPattern(): List<PostRecord> = transaction {
    Posts.selectAll()
        .map { PostRecord(it[Posts.id].value, it[Posts.title], it[Posts.content]) }
    // ✅ SQL 실행 + DTO 변환을 transaction 안에서 완료
}

// ── Main ────────────────────────────────────────────────────────
fun main() {
    initDatabase()

    transaction {
        SchemaUtils.drop(Posts, Authors)
        SchemaUtils.create(Authors, Posts)
        val authorId = Authors.insertAndGetId { it[Authors.name] = "테스트 작가"; it[Authors.email] = "test@example.com" }
        Posts.insert { it[Posts.title] = "첫 번째 포스트"; it[Posts.content] = "내용"; it[Posts.authorId] = authorId }
        Posts.insert { it[Posts.title] = "두 번째 포스트"; it[Posts.content] = "내용"; it[Posts.authorId] = authorId }
        println("✅ 초기 데이터 삽입 완료")
    }

    val service = PostService(PostRepo())

    println("\n=== 전체 조회 ===")
    service.getAllPosts().forEach { println("  ${it.id}: ${it.title}") }

    println("\n=== 신규 생성 ===")
    val newId = service.createPost("새 포스트", "내용")
    println("  생성된 ID: $newId")

    println("\n=== ID 조회 ===")
    println("  ${service.getPost(newId)}")

    println("\n=== 수정 ===")
    service.updatePost(newId, "수정된 포스트")
    println("  수정 후: ${service.getPost(newId)}")

    println("\n=== 삭제 ===")
    service.deletePost(newId)
    println("  삭제 후: ${service.getPost(newId)}")

    println("\n=== Query 객체 유출 패턴 ===")
    badPattern()
    println("✅ goodPattern: ${goodPattern().size}건 조회 성공")
}
