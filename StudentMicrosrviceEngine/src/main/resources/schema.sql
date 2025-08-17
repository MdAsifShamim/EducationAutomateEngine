CREATE TABLE IF NOT EXISTS `studentpersonal`
(
`student_id` int AUTO_INCREMENT PRIMARY KEY,
`firstname` varchar(100) NOT NULL,
`lastname` varchar(100) NOT NULL,
`email` varchar(100) NOT NULL,
`mobile_number` varchar(20) NOT NULL,
`address` varchar(150) NOT NULL,
`dob` varchar(20) NOT NULL,
`created_at` date NOT NULL,
`created_by` varchar(20) NOT NULL,
`updated_at` date DEFAULT NULL,
`updated_by` varchar(20) DEFAULT NULL
);
