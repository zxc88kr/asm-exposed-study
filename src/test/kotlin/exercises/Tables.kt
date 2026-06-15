package exercises

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

/**
 * 공유 테이블 정의 — Exercise02에서 아래 TODO를 완성하세요.
 *
 * 슬라이드 17, 19, 22, 23 참고
 */

// TODO (Exercise02): CRUD · 트랜잭션 실습용 테이블
// 슬라이드 17: IntIdTable — PK 자동 포함
object Users : IntIdTable("users") {
    // TODO: val name      = varchar("name", 100)
    // TODO: val email     = varchar("email", 200).uniqueIndex()
    // TODO: val age       = integer("age").default(0)
    // TODO: val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

// TODO (Exercise02): 블로그 · 관계 매핑 실습용 테이블
// 슬라이드 23: reference() + ReferenceOption
object Authors : IntIdTable("authors") {
    // TODO: val name  = varchar("name", 100)
    // TODO: val email = varchar("email", 200).uniqueIndex()
    // TODO: val bio   = text("bio").nullable()
}

object Posts : IntIdTable("posts") {
    // TODO: val title       = varchar("title", 200)
    // TODO: val content     = text("content")
    // TODO: val authorId    = reference("author_id", Authors, onDelete = ReferenceOption.CASCADE)
    // TODO: val publishedAt = datetime("published_at").defaultExpression(CurrentDateTime)
}

// TODO (Exercise06): N:M 실습용 테이블
object Tags : IntIdTable("tags") {
    // TODO: val name = varchar("name", 50).uniqueIndex()
}

// 슬라이드 46: 중간 테이블 — IntIdTable이 아닌 Table + 복합 PK
object PostTags : Table("post_tags") {
    // TODO: val postId = reference("post_id", Posts, onDelete = ReferenceOption.CASCADE)
    // TODO: val tagId  = reference("tag_id",  Tags,  onDelete = ReferenceOption.CASCADE)
    // TODO: override val primaryKey = PrimaryKey(postId, tagId)
}
