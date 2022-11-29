-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.9.4-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- delivery 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `delivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `delivery`;

-- 테이블 delivery.baguni 구조 내보내기
CREATE TABLE IF NOT EXISTS `baguni` (
  `menu_no` int(11) DEFAULT NULL,
  `sb_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'shopping barsket 번호',
  `m_id` varchar(50) NOT NULL DEFAULT '',
  `menu_count` int(11) DEFAULT NULL COMMENT '메뉴 수량',
  PRIMARY KEY (`sb_no`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 delivery.baguni:~1 rows (대략적) 내보내기
INSERT INTO `baguni` (`menu_no`, `sb_no`, `m_id`, `menu_count`) VALUES
	(2, 1, 'test', 1),
	(1, 64, 'test1', 1),
	(2, 65, 'test1', 1),
	(4, 66, 'test1', 1);

-- 테이블 delivery.business 구조 내보내기
CREATE TABLE IF NOT EXISTS `business` (
  `b_id` varchar(50) NOT NULL COMMENT '업체 아이디',
  `b_pw` varchar(50) NOT NULL COMMENT '업체 비번',
  `b_name` varchar(50) NOT NULL COMMENT '업체명',
  `b_tel` varchar(50) NOT NULL COMMENT '업체 전화번호',
  `b_address` varchar(50) NOT NULL COMMENT '업체 주소',
  `b_reg_date` date DEFAULT NULL COMMENT '업체 가입날짜',
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='업체테이블';

-- 테이블 데이터 delivery.business:~8 rows (대략적) 내보내기
INSERT INTO `business` (`b_id`, `b_pw`, `b_name`, `b_tel`, `b_address`, `b_reg_date`) VALUES
	('kyo', '1234', '교촌치킨', '11', '22', NULL),
	('nene', '1234', '네네치킨', '22', '33', NULL),
	('test1', '1234', 'test1', '11', '22', NULL),
	('test2', '1234', 'test2', '11', '11', '2022-11-15'),
	('test3', '1234', 'test3', '11', '22', NULL),
	('test4', '1234', 'test4', '11', '22', NULL),
	('test5', '1234', 'test5', '11', '22', NULL),
	('test6', '1234', 'test6', '11', '22', NULL);

-- 테이블 delivery.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `m_id` varchar(50) NOT NULL COMMENT '아이디',
  `m_pw` varchar(50) DEFAULT NULL COMMENT '비번',
  `m_name` varchar(50) DEFAULT NULL COMMENT '이름',
  `m_address` varchar(50) DEFAULT NULL COMMENT '주소',
  `m_tel` varchar(50) DEFAULT NULL COMMENT '전화번호',
  `m_nickn` varchar(50) DEFAULT NULL COMMENT '닉네임',
  `m_reg_date` date DEFAULT NULL COMMENT '가입날짜',
  `m_authority` int(11) DEFAULT NULL COMMENT '1.고객 2.관리자 ',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='회원테이블';

-- 테이블 데이터 delivery.member:~5 rows (대략적) 내보내기
INSERT INTO `member` (`m_id`, `m_pw`, `m_name`, `m_address`, `m_tel`, `m_nickn`, `m_reg_date`, `m_authority`) VALUES
	('ghtjd2605', '1234', '김호성', '11', '11', '김호성', '2022-11-27', 1),
	('rlaghtjd2278', '1234', '김호성', '서울특별시 성북구', '01028216269', '호쒕', '2022-11-13', 1),
	('rlaghtjd2605', '1234', '김호성', 'fdssadf', '01028216269', '호', '2022-11-13', 2),
	('test1', '1234', 'test', '111', '222', 'test', '2022-11-15', 1);

-- 테이블 delivery.menu 구조 내보내기
CREATE TABLE IF NOT EXISTS `menu` (
  `menu_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '메뉴번호',
  `b_id` varchar(50) DEFAULT NULL COMMENT '업체 아이디(외래키)',
  `menuname` varchar(50) DEFAULT NULL COMMENT '메뉴이름',
  `menuprice` int(11) DEFAULT NULL COMMENT '메뉴가격',
  `menupic` varchar(100) DEFAULT NULL COMMENT '메뉴사진',
  PRIMARY KEY (`menu_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='업체 메뉴';

-- 테이블 데이터 delivery.menu:~4 rows (대략적) 내보내기
INSERT INTO `menu` (`menu_no`, `b_id`, `menuname`, `menuprice`, `menupic`) VALUES
	(1, 'kyo', '허니', 20000, NULL),
	(2, 'kyo', '레드', 19000, NULL),
	(3, 'nene', '스노윙치즈', 22000, NULL),
	(4, 'nene', '양념', 21000, NULL);

-- 테이블 delivery.order 구조 내보내기
CREATE TABLE IF NOT EXISTS `order` (
  `o_no` int(11) NOT NULL COMMENT '주문번호',
  `b_id` varchar(50) DEFAULT NULL,
  `m_id` varchar(50) DEFAULT NULL,
  `menu_no` int(11) DEFAULT NULL,
  `o_datetime` datetime DEFAULT NULL COMMENT '주문날짜',
  `o_state` varchar(50) DEFAULT NULL COMMENT '주문상태',
  `menu_count` int(11) DEFAULT NULL COMMENT '수량',
  PRIMARY KEY (`o_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='주문테이블';

-- 테이블 데이터 delivery.order:~1 rows (대략적) 내보내기
INSERT INTO `order` (`o_no`, `b_id`, `m_id`, `menu_no`, `o_datetime`, `o_state`, `menu_count`) VALUES
	(1, 'kyo', 'test1', 2, NULL, '완료', NULL),
	(2, 'nene', NULL, NULL, NULL, NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
