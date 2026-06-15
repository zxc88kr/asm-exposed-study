package exercises



/**
 * Exercise 02: 테이블 정의 + SchemaUtils.create()
 *
 * 슬라이드 17~26 참고
 *
 * [핵심] Tables.kt 파일의 TODO 섹션을 먼저 완성하세요!
 *   - Users    : name, email(uniqueIndex), age(default=0), createdAt(datetime)
 *   - Authors  : name, email(uniqueIndex), bio(nullable)
 *   - Posts    : title, content, authorId(FK→Authors·CASCADE), publishedAt(datetime)
 *   - Tags     : name(uniqueIndex)
 *   - PostTags : postId(FK), tagId(FK), 복합PK
 *
 * TODO (이 파일):
 * 1. initDatabase() 호출
 * 2. transaction { } 안에서:
 *    - addLogger(StdOutSqlLogger)                              // 슬라이드 16: DDL 로그 확인
 *    - SchemaUtils.create(Users, Authors, Posts, Tags, PostTags)  // 슬라이드 24
 *    - println("✅ 테이블 생성 완료!")
 *
 * 힌트:
 *   import org.jetbrains.exposed.v1.core.StdOutSqlLogger
 *   import org.jetbrains.exposed.v1.core.addLogger
 *   import org.jetbrains.exposed.v1.jdbc.SchemaUtils
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

fun main() {
    // TODO 1: initDatabase()

    // TODO 2: transaction {
    //     addLogger(StdOutSqlLogger)
    //     SchemaUtils.create(Users, Authors, Posts, Tags, PostTags)
    //     println("✅ 테이블 생성 완료!")
    // }

    println("TODO: 테이블 생성 구현하기")
}
