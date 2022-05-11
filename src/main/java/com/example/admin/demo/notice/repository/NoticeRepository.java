package com.example.admin.demo.notice.repository;

import com.example.admin.demo.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {

  Optional<Notice> findByNoticeIdAndEnableIsTrue(Long noticeId);

  List<Notice> findByNoticeIdInAndEnableIsTrue(List<Long> noticesId);

}
