package com.example.quiz_1150720.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.quiz_1150720.entity.QuizSubmission;
@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
	/**
	 * 1. 寫入問卷填寫主檔 (Native SQL)
	 */
	@Modifying
	@Query(value = "INSERT INTO quiz_submission (quiz_id, user_account, submit_time) VALUES (:quizId, :account, NOW())", nativeQuery = true)
	public void insertSubmission(@Param("quizId") Long quizId, @Param("account") String account);
	/**
	 * 2. 取得剛剛 INSERT 進去的自增主檔 ID (Native SQL)<br>
	 * - 在多執行緒、高併發（Concurrency）的情況下，使用 LAST_INSERT_ID() 絕對不會拿到別人的流水號，
	 *   因為在 MySQL 中，LAST_INSERT_ID() 不是全域變數，而是繫結在當前的資料庫連線（Connection）上的，
	 *   它的意思是：「告訴我這個連線剛剛最近一次產生的自增 ID 是多少」。所以 Service 中的
	 *   @Transactional 保證了同一個連線
	 */
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
	public Long getLastInsertId();


	
}
