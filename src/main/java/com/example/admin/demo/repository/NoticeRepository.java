package com.example.admin.demo.repository;

import com.example.admin.demo.domain.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {

  Optional<Notice> findByNoticeIdAndEnableIsTrue(Long noticeId);

  List<Notice> findByNoticeIdInAndEnableIsTrue(List<Long> noticesId);

  Page<Notice> findAllByNoticeTitleContaining(Pageable pageable, String noticeTitle);

}
