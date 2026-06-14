package exercises

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object Users : IntIdTable("users") {
    val name      = varchar("name", 100)
    val email     = varchar("email", 200).uniqueIndex()
    val age       = integer("age").default(0)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

object Authors : IntIdTable("authors") {
    val name  = varchar("name", 100)
    val email = varchar("email", 200).uniqueIndex()
    val bio   = text("bio").nullable()
}

object Posts : IntIdTable("posts") {
    val title       = varchar("title", 200)
    val content     = text("content")
    val authorId    = reference("author_id", Authors, onDelete = ReferenceOption.CASCADE)
    val publishedAt = datetime("published_at").defaultExpression(CurrentDateTime)
}

object Tags : IntIdTable("tags") {
    val name = varchar("name", 50).uniqueIndex()
}

object PostTags : Table("post_tags") {
    val postId = reference("post_id", Posts, onDelete = ReferenceOption.CASCADE)
    val tagId  = reference("tag_id",  Tags,  onDelete = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(postId, tagId)
}
