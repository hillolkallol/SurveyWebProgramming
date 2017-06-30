-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 12, 2016 at 08:52 PM
-- Server version: 10.1.19-MariaDB-1~jessie
-- PHP Version: 5.6.27-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `apollo10_survey_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `country_id` int(11) NOT NULL,
  `shortname` varchar(3) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE IF NOT EXISTS `options` (
`option_id` int(11) NOT NULL,
  `option_desc` varchar(100) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `option_order` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`option_id`, `option_desc`, `question_id`, `option_order`) VALUES
(16, 'Extremely skilled', 4, NULL),
(17, 'Very skilled', 4, NULL),
(18, 'Somewhat skilled', 4, NULL),
(19, 'Not so skilled', 4, NULL),
(20, '', 4, NULL),
(21, 'Extremely professionally ', 5, NULL),
(22, 'Very professionally ', 5, NULL),
(23, 'Somewhat professionally ', 5, NULL),
(24, 'Not so professionally ', 5, NULL),
(25, '', 5, NULL),
(26, 'Extremely honest.', 6, NULL),
(27, 'Very honest.', 6, NULL),
(28, 'Somewhat honest.', 6, NULL),
(29, 'Not so honest.', 6, NULL),
(30, '', 6, NULL),
(31, 'Extremely well.', 7, NULL),
(32, 'Very well.', 7, NULL),
(33, 'Somewhat well.', 7, NULL),
(34, 'Not so well.', 7, NULL),
(35, '', 7, NULL),
(36, 'Extremely well.', 8, NULL),
(37, 'Very well.', 8, NULL),
(38, 'Somewhat well.', 8, NULL),
(39, 'Not so well.', 8, NULL),
(40, '', 8, NULL),
(41, 'Not at all', 10, NULL),
(42, 'A little bit', 10, NULL),
(43, 'Somewhat ', 10, NULL),
(44, 'Somewhat ', 10, NULL),
(45, '', 10, NULL),
(46, 'Not at all', 11, NULL),
(47, 'A little bit', 11, NULL),
(48, 'Somewhat ', 11, NULL),
(49, 'Somewhat ', 11, NULL),
(50, '', 11, NULL),
(51, 'Somewhat ', 12, NULL),
(52, '', 12, NULL),
(53, '', 12, NULL),
(54, '', 12, NULL),
(55, '', 12, NULL),
(56, 'Option 1', 13, NULL),
(57, 'Option 2', 13, NULL),
(58, 'Option 3', 13, NULL),
(59, 'Option 4', 13, NULL),
(60, 'Option 5', 13, NULL),
(61, 'Option 1', 14, NULL),
(62, '', 14, NULL),
(63, '', 14, NULL),
(64, '', 14, NULL),
(65, '', 14, NULL),
(66, 'russia', 15, NULL),
(67, 'brazil', 15, NULL),
(68, 'arzentina', 15, NULL),
(69, 'bangladesh', 15, NULL),
(70, 'USA', 15, NULL),
(71, 'Germany', 16, NULL),
(72, 'Italy', 16, NULL),
(73, 'England', 16, NULL),
(74, 'France', 16, NULL),
(75, '', 16, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `participation`
--

CREATE TABLE IF NOT EXISTS `participation` (
  `survey_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `survey_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participation`
--

