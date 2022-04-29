package com.example.admin.demo.repository;

import com.example.admin.demo.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository <Notice, Long> {
}
