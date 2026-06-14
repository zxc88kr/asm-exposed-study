# Exposed 완전정복 — 실습 프로젝트

AI·SW 마에스트로 특강 실습용 프로젝트입니다.

---

## 환경 설정

### 1. 사전 준비
- Docker Desktop 실행 중이어야 합니다
- IntelliJ IDEA (Community Edition 가능)
- JDK 17 이상

### 2. Docker로 DB 실행
```bash
docker-compose up -d
```

### 3. DB 접속 정보
| 항목 | 값 |
|------|-----|
| Host | localhost |
| Port | **3232** |
| Database | exposed_db |
| User | postgres |
| Password | password |

### 4. IntelliJ에서 열기
1. `File → Open` → 이 폴더 선택
2. Gradle sync 완료 대기
3. `src/test/kotlin/exercises/` 폴더에서 실습 시작

---

## 실습 순서

| 파일 | 내용 | 슬라이드 |
|------|------|---------|
| `Exercise01_Setup.kt`      | DB 연결 (HikariCP)      | 13~15 |
| `Exercise02_CreateTable.kt`| 테이블 정의 (IntIdTable) | 17~26 |
| `Exercise03_Blog.kt`       | CRUD + batchInsert + 페이지네이션 | 28~33 |
| `Exercise04_Transaction.kt`| 트랜잭션 + 예외 처리     | 35~40 |
| `Exercise05_OneToMany.kt`  | 1:N 관계 + JOIN         | 42~50 |
| `Exercise06_ManyToMany.kt` | N:M 관계 + 중간 테이블  | 46~53 |
| `Exercise07_ComplexQuery.kt`| GROUP BY / HAVING / SubQuery | 54~64 |

---

## 실행 방법

각 파일의 `main()` 함수를 IntelliJ에서 직접 실행하세요.

```
우클릭 → Run 'ExerciseXX...' 또는 좌측 ▶ 버튼 클릭
```

---

## 패키지 경로 주의

Exposed 1.2.0은 패키지가 `v1`으로 변경되었습니다.

```kotlin
// ❌ 구버전 (컴파일 에러)
import org.jetbrains.exposed.sql.*

// ✅ 신버전 (1.2.0+)
import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
```

---

## 트러블슈팅

### Docker 포트 충돌
```bash
# 다른 프로세스가 3232 사용 중인지 확인
netstat -ano | findstr 3232
```

### DB 연결 실패
```bash
# 컨테이너 상태 확인
docker ps
docker-compose logs postgres
```

### Gradle Sync 실패
- File → Invalidate Caches → Restart
- JDK 17 설정 확인: File → Project Structure → SDK
