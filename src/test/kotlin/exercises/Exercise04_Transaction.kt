package exercises

/**
 * Exercise 04: 트랜잭션
 *
 * 슬라이드 35~39 참고
 * Tables.kt의 Users 테이블 사용
 *
 * TODO:
 * 1. initDatabase() + SchemaUtils.drop(Users) + SchemaUtils.create(Users)
 *
 * 2. 예외로 인한 자동 ROLLBACK 확인 (슬라이드 36)
 *    try {
 *        transaction {
 *            Users.insert { ... }  // 3건 insert
 *            error("강제 예외 발생")  // → 자동 ROLLBACK
 *        }
 *    } catch (e: Exception) { println("실패: ${e.message}") }
 *    transaction { println("롤백 후 행 수: ${Users.selectAll().count()}") }  // 0 확인
 *
 * 3. 명시적 rollback() 확인 (슬라이드 36)
 *    transaction {
 *        Users.insert { ... }
 *        rollback()
 *        return@transaction
 *    }
 *    transaction { println("rollback 후 행 수: ${Users.selectAll().count()}") }  // 0 확인
 *
 * 4. 정상 커밋 확인 (슬라이드 35)
 *    transaction { Users.insert { ... }; Users.insert { ... } }
 *    transaction { println("커밋 후 행 수: ${Users.selectAll().count()}") }  // 2 확인
 *
 * 5. ExposedSQLException 처리 (슬라이드 38)
 *    try {
 *        transaction { Users.insert { it[email] = "hong@example.com" /* 중복 */ } }
 *    } catch (e: ExposedSQLException) {
 *        when (e.sqlState) { "23505" -> println("중복 데이터 오류") }
 *    }
 *
 * 6. 중첩 트랜잭션 (슬라이드 37) — useNestedTransactions
 *    TransactionManager.defaultDatabase?.useNestedTransactions = true
 *    transaction {
 *        Users.insert { ... }       // 외부
 *        transaction { ...; rollback() }  // 내부만 롤백
 *        // 외부는 계속 진행 → COMMIT
 *    }
 *
 * 힌트:
 *   import org.jetbrains.exposed.v1.exceptions.ExposedSQLException
 *   import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
 *   import org.jetbrains.exposed.v1.jdbc.transactions.transaction
 */

fun main() {
    // TODO 1: initDatabase() + drop/create Users

    // TODO 2: 예외 → 자동 ROLLBACK

    // TODO 3: 명시적 rollback()

    // TODO 4: 정상 커밋

    // TODO 5: ExposedSQLException (unique 위반)

    // TODO 6: 중첩 트랜잭션 (useNestedTransactions)

    println("TODO: 트랜잭션 구현하기")
}