INSERT INTO `participation` (`survey_id`, `user_id`, `survey_time`) VALUES
(5, 1, '2016-12-10 13:48:45'),
(6, 23, '2016-12-10 15:53:56'),
(6, 24, '2016-12-10 15:54:45'),
(5, 18, '2016-12-10 20:08:27'),
(6, 1, '2016-12-11 22:14:09'),
(7, 1, '2016-12-12 19:46:04'),
(4, 1, '2016-12-12 20:11:46');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
`question_id` int(11) NOT NULL,
  `question` varchar(100) DEFAULT NULL,
  `survey_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`question_id`, `question`, `survey_id`) VALUES
(4, 'How skilled at their jobs are the members of your team.', 3),
(5, 'How professionally do the member of your team behave? ', 3),
(6, 'How honest with each others the members of your team?', 3),
(7, 'How well do members of your team share the responsibility?', 3),
(8, 'How well does your supervisor work with clients?', 3),
(9, 'Do you typically fly for business, personal or other reasons?', 4),
(10, 'To what extent do you think that children enjoy going school?', 5),
(11, 'How much does the school value diversity of children''s background?', 5),
(12, 'How motivate the class rooms are ', 5),
(13, 'Question 1', 6),
(14, 'Question 2', 6),
(15, 'Who is going to win 2022 world cup?', 7),
(16, 'Who will be runnerup?', 7);

-- --------------------------------------------------------

--
-- Table structure for table `recovery_temp_info`
--

CREATE TABLE IF NOT EXISTS `recovery_temp_info` (
`serial_id` int(11) NOT NULL,
  `email_address` varchar(40) NOT NULL,
  `auto_id` varchar(40) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

CREATE TABLE IF NOT EXISTS `states` (
  `state_id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `survey_details`
--

