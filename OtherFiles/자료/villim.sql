-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 21-11-21 10:23
-- 서버 버전: 10.4.21-MariaDB
-- PHP 버전: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `villim`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `my_pick`
--

CREATE TABLE `my_pick` (
  `post_id` int(100) NOT NULL,
  `user_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 테이블 구조 `photos`
--

CREATE TABLE `photos` (
  `id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `file` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 테이블 구조 `post`
--

CREATE TABLE `post` (
  `id` int(100) NOT NULL,
  `title` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `content` varchar(300) CHARACTER SET utf8mb4 NOT NULL,
  `category` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `recommend` int(100) NOT NULL,
  `registration` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `writer_id` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `post`
--

INSERT INTO `post` (`id`, `title`, `content`, `category`, `recommend`, `registration`, `writer_id`) VALUES
(2, '', '', '글씨', 0, '2021년 11월 11일', '정은교');

-- --------------------------------------------------------

--
-- 테이블 구조 `users`
--

CREATE TABLE `users` (
  `id` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `tree` int(100) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `theme` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `my_pick`
--
ALTER TABLE `my_pick`
  ADD KEY `post_id` (`post_id`);

--
-- 테이블의 인덱스 `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- 테이블의 인덱스 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `post`
--
ALTER TABLE `post`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 덤프된 테이블의 제약사항
--

--
-- 테이블의 제약사항 `my_pick`
--
ALTER TABLE `my_pick`
  ADD CONSTRAINT `foreign_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