CREATE TABLE IF NOT EXISTS `survey_details` (
`survey_id` int(11) NOT NULL,
  `survey_title` varchar(128) DEFAULT NULL,
  `survey_description` varchar(512) DEFAULT NULL,
  `logo_location` varchar(11) DEFAULT NULL,
  `last_modification_time` datetime DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `closing_time` datetime DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `survey_details`
--

INSERT INTO `survey_details` (`survey_id`, `survey_title`, `survey_description`, `logo_location`, `last_modification_time`, `publish_time`, `closing_time`, `user_id`) VALUES
(3, 'Department Performance Template ', 'Find out your departmentâ??s strengths and where thereâ??s room for improvement. How professionally do members of your team behave? How well does your teamâ??s supervisor work with clients? How fair are the responsibilities spread out among team members? The feedback you receive can be instrumental in creating a positive, collaborative, and hard-working team. Customize the survey template to add questions specific to your company or department.', NULL, '2016-12-10 02:02:05', '2016-12-10 02:02:05', NULL, 19),
(4, 'Airline Passenger Feedback ', 'The airline industry is highly competitive, and itâ??s often just the small details that will influence someoneâ??s decision to choose one airline over another. Our expert-certified airline passenger feedback survey template helps you find out how your airline is doing, so you donâ??t miss a beat. With our survey, learn if people are satisfied with your airline.', NULL, '2016-12-10 02:09:53', '2016-12-10 02:09:53', NULL, 19),
(5, 'School Climate Survey', 'Get a feel for your schoolâ??s environment when you give your studentsâ?? parents the school climate survey. This expert-certified survey template was created by the Harvard Graduate School of Education to help you gauge the overall attitudes that govern your schoolâ??s atmosphere. Whether you want to know about how much your school values diversity or how motivating classroom lessons are, use this survey to find out where you need to make improvements.', NULL, '2016-12-10 02:32:42', '2016-12-10 02:32:42', NULL, 19),
(6, 'Test Survey', 'Test Survey Desc', NULL, '2016-12-10 15:53:11', '2016-12-10 15:53:11', NULL, 23),
(7, 'Another test survey', 'Survey description', NULL, '2016-12-12 18:12:37', '2016-12-12 18:12:37', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`user_id` int(11) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `username` varchar(11) NOT NULL,
  `email_address` varchar(40) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `username`, `email_address`, `password`, `gender`, `date_of_birth`, `state_id`) VALUES
(1, 'Kallol', 'Das', 'kallol', 'kalloldash@gmail.com', 'kallol', 1, '1992-01-01', 40),
(8, 'a', 'a', 'a', 'a@a.com', 'aa', 1, '2016-01-10', 1),
(10, 'aa', 'Dasaa', 'Dasaa', 'kaaaadash@gmail.com', 'aa', 1, '2016-01-10', 11),
(11, 'New', 'New', 'New', 'new@new.com', 'new', 1, '2016-01-01', 11),
(13, 'aa', 'BB', 'rt', 'aa@m.com', 'abc', 1, '2016-01-09', 2),
(14, 'Sheikh', 'Bakir', 'bakir', 'sheikh.bakir7@gmail.com', '12345', 1, '2016-01-10', 12),
(15, 'aaa', 'aaa', 'aaa', 'aaa@aaa.com', 'aaa', 1, '2016-01-01', 1),
(16, 'Sheikh', 'Bakir', 'ww', 'sheikh.bakir@gmail.com', '12345', 1, '2016-01-24', 1),
(17, 'null', 'null', 'ww', 'null', 'null', 1, '2016-01-24', 1),
(18, 'Bryan', 'Watson', 'bwatson', 'bryan.watson@student.nmt.edu', 'testPassword', 1, '2016-01-14', 1),
(19, 'Sheikh', 'Bakir', 'sbakir', 'sheikh@gmail.com', '12345', 1, '2016-01-16', 1),
(20, 'bob', 'bob', 'bob', 'bob@bob.com', '123', 1, '2016-01-15', 1),
(21, 'qqq', 'qqq', 'qqq', 'qqq@qqq.qqq', 'qqq', 1, '2016-01-07', 1),
(22, 'asdf', 'hjkl', 'asdf', 'test@test.com', 'hello', 1, '1900-01-01', 1),
(23, 'Test', 'User', 'test', 'test@nmt.edu', 'test2', 1, '2016-01-01', 1),
(24, 'hey', 'there', 'hello', 'hello@email.com', 'pass', 1, '2016-01-24', 1),
(25, 'Jodi', 'Clark', 'jac', 'jac@cs.nmt.edu', 'password', 2, '1974-01-03', 1),
(26, 'hi', 'hi', 'laksjd', 'hi@lol.edu', 'hi', 1, '2016-01-01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_selected_option`
--

CREATE TABLE IF NOT EXISTS `user_selected_option` (
  `user_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `option_id` int(11) DEFAULT NULL,
`selection_id` int(11) NOT NULL,
  `survey_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_selected_option`
--

INSERT INTO `user_selected_option` (`user_id`, `question_id`, `option_id`, `selection_id`, `survey_id`) VALUES
(1, 10, 41, 1, 5),
(1, 11, 47, 2, 5),
(1, 12, 51, 3, 5),
(23, 13, 56, 4, 6),
(23, 14, 61, 5, 6),
(24, 13, 58, 6, 6),
(24, 14, 61, 7, 6),
(24, 13, 58, 8, 6),
(24, 14, 61, 9, 6),
(18, 10, 42, 10, 5),
(18, 11, 48, 11, 5),
(18, 12, 51, 12, 5),
(1, 13, 57, 13, 6),
(1, 14, 61, 14, 6),
(1, 15, 67, 15, 7),
(1, 16, 71, 16, 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `country`
--
ALTER TABLE `country`
 ADD PRIMARY KEY (`country_id`);

--
-- Indexes for table `options`
--
ALTER TABLE `options`
 ADD PRIMARY KEY (`option_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
 ADD PRIMARY KEY (`question_id`);

--
-- Indexes for table `recovery_temp_info`
--
ALTER TABLE `recovery_temp_info`
 ADD PRIMARY KEY (`serial_id`);

--
-- Indexes for table `states`
--
ALTER TABLE `states`
 ADD PRIMARY KEY (`state_id`);

--
-- Indexes for table `survey_details`
--
ALTER TABLE `survey_details`
 ADD PRIMARY KEY (`survey_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `email_address` (`email_address`);

--
-- Indexes for table `user_selected_option`
--
ALTER TABLE `user_selected_option`
 ADD PRIMARY KEY (`selection_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE `options`
MODIFY `option_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `recovery_temp_info`
--
ALTER TABLE `recovery_temp_info`
MODIFY `serial_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `survey_details`
--
ALTER TABLE `survey_details`
MODIFY `survey_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `user_selected_option`
--
ALTER TABLE `user_selected_option`
MODIFY `selection_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
